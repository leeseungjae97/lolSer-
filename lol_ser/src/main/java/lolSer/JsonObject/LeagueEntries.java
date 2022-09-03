package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class LeagueEntries extends Response implements Serializable {
    LeagueEntry[] leagueEntries;

    public LeagueEntries(LeagueEntry[] leagueEntries) {
        super(Response.ERROR);
        this.leagueEntries = leagueEntries;
    }

    @Override
    public String toString() {
        return "LeagueEntries{" +
                "leagueEntries=" + Arrays.toString(leagueEntries) +
                '}';
    }

    public LeagueEntry[] getLeagueEntries() {
        return leagueEntries;
    }

    public void setLeagueEntries(LeagueEntry[] leagueEntries) {
        this.leagueEntries = leagueEntries;
    }
}
