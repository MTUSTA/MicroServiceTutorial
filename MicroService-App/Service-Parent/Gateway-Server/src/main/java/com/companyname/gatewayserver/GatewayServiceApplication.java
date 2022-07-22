package com.companyname.gatewayserver;

import org.hibernate.validator.cfg.defs.RangeDef;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JAutoConfiguration;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.DispatcherHandler;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@ConditionalOnClass({ DispatcherHandler.class, ReactiveResilience4JAutoConfiguration.class, HystrixCircuitBreakerAutoConfiguration.class })
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("Hesap-Service", r -> r
                        .path("/hesap/**")
                        .filters(f -> f
                                .circuitBreaker(c -> c
                                        .setName("Hesap-Service")
                                        .setFallbackUri("forward:/fb/hesap")
                                )
                                .rewritePath("/(?<segment>.*)", "/$\\{segment}")

                        )
                        .uri("lb://HESAP-SERVICE")
                ).route("Talimat-Service", r -> r
                        .path("/talimat/**")
                        .filters(f -> f
                                .circuitBreaker(c->c
                                        .setName("Talimat-Service")
                                        .setFallbackUri("forward:/fb/talimat"))
                                .rewritePath("/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb://TALIMAT-SERVICE")) .build();
    }

}
