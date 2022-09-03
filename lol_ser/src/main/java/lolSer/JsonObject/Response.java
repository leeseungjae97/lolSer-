package lolSer.JsonObject;

import java.io.Serializable;

public class Response implements Serializable {
    public final static String ERROR = " ERROR";
    private String getErrorMessage;

    public Response(String errorMessage) {
        this.getErrorMessage = errorMessage;
    }

}
