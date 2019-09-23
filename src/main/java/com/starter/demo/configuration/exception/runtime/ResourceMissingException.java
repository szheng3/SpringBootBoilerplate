package com.starter.demo.configuration.exception.runtime;

import com.starter.demo.configuration.exception.RootException;

public class ResourceMissingException extends RootException {


    public ResourceMissingException() {
        super("No Resource ");
    }

    public ResourceMissingException(String message) {
        super(message);
    }


}


