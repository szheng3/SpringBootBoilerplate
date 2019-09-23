package com.starter.demo.configuration.exception.runtime;

import com.starter.demo.configuration.exception.RootException;

public class UnAuthorizedException extends RootException {

    public UnAuthorizedException() {
        super("401!Unauthorized");
    }

    public UnAuthorizedException(String message) {
        super(message);
    }


}
