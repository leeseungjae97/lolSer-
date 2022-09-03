package lolSer.JsonObject;

public class Summoner extends Response {
    public String accountId;
    public int profileIconId;
    public double revisionDate;
    public String name;
    public String id;
    public String puuid;
    public double summonerLevel;

    public Summoner(String accountId, int profileIconId, double revisionDate, String name, String id, String puuid, double summonerLevel) {
        super(Response.ERROR);
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.id = id;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "accountId=" + accountId +
                ", profileIconId=" + profileIconId +
                ", revisionDate=" + revisionDate +
                ", name=" + name +
                ", id=" + id +
                ", puuid=" + puuid +
                ", summonerLevel=" + summonerLevel +
                '}';
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public double getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(double revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public double getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(double summonerLevel) {
        this.summonerLevel = summonerLevel;
    }
}
