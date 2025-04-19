package com.concrete.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RefreshScope  // Enables runtime config refresh
@Configuration
public class GatewayConfig  {
	
	private final JwtAuthGatewayFilterFactory jwtAuthFilter;

	@Autowired
    public GatewayConfig(JwtAuthGatewayFilterFactory jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("userservice", r -> r.path("/users/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                          .uri("lb://USERSERVICE"))
                .route("orderservice", r -> r.path("/Orders/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                        .uri("lb://ORDERSERVICE"))
                .route("chatservice", r -> r.path("/chats/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                        .uri("lb://CHATSERVICE"))
                .route("productservice", r -> r.path("/products/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                        .uri("lb://PRODUCTSERVICE"))
                .route("infoservice", r -> r.path("/info/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                        .uri("lb://INFOSERVICE"))
                .route("notificationservice", r -> r.path("/notify/**")
                		 .filters(f -> f.filter(jwtAuthFilter.apply(new JwtAuthGatewayFilterFactory.Config())))
                        .uri("lb://NOTIFICATIONSERVICE"))
                .build();
    }
}