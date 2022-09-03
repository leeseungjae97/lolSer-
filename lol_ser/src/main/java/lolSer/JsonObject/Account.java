package lolSer.JsonObject;

import static lolSer.util.StringResources.TAG_LINE;

public class Account extends Response {
    public String puuid;
    public String gameName;
    public String tagLine;

    public Account(String puuid, String gameName) {
        super(Response.ERROR);
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = TAG_LINE;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }
}
