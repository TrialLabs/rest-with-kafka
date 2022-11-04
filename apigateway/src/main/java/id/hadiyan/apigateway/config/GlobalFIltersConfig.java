package id.hadiyan.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFIltersConfig {
    final Logger log = LoggerFactory.getLogger(GlobalFIltersConfig.class);

    @Bean
    public GlobalFilter postGlobalPreFilter() {
        log.info("GlobalPreFilter");
        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            log.info("server response is {}", response.getStatusCode());

        }));
    }

    private class Test {
        private Object data;
        Test(Object data){
            this.data = data;
        }
    }
}
