package lolSer.JsonObject;

import java.io.Serializable;

public class Event implements Serializable {
    int creatorId;
    long realTimestamp;
    long timestamp;
    int participantId;
    String type;
    String wardType;
    int itemId;

    public Event(int creatorId, long realTimestamp, long timestamp, int participantId, String type, String wardType, int itemId) {
        this.creatorId = creatorId;
        this.realTimestamp = realTimestamp;
        this.timestamp = timestamp;
        this.participantId = participantId;
        this.type = type;
        this.wardType = wardType;
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "creatorId=" + creatorId +
                ", realTimestamp=" + realTimestamp +
                ", timestamp=" + timestamp +
                ", participantId=" + participantId +
                ", type='" + type + '\'' +
                ", wardType='" + wardType + '\'' +
                ", itemId=" + itemId +
                '}';
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public long getRealTimestamp() {
        return realTimestamp;
    }

    public void setRealTimestamp(long realTimestamp) {
        this.realTimestamp = realTimestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
