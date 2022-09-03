package lolSer.JsonObject;

public class MatchReference {
    String role;
    String platformId;
    String lane;
    int season;
    int champion;
    int queue;
    long gameId;
    long timestamp;

    public MatchReference(String role, String platformId, String lane, int season, int champion, int queue, long gameId, long timestamp) {
        this.role = role;
        this.platformId = platformId;
        this.lane = lane;
        this.season = season;
        this.champion = champion;
        this.queue = queue;
        this.gameId = gameId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MatchReference{" +
                "role='" + role + '\'' +
                ", platformId='" + platformId + '\'' +
                ", lane='" + lane + '\'' +
                ", season=" + season +
                ", champion=" + champion +
                ", queue=" + queue +
                ", gameId=" + gameId +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getChampion() {
        return champion;
    }

    public void setChampion(int champion) {
        this.champion = champion;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
