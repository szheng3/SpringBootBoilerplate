package com.starter.demo.response;

public class ErrorResponse extends Response {

    protected String[] stackTrace;
    protected String error;
    protected long timestamp = System.currentTimeMillis();
    protected String path;

    public ErrorResponse() {
        super.status = 0;
    }

    public ErrorResponse(int status, String error, String[] stackTrace, String path) {
        super.status = 0;
        this.stackTrace = stackTrace;
        this.error = error;
        this.path = path;
    }

    public String[] getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String[] stackTrace) {
        this.stackTrace = stackTrace;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getError() {
        return error;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
