package lolSer.JsonObject;

import java.io.Serializable;
import java.util.Arrays;

public class LocalCurrentGameInfo extends Response implements Serializable {
    String championName;
    boolean isBot;
    boolean isDead;
    int[] items;
    int level;
    String position;
    String team;

    public LocalCurrentGameInfo(String championName, boolean isBot,
                                boolean isDead, int[] items, int level, String position, String team) {
        super(Response.ERROR);
        this.championName = championName;
        this.isBot = isBot;
        this.isDead = isDead;
        this.items = items;
        this.level = level;
        this.position = position;
        this.team = team;
    }

    @Override
    public String toString() {
        return "LocalCurrentGameInfo{" +
                "championName='" + championName + '\'' +
                ", isBot=" + isBot +
                ", isDead=" + isDead +
                ", items=" + Arrays.toString(items) +
                ", level=" + level +
                ", position='" + position + '\'' +
                ", team='" + team + '\'' +
                '}';
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
