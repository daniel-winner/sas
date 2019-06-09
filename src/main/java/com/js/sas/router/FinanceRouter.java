package com.js.sas.router;

import com.js.sas.handler.FinanceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FinanceRouter {
    @Autowired
    private FinanceHandler financeHandler;

    @Bean
    public RouterFunction<ServerResponse> settlementCustomerSummary() {
        return route(GET("/finance/settlementSummary"), financeHandler::settlementSummary)
                .andRoute(GET("/finance/settlementSummaryColumns"), financeHandler::settlementSummaryColumns);
    }

}
