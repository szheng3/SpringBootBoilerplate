package com.starter.demo.configuration.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

public interface LogMessageFormatter {

    String format(ServerHttpRequest request, ServerHttpResponse response, byte[] payload);

}
