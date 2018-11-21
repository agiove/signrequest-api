package it.ivert.signrequest.exc;

public class SRApiRequestException extends Exception {

    private int httpCode;
    private String jsonBody;

    public SRApiRequestException() {
        super();
    }

    public SRApiRequestException(int httpCode, String msg, String jsonBody) {
        super(msg + " - " + jsonBody);
        this.httpCode = httpCode;
        this.jsonBody = jsonBody;
    }

    public SRApiRequestException(int httpCode, String msg) {
        super(msg);
        this.httpCode = httpCode;
    }

    public SRApiRequestException(String msg) {
        super(msg);
    }

    public SRApiRequestException(Throwable e) {
        super(e);
    }

    public int getHttpCode() {
        return httpCode;
    }
}
