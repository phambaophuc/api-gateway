package org.pbp.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r
                        .path("/product-service/**")
                        .uri("https://product-service-0093.onrender.com")
                )
                .route("order-service", r -> r
                        .path("/order-service/**")
                        .uri("https://order-service-y3mg.onrender.com")
                )
                .route("table-service", r -> r
                        .path("/table-service/**")
                        .uri("https://table-service.onrender.com")
                )
                .route("review-service", r -> r
                        .path("/review-service/**")
                        .uri("lb://review-service")
                )
                .route("websocket", r -> r
                        .path("/websocket/**")
                        .uri("lb://notification-service/websocket")
                )
                .build();
    }
}
