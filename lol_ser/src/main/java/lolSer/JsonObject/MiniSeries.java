package lolSer.JsonObject;

import java.io.Serializable;

public class MiniSeries implements Serializable {
    int wins;
    int losses;
    String progress;
    int target;

    public MiniSeries(int wins, int losses, String progress, int target) {
        this.wins = wins;
        this.losses = losses;
        this.progress = progress;
        this.target = target;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
