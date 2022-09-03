package lolSer.JsonObject;

import java.util.ArrayList;

public class MatchTotalInfo {
    ArrayList<MatchTimeLine> mtlAl;
    ArrayList<Match> mDAl;

    public MatchTotalInfo(ArrayList<MatchTimeLine> mtlAl, ArrayList<Match> mDAl) {
        this.mtlAl = mtlAl;
        this.mDAl = mDAl;
    }

    @Override
    public String toString() {
        return "MatchTotalInfo{" +
                "mtlAl=" + mtlAl +
                ", mDAl=" + mDAl +
                '}';
    }

    public ArrayList<MatchTimeLine> getMtlAl() {
        return mtlAl;
    }

    public void setMtlAl(ArrayList<MatchTimeLine> mtlAl) {
        this.mtlAl = mtlAl;
    }

    public ArrayList<Match> getmDAl() {
        return mDAl;
    }

    public void setmDAl(ArrayList<Match> mDAl) {
        this.mDAl = mDAl;
    }
}
