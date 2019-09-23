package com.starter.demo.configuration.exception;

import com.starter.demo.configuration.exception.runtime.ResourceMissingException;
import com.starter.demo.configuration.exception.runtime.UnAuthorizedException;
import com.starter.demo.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
//    private static ErrorResponse errorResponseHandler(int statusCode, Exception ex, String message) {
//        StringWriter writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
//        printWriter.flush();
//        String stackTrace = writer.toString();
//
//        stackTrace = stackTrace.replace("\t", "        ");
//
//        return new ErrorResponse(statusCode,
//                message,
//                stackTrace.split("\n"),
//                request.getServletPath());
//    }
    @ExceptionHandler(value = {ResourceMissingException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleConflict(RuntimeException ex) {
        return new ErrorResponse();
    }

    @ExceptionHandler(value = {UnAuthorizedException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorResponse handleUnAuthorized(RuntimeException ex) {
        return new ErrorResponse();
    }
}
