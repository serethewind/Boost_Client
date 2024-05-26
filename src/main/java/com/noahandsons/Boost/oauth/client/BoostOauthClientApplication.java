package com.noahandsons.Boost.oauth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BoostOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoostOauthClientApplication.class, args);
	}


	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder
				.routes()
				.route(rs -> rs.path("/")
						.filters(GatewayFilterSpec::tokenRelay)
						.uri("http://localhost:8081/welcome/demo/")
				)
				.build();
	}
}
