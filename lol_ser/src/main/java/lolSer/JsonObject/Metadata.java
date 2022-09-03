package lolSer.JsonObject;

import java.io.Serializable;
import java.util.Arrays;

public class Metadata implements Serializable {
    String dataVersion;
    String matchId;
    String[] participants;
    int participantsId;

    public Metadata(String dataVersion, String matchId, String[] participants) {
        this.dataVersion = dataVersion;
        this.matchId = matchId;
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "dataVersion='" + dataVersion + '\'' +
                ", matchId='" + matchId + '\'' +
                ", participants=" + Arrays.toString(participants) +
                ", participantsId=" + participantsId +
                '}';
    }

    public int getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(String puuid) {
        for (int i = 0; i < participants.length; i++) {
            if(participants[i].equals(puuid)) {
                this.participantsId = i;
            }

        }
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
