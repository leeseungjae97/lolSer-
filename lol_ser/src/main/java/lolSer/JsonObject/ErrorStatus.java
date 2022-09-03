package lolSer.JsonObject;

public class ErrorStatus extends Response{
    public Status status;

    public ErrorStatus(Status status) {
        super(Response.ERROR);
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorStatus{" +
                "status=" + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
