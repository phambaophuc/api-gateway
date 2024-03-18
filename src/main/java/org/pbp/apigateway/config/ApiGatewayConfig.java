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
                        .uri("lb://product-service")
                )
                .route("order-service", r -> r
                        .path("/order-service/**")
                        .uri("lb://order-service")
                )
                .route("table-service", r -> r
                        .path("/table-service/**")
                        .uri("lb://table-service")
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
