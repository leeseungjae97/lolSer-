package lolSer.JsonObject;

import java.io.Serializable;
import java.util.Arrays;

public class LocalCurrentGameInfos extends Response implements Serializable {
    LocalCurrentGameInfo[] localCurrentGameInfos;

    public LocalCurrentGameInfos(LocalCurrentGameInfo[] localCurrentGameInfos) {
        super(Response.ERROR);
        this.localCurrentGameInfos = localCurrentGameInfos;
    }

    @Override
    public String toString() {
        return "LocalCurrentGameInfos{" +
                "localCurrentGameInfos=" + Arrays.toString(localCurrentGameInfos) +
                '}';
    }

    public LocalCurrentGameInfo[] getLocalCurrentGameInfos() {
        return localCurrentGameInfos;
    }

    public void setLocalCurrentGameInfos(LocalCurrentGameInfo[] localCurrentGameInfos) {
        this.localCurrentGameInfos = localCurrentGameInfos;
    }
}
