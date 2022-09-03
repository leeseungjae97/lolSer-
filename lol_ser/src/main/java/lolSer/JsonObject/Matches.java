package lolSer.JsonObject;

import java.io.Serializable;

public class Matches extends Response implements Serializable {
    String[] matches;

    public Matches(String[] matches) {
        super(Response.ERROR);
        this.matches = matches;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < matches.length; i++) {
            st.append(matches[i]).append("\n");
        }
        return st.toString();
    }

    public String[] getMatches() {
        return matches;
    }

    public void setMatches(String[] matches) {
        this.matches = matches;
    }
}
