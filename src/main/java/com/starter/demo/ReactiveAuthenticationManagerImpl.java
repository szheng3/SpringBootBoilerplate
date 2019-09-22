//package com.starter.demo;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.mapstruct.Qualifier;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Component
//public class ReactiveAuthenticationManagerImpl implements ReactiveAuthenticationManager {
//
//    private final ResourceServerProperties sso;
//    private final WebClient.Builder webClient;
//    private final ObjectMapper objectMapper;
//    private AuthoritiesExtractor authoritiesExtractor = new FixedAuthoritiesExtractor();
//
//    public ReactiveAuthenticationManagerImpl(ResourceServerProperties sso,
//            @Qualifier("loadBalancedWebClient") WebClient.Builder webClient, ObjectMapper objectMapper) {
//        this.sso = sso;
//        this.webClient = webClient;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//        return Mono.just(authentication)
//                .cast(BearerTokenAuthenticationToken.class)
//                .flatMap(it -> getMap(it.getToken()))
//                .flatMap(result -> Mono.just(extractAuthentication(result)));
//    }
//
//    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
//        Object principal = getPrincipal(map);
//        OAuth2Request request = getRequest(map);
//        List<GrantedAuthority> authorities = authoritiesExtractor.extractAuthorities(map);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
//        token.setDetails(map);
//        return new OAuth2Authentication(request, token);
//    }
//
//    private Object getPrincipal(Map<String, Object> map) {
//        if (map.containsKey("principal")) {
//            try {
//                //that is the case for user authentication
//                return objectMapper.convertValue(map.get("principal"), UserPrincipal.class);
//            } catch (IllegalArgumentException ex) {
//                //that is the case for client authentication
//                return objectMapper.convertValue(map.get("principal"), String.class);
//            }
//        }
//        return null;
//    }
//
//    @SuppressWarnings({"unchecked"})
//    private OAuth2Request getRequest(Map<String, Object> map) {
//        Map<String, Object> request = (Map<String, Object>) map.get("oauth2Request");
//
//        String clientId = (String) request.get("clientId");
//        Set<String> scope = new LinkedHashSet<>(request.containsKey("scope") ?
//                (Collection<String>) request.get("scope") : Collections.emptySet());
//
//        return new OAuth2Request(null, clientId, null, true, new HashSet<>(scope),
//                null, null, null, null);
//    }
//
//    private Mono<Map<String, Object>> getMap(String accessToken) {
//        String uri = sso.getUserInfoUri();
//        return webClient.build().get()
//                .uri(uri)
//                .accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Bearer " + accessToken)
//                .exchange()
//                .flatMap(it -> it.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {}))
//                .onErrorMap(InvalidTokenException.class, mapper -> new InvalidTokenException("Invalid token: " + accessToken));
//    }
