package id.hadiyan.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


public class SpringCloudConfig {

    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/product/**")
                        .uri("http://localhost:7070/"))
                .route(r -> r.path("/consumer/**")
                        .uri("http://localhost:7071/"))

                .build();
    }
}
