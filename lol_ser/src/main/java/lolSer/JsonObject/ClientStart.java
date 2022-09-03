package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientStart extends Response implements Serializable {
    ArrayList<StartEvent> Events;

    public ClientStart(ArrayList<StartEvent> events) {
        super(Response.ERROR);
        Events = events;
    }

    @Override
    public String toString() {
        return "ClientStart{" +
                "Events=" + Events +
                '}';
    }

    public ArrayList<StartEvent> getEvents() {
        return Events;
    }

    public void setEvents(ArrayList<StartEvent> events) {
        Events = events;
    }
}
