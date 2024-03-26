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
                        .uri("https://product-service-production-25bc.up.railway.app")
                )
                .route("order-service", r -> r
                        .path("/order-service/**")
                        .uri("https://order-service-production-5b8c.up.railway.app")
                )
                .route("table-service", r -> r
                        .path("/table-service/**")
                        .uri("https://table-service-production.up.railway.app")
                )
                .route("review-service", r -> r
                        .path("/review-service/**")
                        .uri("lb://review-service")
                )
                .route("websocket", r -> r
                        .path("/websocket/**")
                        .uri("https://notification-service-y6a7.onrender.com/websocket")
                )
                .build();
    }
}
