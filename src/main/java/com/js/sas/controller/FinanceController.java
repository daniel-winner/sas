package com.js.sas.controller;

import com.alibaba.fastjson.JSONObject;
import com.js.sas.utils.JsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.sql.DataSource;
import java.sql.*;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始日期", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endDate", value = "结束日期", required = false, paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "offset", value = "起始序号", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页数量", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "sort", value = "排序字段", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sortOrder", value = "排序规则", required = false, paramType = "query", dataType = "string")
    })
    @PostMapping("/settlementSummary")
    public Mono<JSONObject> settlementSummary(@RequestBody Map<String, String> params) {

        String offset = Optional.ofNullable(params.get("offset")).orElse("0");
        String limit = Optional.ofNullable(params.get("limit")).orElse("10");
        String sort = Optional.ofNullable(params.get("sort")).orElse("name");
        String sortOrder = Optional.ofNullable(params.get("sortOrder")).orElse("asc");

        Connection con = null;
        PreparedStatement statement = null;
        ResultSet res = null;
        JSONObject result = null;
        try {
            con = dataSource.getConnection();
            CallableStatement c = con.prepareCall("{call PROC_SettlementSummary_OnLineOffLine(?,?,?,?,?,?,?)}");
            c.setString("startDate", "2019-04-28");
            c.setString("endDate", "2019-05-27");
            c.setInt("offsetNum", Integer.parseInt(offset));
            c.setInt("limitNum", Integer.parseInt(limit));
            c.setString("sort", sort);
            c.setString("sortOrder", sortOrder);

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
                if (res != null) {
                    res.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return Mono.just(result);
    }

}
