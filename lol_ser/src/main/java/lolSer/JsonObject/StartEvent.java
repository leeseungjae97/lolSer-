package lolSer.JsonObject;

import java.io.Serializable;

public class StartEvent implements Serializable {
    String EventName;

    public StartEvent(String eventName) {
        EventName = eventName;
    }

    @Override
    public String toString() {
        return "StartEvent{" +
                "EventName='" + EventName + '\'' +
                '}';
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }
}
