package id.hadiyan.apigateway.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewaySampleApplication {

    final Logger log = LoggerFactory.getLogger(GatewaySampleApplication.class);

    @GetMapping("/asdf")
    public Mono<ResponseEntity<Object>> proxy(ProxyExchange<Object> proxy, String object) throws Exception {
        log.info("param = {}", object);
        return proxy.uri("http://localhost:7070/product").get();
    }
}
