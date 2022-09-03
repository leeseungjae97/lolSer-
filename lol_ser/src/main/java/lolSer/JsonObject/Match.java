package lolSer.JsonObject;

import java.io.Serializable;

public class Match extends Response implements Serializable {
    Metadata metadata;
    MatchInfo info;

    public Match(Metadata metadata, MatchInfo info) {
        super(Response.ERROR);
        this.metadata = metadata;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Match{" +
                "metadata=" + metadata +
                ", info=" + info +
                '}';
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public MatchInfo getInfo() {
        return info;
    }

    public void setInfo(MatchInfo info) {
        this.info = info;
    }
}
