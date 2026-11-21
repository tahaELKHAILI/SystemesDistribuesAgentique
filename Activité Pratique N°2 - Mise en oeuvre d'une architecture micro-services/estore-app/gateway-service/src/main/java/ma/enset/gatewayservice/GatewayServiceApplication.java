package ma.enset.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


    // Static routing
    RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                    .route("r1", predicateSpec ->
                            predicateSpec.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                    .route("r2",predicateSpec ->
                            predicateSpec.path("/products/**").uri("lb://INVENTORY-SERVICE"))
                    .build();

    }

    // Dynamic routing
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRouting(ReactiveDiscoveryClient reactiveDiscoveryClient,
                                                         DiscoveryLocatorProperties discoveryLocatorProperties){
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, discoveryLocatorProperties);
    }
}
