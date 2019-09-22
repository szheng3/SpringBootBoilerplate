//package com.starter.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private ReactiveAuthenticationManager manager; //custom implementation
//
//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange()
//                .pathMatchers("/", "/swagger-ui.html**","/v2/api-docs").permitAll()
////                .pathMatchers("/role").hasRole("ADMIN")
////                .pathMatchers("/test").access(new HasScope("server")) //custom implementation
//                .anyExchange().authenticated()
//                .and()
//                .httpBasic().disable()
//                .oauth2ResourceServer()
//                .jwt()
//                .authenticationManager(manager)
//                .and().and()
//                .build();
//    }
//}
