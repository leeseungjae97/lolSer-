package lolSer.util;

import lolSer.JsonObject.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static lolSer.util.StringResources.*;

public class MakeUrl {
//    public final static int PUUID = 1;
    public final static int ACCOUNT_ID = 2;

    public final static int GET_EN1_MATCHES = 3;
    public final static int GET_EN2_MATCHES = 4;
    public final static int GET_EN3_MATCHES = 5;
    public final static int GET_EN4_MATCHES = 6;
    public final static int GET_EN5_MATCHES = 7;

    public final static int CURRENT_GAME = 8;

    public final static int MATCH_DETAIL = 9;
    public final static int EN1_MATCH_DETAIL = 10;
    public final static int EN2_MATCH_DETAIL = 11;
    public final static int EN3_MATCH_DETAIL = 12;
    public final static int EN4_MATCH_DETAIL = 13;
    public final static int EN5_MATCH_DETAIL = 14;

    public final static int EN1_MATCH_TIME_LINE = 15;
    public final static int EN2_MATCH_TIME_LINE = 16;
    public final static int EN3_MATCH_TIME_LINE = 17;
    public final static int EN4_MATCH_TIME_LINE = 18;
    public final static int EN5_MATCH_TIME_LINE = 19;

    public final static int GET_ITEM_IMAGE = 20;

    public final static int RANK_INFO = 21;
    public final static int EN1_RANK_INFO = 22;
    public final static int EN2_RANK_INFO = 23;
    public final static int EN3_RANK_INFO = 24;
    public final static int EN4_RANK_INFO = 25;
    public final static int EN5_RANK_INFO = 26;

    public final static int CURRENT_GAME_IN_LOCAL = 27;
    public final static int CHECK_LOCAL_GAME_START = 28;

    public final static int EN1_MATCH_LIST = 29;
    public final static int EN2_MATCH_LIST = 30;
    public final static int EN3_MATCH_LIST = 31;
    public final static int EN4_MATCH_LIST = 32;
    public final static int EN5_MATCH_LIST = 33;


    private final static int GET_ENEMY_TIME_LINE_BRANCH_VALUE = 12;
    private static final int MAX_PLAYER = 10;

    public URL URL;
    public Http http;

    private StringBuilder st;
    private Response response;
    private int branch = -1;
    //branch
    
