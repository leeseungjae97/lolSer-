package lolSer.JsonObject;

import java.io.Serializable;

public class MatchParticipant implements Serializable {
    long championId;
    long teamId;
    String summonerName;
    String summonerId;
    int assists;
    int baronKills;
    String championName;
    int deaths;
    int kills;
    int detectorWardsPlaced;
    int wardsPlaced;
    boolean firstBloodAssist;
    boolean firstBloodKill;
    boolean firstTowerAssist;
    boolean firstTowerKill;
    boolean teamEarlySurrendered;
    String lane;
    int nexusLost;
    String teamPosition;
    String puuid;


    public MatchParticipant(long championId, long teamId, String summonerName, String summonerId, int assists, int baronKills, String championName, int deaths, int kills, int detectorWardsPlaced, int wardsPlaced, boolean firstBloodAssist, boolean firstBloodKill, boolean firstTowerAssist, boolean firstTowerKill, boolean teamEarlySurrendered, String lane, int nexusLost, String teamPosition, String puuid) {
        this.championId = championId;
        this.teamId = teamId;
        this.summonerName = summonerName;
        this.summonerId = summonerId;
        this.assists = assists;
        this.baronKills = baronKills;
        this.championName = championName;
        this.deaths = deaths;
        this.kills = kills;
        this.detectorWardsPlaced = detectorWardsPlaced;
        this.wardsPlaced = wardsPlaced;
        this.firstBloodAssist = firstBloodAssist;
        this.firstBloodKill = firstBloodKill;
        this.firstTowerAssist = firstTowerAssist;
        this.firstTowerKill = firstTowerKill;
        this.teamEarlySurrendered = teamEarlySurrendered;
        this.lane = lane;
        this.nexusLost = nexusLost;
        this.teamPosition = teamPosition;
        this.puuid = puuid;
    }

    @Override
    public String toString() {
        return "MatchParticipant{" +
                "championId=" + championId +
                ", teamId=" + teamId +
                ", summonerName='" + summonerName + '\'' +
                ", summonerId='" + summonerId + '\'' +
                ", assists=" + assists +
                ", baronKills=" + baronKills +
                ", championName='" + championName + '\'' +
                ", deaths=" + deaths +
                ", kills=" + kills +
                ", detectorWardsPlaced=" + detectorWardsPlaced +
                ", wardsPlaced=" + wardsPlaced +
                ", firstBloodAssist=" + firstBloodAssist +
                ", firstBloodKill=" + firstBloodKill +
                ", firstTowerAssist=" + firstTowerAssist +
                ", firstTowerKill=" + firstTowerKill +
                ", teamEarlySurrendered=" + teamEarlySurrendered +
                ", lane='" + lane + '\'' +
                ", nexusLost=" + nexusLost +
                ", teamPosition='" + teamPosition + '\'' +
                ", puuid='" + puuid + '\'' +
                '}';
    }
    public long getChampionId(String summonerId) {
        if(summonerId == this.summonerId) return championId;
        else return -1;
    }

    public long getChampionId() {
        return championId;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDetectorWardsPlaced() {
        return detectorWardsPlaced;
    }

    public void setDetectorWardsPlaced(int detectorWardsPlaced) {
        this.detectorWardsPlaced = detectorWardsPlaced;
    }

    public int getWardsPlaced() {
        return wardsPlaced;
    }

    public void setWardsPlaced(int wardsPlaced) {
        this.wardsPlaced = wardsPlaced;
    }

    public boolean isFirstBloodAssist() {
        return firstBloodAssist;
    }

    public void setFirstBloodAssist(boolean firstBloodAssist) {
        this.firstBloodAssist = firstBloodAssist;
    }

    public boolean isFirstBloodKill() {
        return firstBloodKill;
    }

    public void setFirstBloodKill(boolean firstBloodKill) {
        this.firstBloodKill = firstBloodKill;
    }

    public boolean isFirstTowerAssist() {
        return firstTowerAssist;
    }

    public void setFirstTowerAssist(boolean firstTowerAssist) {
        this.firstTowerAssist = firstTowerAssist;
    }

    public boolean isFirstTowerKill() {
        return firstTowerKill;
    }

    public void setFirstTowerKill(boolean firstTowerKill) {
        this.firstTowerKill = firstTowerKill;
    }

    public boolean isTeamEarlySurrendered() {
        return teamEarlySurrendered;
    }

    public void setTeamEarlySurrendered(boolean teamEarlySurrendered) {
        this.teamEarlySurrendered = teamEarlySurrendered;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getNexusLost() {
        return nexusLost;
    }

    public void setNexusLost(int nexusLost) {
        this.nexusLost = nexusLost;
    }

    public String getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(String teamPosition) {
        this.teamPosition = teamPosition;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }
}
