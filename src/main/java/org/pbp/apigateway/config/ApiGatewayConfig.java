package org.pbp.apigateway.config;

import org.pbp.apigateway.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r
                        .path("/auth-service/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                        .uri("https://auth-service-production-e4c9.up.railway.app")
                )
                .route("product-service", r -> r
                        .path("/product-service/**")
                        .uri("https://product-service-production-25bc.up.railway.app")
                )
                .route("order-service", r -> r
                        .path("/order-service/**")
                        .uri("https://order-service-production-5b8c.up.railway.app")
                )
                .route("table-service", r -> r
                        .path("/table-service/**")
                        .uri("https://table-service.onrender.com")
                )
                .route("review-service", r -> r
                        .path("/review-service/**")
                        .uri("https://review-service.onrender.com")
                )
                .route("websocket", r -> r
                        .path("/websocket/**")
                        .uri("https://notification-service-8vz7.onrender.com/websocket")
                )
                .build();
    }
}
