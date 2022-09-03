package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MatchInfo implements Serializable {
    long gameCreation;
    long gameDuration;
    long gameId;
    String gameMode;
    String gameName;
    long gameStartTimestamp;
    String gameType;
    String gameVersion;
    int mapId;
    ArrayList<MatchParticipant> participants;

    public MatchInfo(long gameCreation, long gameDuration, long gameId, String gameMode, String gameName
            , long gameStartTimestamp, String gameType, String gameVersion, int mapId, ArrayList<MatchParticipant> participants) {
        this.gameCreation = gameCreation;
        this.gameDuration = gameDuration;
        this.gameId = gameId;
        this.gameMode = gameMode;
        this.gameName = gameName;
        this.gameStartTimestamp = gameStartTimestamp;
        this.gameType = gameType;
        this.gameVersion = gameVersion;
        this.mapId = mapId;
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "gameCreation=" + gameCreation +
                ", gameDuration=" + gameDuration +
                ", gameId=" + gameId +
                ", gameMode='" + gameMode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameStartTimestamp=" + gameStartTimestamp +
                ", gameType='" + gameType + '\'' +
                ", gameVersion='" + gameVersion + '\'' +
                ", mapId=" + mapId +
                ", participants=" + participants +
                '}';
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public long getGameStartTimestamp() {
        return gameStartTimestamp;
    }

    public void setGameStartTimestamp(long gameStartTimestamp) {
        this.gameStartTimestamp = gameStartTimestamp;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public ArrayList<MatchParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<MatchParticipant> participants) {
        this.participants = participants;
    }
}
