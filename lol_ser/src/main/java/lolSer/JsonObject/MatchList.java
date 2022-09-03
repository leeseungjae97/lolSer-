package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MatchList extends Response implements Serializable {
    ArrayList<MatchReference> matches;

    public MatchList(ArrayList<MatchReference> matches) {
        super(Response.ERROR);
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "MatchList{" +
                "matches=" + matches +
                '}';
    }

    public ArrayList<MatchReference> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchReference> matches) {
        this.matches = matches;
    }
}
