package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Frame implements Serializable {
    ArrayList<Event> events;
    int timestamp;

    public Frame(ArrayList<Event> events, int timestamp) {
        this.events = events;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "events=" + events +
                ", timestamp=" + timestamp +
                '}';
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
