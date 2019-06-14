package com.js.sas.controller;

import com.alibaba.fastjson.JSONObject;
import com.js.sas.utils.CommonUtils;
import com.js.sas.utils.ExportExcelUtils;
import com.js.sas.utils.JsonUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName FinanceController
 * @Description 财务Controller
 * @Author zc
 * @Date 2019/6/11 12:47
 **/
@RestController
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private DataSource dataSource;

    @ApiOperation(value = "结算客户对账单汇总（线上、线下）", notes = "数据来源：用友；数据截止日期：昨天")
    @PostMapping("/settlementSummary")
    public Mono<JSONObject> settlementSummary(@ApiParam @RequestBody Map<String, String> params) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        String name = Optional.ofNullable(params.get("name")).orElse("");
        String channel = Optional.ofNullable(params.get("channel")).orElse("");

        String startDate = Optional.ofNullable(CommonUtils.isValidDate(params.get("startDate"))).orElse("2018-12-28");
        String endDate = Optional.ofNullable(CommonUtils.isValidDate(params.get("endDate"))).orElse(today);
        int offset = CommonUtils.toInt(params.get("offset")).orElse(0);
        int limit = CommonUtils.toInt(params.get("limit")).orElse(10);
        String sort = Optional.ofNullable(params.get("sort")).orElse("name");
        String sortOrder = Optional.ofNullable(params.get("sortOrder")).orElse("asc");

        return Mono.just(getDatas(name, channel, startDate, endDate, offset, limit, sort, sortOrder));
    }


    @GetMapping("/settlementSummary/download/excel")
    public Mono<Void> download(@RequestBody Map<String, String> params, ServerHttpResponse response) throws IOException {

        return new ExportExcelUtils().createExcel(response, "结算客户汇总（线上、线下）.xlsx");

    }

    private JSONObject getDatas(String name, String channel, String startDate, String endDate, int offset, int limit, String sort, String sortOrder) {
        JSONObject result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            CallableStatement c = con.prepareCall("{call PROC_SettlementSummary_OnLineOffLine(?,?,?,?,?,?,?,?,?)}");
            c.setString("settlementName", name.trim());
            c.setString("channel", channel);
            c.setString("startDate", startDate);
            c.setString("endDate", endDate);
            c.setInt("offsetNum", offset);
            c.setInt("limitNum", limit);
            c.setString("sort", sort);
            c.setString("sortOrder", sortOrder);

            rs = c.executeQuery();

            result = JsonUtils.formatRsToJsonArray(rs);
            result.put("total", c.getString("totalNum"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
