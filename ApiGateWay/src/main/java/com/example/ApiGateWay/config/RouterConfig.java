package com.example.ApiGateWay.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class RouterConfig {



    @Bean
    public RouteLocator myRouts(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p ->
                        p.path("/api/Authentication/**")
                                .uri("http://localhost:7867/"))
                .route(p ->
                        p.path("/api/Restaurant/**")
                                .uri("http://localhost:7868/")).build();
    }
}
