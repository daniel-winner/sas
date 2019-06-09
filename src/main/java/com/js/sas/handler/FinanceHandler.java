package com.js.sas.handler;

import com.alibaba.fastjson.JSONObject;
import com.js.sas.Utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class FinanceHandler {

    @Autowired
    private DataSource dataSource;

    public Mono<ServerResponse> settlementSummary(ServerRequest serverRequest) {

        Optional<String> limit = serverRequest.queryParam("limit");
        Optional<String> offset = serverRequest.queryParam("offset");
        Optional<String> page = serverRequest.queryParam("page");
        Optional<String> sort = serverRequest.queryParam("sort");
        Optional<String> sortOrder = serverRequest.queryParam("sortOrder");

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        JSONObject result = null;
        try {
            con =  dataSource.getConnection();
            CallableStatement c = con.prepareCall("{call PROC_SettlementSummary_OnLineOffLine(?,?,?,?,?)}");
            c.setString("startDate", "2019-04-28");
            c.setString("endDate", "2019-05-27");
            c.setInt("offsetNum", Integer.parseInt(offset.orElse("0")));
            c.setInt("limitNum", Integer.parseInt(limit.orElse("0")));

            ResultSet rs = c.executeQuery();

            try {
                result = JsonUtil.formatRsToJsonArray(rs);
                result.put("total", c.getString("totalNum"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) res.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(Mono.just(result), JSONObject.class);
    }


}
