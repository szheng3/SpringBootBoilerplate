package com.starter.demo.configuration.exception;

public class UnAuthorizedException extends RootException {

    public UnAuthorizedException() {
        super("401!Unauthorized");
    }

    public UnAuthorizedException(String message) {
        super(message);
    }


}
