package com.js.sas.router;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class BaseRouter {

    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/templates/index.html") final Resource html) {
        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).syncBody(html));
    }

    @Bean
    public RouterFunction<ServerResponse> settlementCustomerSummaryRouter(@Value("classpath:/templates/pages/finance/settlementSummary.html") final Resource html) {
        return route(GET("/settlementCustomerSummary"), request -> ok().contentType(MediaType.TEXT_HTML).syncBody(html));
    }

}
