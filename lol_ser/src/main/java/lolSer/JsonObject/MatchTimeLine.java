package lolSer.JsonObject;

import java.io.Serializable;

public class MatchTimeLine extends Response implements Serializable {
    Metadata metadata;
    Info info;
    int enmBranch; //3 ~ 7

    public MatchTimeLine(Metadata metadata, Info info) {
        super(Response.ERROR);
        this.metadata = metadata;
        this.info = info;
    }

    @Override
    public String toString() {
        return "MatchTimeLine{" +
                "metadata=" + metadata +
                ", info=" + info +
                ", enmBranch=" + enmBranch +
                '}';
    }

    public int getEnmBranch() {
        return enmBranch;
    }

    public void setEnmBranch(int enmBranch) {
        this.enmBranch = enmBranch;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
