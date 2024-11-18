package com.dieva.gateway.mcsv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class SampleGlobalFilter implements GlobalFilter {

    private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Sample Global Filter: Executing... PRE before request");
        return chain.filter(exchange).then(Mono.fromRunnable(() ->{
            logger.info("Sample Global Filter: Executing... POST request - Response");
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color","blue").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        } ));
    }
}
