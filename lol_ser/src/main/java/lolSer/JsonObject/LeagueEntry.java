package lolSer.JsonObject;

import java.io.Serializable;

public class LeagueEntry extends Response implements Serializable {
    String leagueId;
    String queueType;
    String rank;
    String summonerId;
    String summonerName;
    String tier;
    int leaguePoints;
    int wins;
    int losses;
    boolean veteran;
    boolean inactive;
    boolean freshBlood;
    boolean hotStreak;
    MiniSeries miniSeries;

    public LeagueEntry(String errorMessage, String leagueId, String queueType, String rank, String summonerId, String summonerName, String tier, int leaguePoints
            , int wins, int losses, boolean veteran, boolean inactive, boolean freshBlood, boolean hotStreak, MiniSeries miniSeries) {
        super(errorMessage);
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.rank = rank;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.tier = tier;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.veteran = veteran;
        this.inactive = inactive;
        this.freshBlood = freshBlood;
        this.hotStreak = hotStreak;
        this.miniSeries = miniSeries;
    }

    @Override
    public String toString() {
        return "LeagueEntry{" +
                "leagueId=" + leagueId +
                ", queueType=" + queueType +
                ", rank=" + rank +
                ", summonerId=" + summonerId +
                ", summonerName=" + summonerName +
                ", tier=" + tier +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", veteran=" + veteran +
                ", inactive=" + inactive +
                ", freshBlood=" + freshBlood +
                ", hotStreak=" + hotStreak +
                ", miniSeries=" + miniSeries +
                "}";
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public String getQueueType() {
        return queueType;
    }

    public MiniSeries getMiniSeries() {
        return miniSeries;
    }

    public void setMiniSeries(MiniSeries miniSeries) {
        this.miniSeries = miniSeries;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }
}
