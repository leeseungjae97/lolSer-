package lolSer.JsonObject;

import lolSer.util.SavePref;
import java.io.Serializable;
import java.util.ArrayList;

public class CurrentGameInfo extends Response implements Serializable {

    long gameId;
    String gameType;
    long mapId;
    long gameLength;
    String platformId;
    String gameMode;
    long gameQueueConfigId;
    ArrayList<CurrentGameParticipants> participants;

    public CurrentGameInfo(long gameId, String gameType, long mapId, long gameLength, String platformId,
                           String gameMode, long gameQueueConfigId, ArrayList<CurrentGameParticipants> participants) {
        super(Response.ERROR);
        this.gameId = gameId;
        this.gameType = gameType;
        this.mapId = mapId;
        this.gameLength = gameLength;
        this.platformId = platformId;
        this.gameMode = gameMode;
        this.gameQueueConfigId = gameQueueConfigId;
        this.participants = participants;
    }
    public void checkUserTeamId() {
        for (int i = 0; i < participants.size(); i++) {
            if(participants.get(i).getSummonerName().equals(SavePref.getGameName())) {
                SavePref.setTeamId(participants.get(i).getTeamId());
            }
        }
    }

    @Override
    public String toString() {
        return "CurrentGameInfo{" +
                "gameId=" + gameId +
                ", gameType=" + gameType +
                ", mapId=" + mapId +
                ", gameLength=" + gameLength +
                ", platformId=" + platformId +
                ", gameMode=" + gameMode +
                ", gameQueueConfigId=" + gameQueueConfigId +
                ", participants=" + participants +
                "}";
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public long getGameLength() {
        return gameLength;
    }

    public void setGameLength(long gameLength) {
        this.gameLength = gameLength;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public long getGameQueueConfigId() {
        return gameQueueConfigId;
    }

    public void setGameQueueConfigId(long gameQueueConfigId) {
        this.gameQueueConfigId = gameQueueConfigId;
    }

    public ArrayList<CurrentGameParticipants> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<CurrentGameParticipants> participants) {
        this.participants = participants;
    }
}