    public MakeUrl() {
        this.st = new StringBuilder();
        this.http = new Http();
    }
    public Byte getItemImage(int itemCode) {
        try {
            st = new StringBuilder();
            st.append(GET_ITEM_IMAGE)
                    .append(itemCode);

            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if(response != null) {
                if(response.getClass() == (ErrorStatus.class)) {
                    ErrorStatus es = ((ErrorStatus)response);
                }
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MatchTimeLine getMatchTimeLine(long matchId, int branch) {
        try {
            st = new StringBuilder();
            st.append(KR_SERVER_API)
                    .append(MATCH_V4)
                    .append(TIME_LINE)
                    .append(matchId)
                    .append(API_KEY);

            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if(response != null) {
                if(response.getClass() == (MatchTimeLine.class)) {
                    return (MatchTimeLine) response;
                }
                if(response.getClass() == (ErrorStatus.class)) {
                    ErrorStatus es = ((ErrorStatus)response);
                }
            }
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public MatchTotalInfo getMatchList(String accountId, int branch){
        try {
            st = new StringBuilder();
            st.append(KR_SERVER_API)
                    .append(GET_MATCH_LIST)
                    .append(accountId)
                    .append(MATCH_LIST_LENGTH_100)
                    .append(API_KEY_2);

            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if(response != null) {
                if(response.getClass() == (MatchList.class)) {
                    MatchList matchList = (MatchList) response;
                    ArrayList<MatchTimeLine> timelines = new ArrayList<>();
                    ArrayList<Match> matchesDetail = new ArrayList<>();
                    for (int i = 0; i < matchList.getMatches().size(); i++) {
                        System.out.println("match id : " + matchList.getMatches().get(i).getGameId());
                        Match match = getMatchDetail(matchList.getMatches().get(i).getGameId(), MATCH_DETAIL);
                        MatchTimeLine mtl = new MakeUrl().getMatchTimeLine(matchList.getMatches().get(i).getGameId(), branch + GET_ENEMY_TIME_LINE_BRANCH_VALUE);

//                        mtl.getMetadata().setParticipantsId(accountId);
//                        mtl.setEnmBranch(branch);

                        timelines.add(mtl);
                        matchesDetail.add(match);
                    }
                    return new MatchTotalInfo(timelines, matchesDetail);
                }
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Match getMatchDetail(long matchId, int branch) {
        try {
            st = new StringBuilder();
            st.append(KR_SERVER_API)
                    .append(MATCH_V4)
                    .append(MATCHES)
                    .append(matchId)
                    .append(API_KEY);

            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if (response != null) {
                if (response.getClass() == (Match.class)) {
                    return ((Match) response);
                }
                if (response.getClass() == (ErrorStatus.class)) {
                    ErrorStatus es = ((ErrorStatus) response);
                }
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public MatchTotalInfo getTimeLineByAccount(String getName, int branch, int enmBranch) {
        try {
            st = new StringBuilder();
            st.append(KR_SERVER_API)
                    .append(GET_ACCOUNT_ID_BY_NAME)
                    .append(getName)
                    .append(API_KEY);

            System.out.println(st.toString());
            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if(response != null) {
                if(response.getClass() == (Summoner.class)) {
                    Summoner su = (Summoner)response;
                    System.out.println("puuid : " + su.getPuuid());
                    System.out.println("id : " + su.getId());
                    System.out.println("accountId : " + su.getAccountId());
                    MatchTotalInfo matchTotalInfo = getMatchList(su.getAccountId(), enmBranch);
                    getRankInfo(su.getId(), MakeUrl.RANK_INFO, enmBranch);
                    SavePref.setPuuid(su.getPuuid(), enmBranch);

                    return matchTotalInfo;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //AsyncHTTPTest
//    public MatchTotalInfo getTimeLineByAccount(String getName, int branch, int enmBranch) {
//        final MatchTotalInfo[] matchTotalInfo = {null};
//        synchronized (this) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        st = new StringBuilder();
//                        st.append(KR_SERVER_API)
//                                .append(GET_ACCOUNT_ID)
//                                .append(getName)
//                                .append(API_KEY);
//
//                        System.out.println(st.toString());
//                        URL = new URL(st.toString());
//                        response = http.GET(URL, branch);
//
//                        if(response != null) {
//                            if(response.getClass() == (Summoner.class)) {
//                                Summoner su = (Summoner)response;
//                                System.out.println("puuid : " + su.getPuuid());
//                                System.out.println("id : " + su.getId());
//                                matchTotalInfo[0] = getMatches(su.getPuuid(), enmBranch);
//                                getRankInfo(su.getId(), MakeUrl.RANK_INFO, enmBranch);
//                                SavePref.setPuuid(su.getPuuid(), enmBranch);
//                            }
//                        }
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//        System.out.println("Sync");
//        return matchTotalInfo[0];
//    }
    public void getRankInfo(String id, int branch, int saveKeyBranch) {
        try {
            st = new StringBuilder();
            st.append(KR_SERVER_API)
                    .append(StringResources.GET_RANK_INFO_BY_SUMMONER)
                    .append(id)
                    .append(API_KEY);

            URL = new URL(st.toString());
            response = http.GET(URL, branch);

            if(response != null) {
                if(response.getClass() == (LeagueEntries.class)) {
                    SavePref.setLeagueEntries((LeagueEntries) response, saveKeyBranch + 19);
                }
                if(response.getClass() == (ErrorStatus.class)) {
                    ErrorStatus es = ((ErrorStatus)response);
                }
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public Response HTTP_GET(int action) {
        this.branch = action;
        this.st = new StringBuilder();
        try {
            switch (branch) {
//                case PUUID:
//                    st.append(SERVER_OF_ASIA_API)
//                            .append(GET_PUUID)
//                            .append(SavePref.getGameName())
//                            .append(TAG_LINE)
//                            .append(API_KEY);
//                    URL = new URL(st.toString());
//                    response = http.GET(URL, branch);
//                    if(response != null) {
//                        if(response.getClass() == (Account.class)) {
//                            Account ac = ((Account)response);
//                            SavePref.setAccount( ac.getPuuid(), SavePref.getGameName() );
//                        }
//                        if(response.getClass() == (ErrorStatus.class)) {
//                            ErrorStatus es = ((ErrorStatus)response);
//                            System.out.println(es.toString());
//                        }
//                    }
//                    break;

                case ACCOUNT_ID:
                    st.append(KR_SERVER_API)
                            .append(GET_ACCOUNT_ID_BY_NAME)
                            .append(SavePref.getGameName())
                            .append(API_KEY);

                    URL = new URL(st.toString());
                    response = http.GET(URL, branch);

                    if(response != null) {
                        if(response.getClass() == (Summoner.class)) {
                            Summoner su = ((Summoner)response);
                            System.out.println("player : " + su.toString());
                            SavePref.setSummoner(su.getAccountId(), su.getProfileIconId(), su.getRevisionDate(),
                                    su.getName(),su.getId(), su.getPuuid(), su.getSummonerLevel() );
                            System.out.println("player Id : " + SavePref.getId() + '\n');
                            response = null;

                            st = new StringBuilder();
                            st.append(KR_SERVER_API)
                                    .append(StringResources.GET_RANK_INFO_BY_SUMMONER)
                                    .append(SavePref.getId())
                                    .append(API_KEY);

                            URL = new URL(st.toString());
                            response = http.GET(URL, RANK_INFO);

                            if(response != null) {
                                if (response.getClass() == (LeagueEntries.class)) {
                                    LeagueEntries leagueEntries = ((LeagueEntries) response);
                                    SavePref.setLeagueEntries(leagueEntries, RANK_INFO);
                                    break;
                                }
                                if (response.getClass() == (ErrorStatus.class)) {
                                    ErrorStatus es = ((ErrorStatus) response);
                                }
                            }
                        }
                        if(response.getClass() == (ErrorStatus.class)) {
                            ErrorStatus es = ((ErrorStatus)response);
                            System.out.println(es.toString());
                        }
                    }
                    break;
                case GET_EN1_MATCHES:
                case GET_EN2_MATCHES:
                case GET_EN3_MATCHES:
                case GET_EN4_MATCHES:
                case GET_EN5_MATCHES:
                    st.append(SERVER_OF_ASIA_API)
                            .append(GET_MATCHES_ID_BY_PUUID)
                            .append(SavePref.getPuuid(branch))
                            .append(SET_RANK_MATCHES_LENGTH)
                            .append(API_KEY_2);

                    URL = new URL(st.toString());
                    response = http.GET(URL, branch);

                    if(response != null) {
                        if(response.getClass() == (Matches.class)) {
                            Matches matches = ((Matches)response);
                            System.out.println(matches.toString());
                            SavePref.setMatches(matches.getMatches(), branch);
                        }
                        if(response.getClass() == (ErrorStatus.class)) {
                            ErrorStatus es = ((ErrorStatus)response);
                            System.out.println(es.toString());
                        }
                    }
                    break;
//                case EN1_MATCH_TIME_LINE:
//                case EN2_MATCH_TIME_LINE:
//                case EN3_MATCH_TIME_LINE:
//                case EN4_MATCH_TIME_LINE:
//                case EN5_MATCH_TIME_LINE:
//                    st.append(SERVER_OF_ASIA_API)
//                            .append(GET_MATCH_INFO)
//                            .append(SavePref.getEnemyMatchId(branch))
//                            .append(GET_MATCH_TIME_LINE)
//                            .append(API_KEY);
//
//                    URL = new URL(st.toString());
//                    response = http.GET(URL, branch);
//
//                    if(response != null) {
//                        if(response.getClass() == (CurrentGameInfo.class)) {
//
//                        }
//                        if(response.getClass() == (ErrorStatus.class)) {
//                            ErrorStatus es = ((ErrorStatus)response);
//                            System.out.println(es.toString());
//                        }
//                    }
//                    break;
                case CURRENT_GAME:
                    st.append(KR_SERVER_API)
                            .append(StringResources.GET_CURRENT_GAME_BY_SUMMONER)
                            .append(SavePref.getId())
                            .append(API_KEY);

                    URL = new URL(st.toString());
                    response = http.GET(URL, branch);

                    if(response != null) {
                        if(response.getClass() == (CurrentGameInfo.class)) {
                            CurrentGameInfo currentGameInfo = ((CurrentGameInfo)response);
                            SavePref.setGameId(currentGameInfo.getGameId());
                            SavePref.setCurrentGameInfo(currentGameInfo);
                            currentGameInfo.checkUserTeamId();
                        }
                        if(response.getClass() == (ErrorStatus.class)) {
                            ErrorStatus es = ((ErrorStatus)response);
                            System.out.println(es.toString());
                        }
                    }
                    break;
                case RANK_INFO:
                    st.append(KR_SERVER_API)
                            .append(StringResources.GET_RANK_INFO_BY_SUMMONER)
                            .append(SavePref.getId())
                            .append(API_KEY);

                    URL = new URL(st.toString());
                    response = http.GET(URL, branch);

                    if(response != null) {
                        if(response.getClass() == (LeagueEntries.class)) {
                            SavePref.setLeagueEntries((LeagueEntries) response, RANK_INFO);
                        }
                        if(response.getClass() == (ErrorStatus.class)) {
                            ErrorStatus es = ((ErrorStatus)response);
                        }
                    }
                    break;
//                case MATCH_DETAIL:
//                    st.append(SERVER_OF_ASIA_API)
//                            .append(GET_MATCH_INFO)
//                            .append(SavePref.getId())
//                            .append(API_KEY);
//
//                    URL = new URL(st.toString());
//                    response = http.GET(URL, branch);
//
//                    if(response != null) {
//                        if (response.getClass() == (Match.class)) {
//                            Match match = ((Match) response);
//                            return match;
//                        }
//                        if(response.getClass() == (ErrorStatus.class)) {
//                            ErrorStatus es = ((ErrorStatus)response);
//                        }
//                    }
//                    break;
//                case CHECK_LOCAL_GAME_START:
//                    st.append(LOCAL_CLIENT_NAME)
//                            .append(CLIENT_START);
//
//                    URL = new URL(st.toString());
//                    response = http.GET(URL, CHECK_LOCAL_GAME_START);
//
//                    if(response != null) {
//                        if (response.getClass() == (ClientStart.class)) {
//                            ClientStart clientStart= ((ClientStart) response);
//                            System.out.println(clientStart);
//                            return ((ClientStart)response);
//                        }
//                        if(response.getClass() == (ErrorStatus.class)) {
//                            ErrorStatus es = ((ErrorStatus)response);
//                        }
//                    }
//                    break;
                case CURRENT_GAME_IN_LOCAL:
                    st.append(LOCAL_CLIENT_NAME)
                            .append(CLIENT_INFO);

                    URL = new URL(st.toString());
                    response = http.GET(URL, CURRENT_GAME_IN_LOCAL);

                    if(response != null) {
                        if (response.getClass() == (LocalCurrentGameInfo.class)) {
                            LocalCurrentGameInfo localCurrentGameInfo= ((LocalCurrentGameInfo) response);
                            SavePref.setClientStart(true);
                            return localCurrentGameInfo;
                        }
                        if(response.getClass() == (ErrorStatus.class)) {
                            ErrorStatus es = ((ErrorStatus)response);
                        }
                    }
                    break;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return response;
    }
}

