package com.starter.demo.response;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
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

}
