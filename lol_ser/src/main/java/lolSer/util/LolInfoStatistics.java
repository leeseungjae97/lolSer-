package lolSer.util;

import lolSer.JsonObject.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LolInfoStatistics {
    MatchTotalInfo matchTotalInfo;
    String summonerId;
    int[] firstItemBuyList;
    HashMap<String, Integer>otherTeamChampId;
    LeagueEntries les = null;
    int top = 0;

    public LolInfoStatistics(MatchTotalInfo matchTotalInfo, String summonerId, HashMap<String, Integer>otherTeamChampId, int branch) {
        this.matchTotalInfo = matchTotalInfo;
        this.summonerId = summonerId;
        this.otherTeamChampId = otherTeamChampId;
        System.out.println("branch : " + branch);

        switch (branch) {
            case 0:
                les = SavePref.getLeagueEntries(MakeUrl.EN1_RANK_INFO);
                break;
            case 1:
                les = SavePref.getLeagueEntries(MakeUrl.EN2_RANK_INFO);
                break;
            case 2:
                les = SavePref.getLeagueEntries(MakeUrl.EN3_RANK_INFO);
                break;
            case 3:
                les = SavePref.getLeagueEntries(MakeUrl.EN4_RANK_INFO);
                break;
            case 4:
                les = SavePref.getLeagueEntries(MakeUrl.EN5_RANK_INFO);
                break;
        }
    }

    public String getRankPostion() {
        for (int i = 0; i < (les != null ? les.getLeagueEntries().length : 0); i++) {
            if(les.getLeagueEntries()[i].getQueueType().equals("RANKED_SOLO_5x5")) {
                LeagueEntry le = les.getLeagueEntries()[i];
                return le.getTier() + " " + le.getRank() + " " + le.getLeaguePoints();
            }
        }
        return "랭크 정보가 없습니다.";
    }
    //1. 승률 : 해당 챔피언 전체 판수 / 승 : 한번에 많은 판수를 가져올 수 없음 불가능
    // -> 랭크 승률로 바꾸기.
    public String getRankWinRate() {
        for (int i = 0; i < (les != null ? les.getLeagueEntries().length : 0); i++) {
            if(les.getLeagueEntries()[i].getQueueType().equals("RANKED_SOLO_5x5")) {
                LeagueEntry le = les.getLeagueEntries()[i];
                double totalPlayed = le.getLosses() + le.getWins();
                System.out.println("totalPlayed : " + totalPlayed);
                return "랭크 승률 : "+(int)(( le.getWins() / totalPlayed) * 100.0 ) + "%";
            }
        }
        return "랭크 정보가 없습니다.";
    }

    //2. 초반 선호 아이템 : -> timeline
    // : 가져온 판수 중에 해당 챔피언이 있다는 보장을 못함
    // 그래서 가져온 판수중에 같은 라인의 정보를 가져와야하는데
    // 그러면 의미가없어짐.
    // 아이템 관련 다른걸로 바꾸기

    //3. 평균 K/D : 해당 챔피언 킬 / 데스
    // 1, 2번과 같은 이유 -> 전판 킬 관여율로 전환

    //4. 주 플레이 라인 : 해당 챔피언 전체 판수 / 플레이한 라인 비중
    // 해당 챔피언 전체판수를 못가져옴.
    // 플레이한 라인 비중을 못가져옴
    // -> 불가능

    //5. 퍼스트 킬 관여율 : 해당 챔피언 전체 판수 / 퍼블 킬 및 퍼블 어시한 판
    // 전판 퍼스트 킬 관여율은 가져올 수 있는데.
    // 그런건 의미 없을듯.
    // 통계를 낼 수 있을만한 정보여야함.

    //    선호 아이템
    public void getLaneFirstUseItem() {
        //원챔장인이지 않은 이상 하나의 챔피언을 계속하는경우는 거의없음
        //그래서 같은 라인에서 통계를 내야함.
        //알아내는 방법은 쉬움
        //매치디테일에서 lane을 알아내고 해당 lane의 puuid를 매치타임라인의 puuid와 비교하여
        //동일한 puuid의 firstbuyitem을 가져오면 됨.

        //문제는 currentGameInfo에서 lane을 알아낼 수 없다는점.
        //이게 시급하다...
        //currentGameInfo에서 lane을 제공하는 api는 따로없는듯
        //다들 무슨 통계내고 지랄났네..

        ArrayList<MatchTimeLine> timelines = new ArrayList<>();
        ArrayList<Match> matchesDetail = new ArrayList<>();
        boolean findId = false;

        matchesDetail = matchTotalInfo.getmDAl();
        for (int i = 0; i < matchesDetail.size(); i++) {
            Match match = matchesDetail.get(i);
            for (int j = 0; j < match.getInfo().getParticipants().size(); j++) {
                if(match.getInfo().getParticipants().get(j).getSummonerId().equals(summonerId)) {
                    findId = true;
                }
            }
            if(!findId) {
                matchesDetail.remove(i);
            }
        }

        firstItemBuyList = new int[matchTotalInfo.getMtlAl().size() * 6];
        if(matchTotalInfo.getMtlAl().size() != 0) {
            System.out.println("Match id : " + matchTotalInfo.getMtlAl().get(0).getMetadata().getMatchId());
            for (int i = 0; i < matchTotalInfo.getMtlAl().size(); i++) {
                MatchTimeLine mtl = matchTotalInfo.getMtlAl().get(i);
                int getParticipantId = mtl.getMetadata().getParticipantsId();
                ArrayList<Event> itemBuyEvent = mtl.getInfo().getFrames().get(1).getEvents();

                for (int j = 0; j < itemBuyEvent.size(); j++) {
                    if(itemBuyEvent.get(j).getParticipantId() == getParticipantId && itemBuyEvent.get(j).getType().equals("ITEM_PURCHASED")) {
                        firstItemBuyList[top] = itemBuyEvent.get(j).getItemId();
                        top++;
                    }
                }
            }
//            for (int i = 0; i < firstItemBuyList.length; i++) {
//                System.out.println("BUY LIST : " + firstItemBuyList[i]);
//            }
        }
        //여기서 itemid 뽑아오기
        //GET_ITEM_IMAGE 활용.
    }
    //    플레이 성향
    public String getCurrentChampionFirstKillTime() {
        for (int i = 0; i < matchTotalInfo.getmDAl().size(); i++) {
            Match match = matchTotalInfo.getmDAl().get(i);
            for (int j = 0; j < match.getInfo().getParticipants().size(); j++) {
                MatchParticipant matchParticipant = match.getInfo().getParticipants().get(j);
                if(matchParticipant.isFirstBloodAssist()) {

                }
                if (matchParticipant.isFirstBloodKill()) {

                }
                matchParticipant.getLane();
            }
        }
        // 라인전이 강함
        // 버티는걸 잘함
        // 킬 기여도가 높음
        return "";
    }
    //    팀 기여도
    public String getBeforeGameTeamContribution() {
        int killContribution = 0;
        if(matchTotalInfo.getmDAl().size() == 0) {
            return "플레이한 랭크 게임 정보가 없습니다.";
        }
        int totalKills = 0;
        long teamId = 100;
        int userKill = 0;
        int userAssists = 0;

        double totalContribution = 0;

        Match match = matchTotalInfo.getmDAl().get(0);
        for (int j = 0; j < match.getInfo().getParticipants().size(); j++) {
            if (match.getInfo().getParticipants().get(j).getSummonerId().equals(summonerId)) {
                teamId = match.getInfo().getParticipants().get(j).getTeamId();
            }
        }

        for (int j = 0; j < match.getInfo().getParticipants().size(); j++) {
            if(match.getInfo().getParticipants().get(j).getTeamId() == teamId) {
                totalKills += match.getInfo().getParticipants().get(j).getKills();
            }
            if(match.getInfo().getParticipants().get(j).getSummonerId().equals(summonerId)) {
                MatchParticipant matchParticipant = match.getInfo().getParticipants().get(j);
                userKill= matchParticipant.getKills();
                userAssists = matchParticipant.getAssists();
            }
        }
        totalContribution = ((userKill / (double)totalKills) * 100.0);
        totalContribution += ((userAssists / (double)totalKills) * 100.0);

        killContribution = (int)totalContribution;

        return "이전 랭크게임 킬 관여율 : " + killContribution + "%";
    }

    //  판당 와드 설치 수
    public int getWardPlaced() {
        ArrayList<Match> matchesDetail = new ArrayList<>();
        int totalWardPlaced = 0;
        matchesDetail = matchTotalInfo.getmDAl();
        for (int i = 0; i < matchesDetail.size(); i++) {
            Match match = matchesDetail.get(i);
            for (int j = 0; j < match.getInfo().getParticipants().size(); j++) {
                if(match.getInfo().getParticipants().get(j).getSummonerId().equals(summonerId)) {
                    System.out.println("pink"+match.getInfo().getParticipants().get(j).getDetectorWardsPlaced());
                    System.out.println("ward"+match.getInfo().getParticipants().get(j).getWardsPlaced());
                    totalWardPlaced += match.getInfo().getParticipants().get(j).getDetectorWardsPlaced();
                }
            }
        }
        return (totalWardPlaced / matchesDetail.size());
    }
}
