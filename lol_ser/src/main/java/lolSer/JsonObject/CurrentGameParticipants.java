package lolSer.JsonObject;

import java.io.Serializable;

public class CurrentGameParticipants implements Serializable {
    int championId;
    long profileIconId;
    long teamId;
    String summonerName;
    String summonerId;

    public CurrentGameParticipants(int championId, long profileIconId, long teamId,
                                   String summonerName, String summonerId) {
        this.championId = championId;
        this.profileIconId = profileIconId;
        this.teamId = teamId;
        this.summonerName = summonerName;
        this.summonerId = summonerId;
    }

    @Override
    public String toString() {
        return "CurrentGameParticipants{" +
                "championId=" + championId +
                ", profileIconId=" + profileIconId +
                ", teamId=" + teamId +
                ", summonerName='" + summonerName + '\'' +
                ", summonerId='" + summonerId + '\'' +
                '}';
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public long getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(long profileIconId) {
        this.profileIconId = profileIconId;
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
}
