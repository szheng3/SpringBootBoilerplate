package com.starter.demo.configuration.exception;

public class ResourceMissingException extends RootException {


    public ResourceMissingException() {
        super("No Resource ");
    }

    public ResourceMissingException(String message) {
        super(message);
    }


}


