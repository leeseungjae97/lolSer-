package lolSer.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Info implements Serializable {
    int frameInterval;
    ArrayList<Frame> frames;

    public Info(int frameInterval, ArrayList<Frame> frames) {
        this.frameInterval = frameInterval;
        this.frames = frames;
    }

    @Override
    public String toString() {
        return "Info{" +
                "frameInterval=" + frameInterval +
                ", frames=" + frames +
                '}';
    }

    public int getFrameInterval() {
        return frameInterval;
    }

    public void setFrameInterval(int frameInterval) {
        this.frameInterval = frameInterval;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }
}
