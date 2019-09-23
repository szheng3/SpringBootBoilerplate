package com.starter.demo.configuration.exception;

import com.starter.demo.configuration.exception.runtime.ResourceMissingException;
import com.starter.demo.configuration.exception.runtime.UnAuthorizedException;
import com.starter.demo.response.ErrorResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    private static ErrorResponse errorResponseHandler(int statusCode, Exception ex, String message, ServerHttpRequest serverHttpRequest) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        String stackTrace = writer.toString();

        stackTrace = stackTrace.replace("\t", "        ");

        log.debug(stackTrace);
        return new ErrorResponse(statusCode,
                message,
                stackTrace.split("\n"),
                serverHttpRequest.getPath().toString()
        );
    }

    @ExceptionHandler(value = {ResourceMissingException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleConflict(RuntimeException ex, ServerHttpRequest serverHttpRequest) {
        return errorResponseHandler(HttpStatus.BAD_REQUEST.value(), ex, ex.getMessage(), serverHttpRequest);
    }

    @ExceptionHandler(value = {UnAuthorizedException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    protected ErrorResponse handleUnAuthorized(RuntimeException ex,ServerHttpRequest serverHttpRequest) {
        return errorResponseHandler(HttpStatus.BAD_REQUEST.value(), ex, ex.getMessage(), serverHttpRequest);
    }
}
