package org.pbp.apigateway.filter;

import org.pbp.apigateway.config.RouterValidator;
import org.pbp.apigateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouterValidator validator;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
           if (validator.isSecured.test(exchange.getRequest())) {
               if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                   throw new RuntimeException("Missing authorization header!");
               }

               String authHeader = Objects.requireNonNull(exchange.getRequest()
                       .getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
               if (authHeader != null && authHeader.startsWith("Bearer ")) {
                   authHeader = authHeader.substring(7);
               }

               try {
                    jwtUtils.validateToken(authHeader);
               } catch (Exception e) {
                   System.out.println("Invalid access...!");
                   throw new RuntimeException("UNAUTHORIZED access to application!");
               }
           }
           return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
