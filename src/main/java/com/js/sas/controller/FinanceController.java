package com.js.sas.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.js.sas.dto.SettlementSummaryDTO;
import com.js.sas.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import springfox.documentation.annotations.ApiIgnore;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @PostMapping(value = "/settlementSummary")
    public Mono<Map<String, Object>> settlementSummary(@RequestBody SettlementSummaryDTO settlementSummasryDTO) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//        String name = Optional.ofNullable(params.get("name")).orElse("");
//        String channel = Optional.ofNullable(params.get("channel")).orElse("");
        String startDate = Optional.ofNullable(CommonUtils.isValidDate(settlementSummasryDTO.getStartDate())).orElse("2018-12-28");
        String endDate = Optional.ofNullable(CommonUtils.isValidDate(settlementSummasryDTO.getEndDate())).orElse(today);
//        int offset = CommonUtils.toInt(params.get("offset")).orElse(0);
//        int limit = CommonUtils.toInt(params.get("limit")).orElse(10);
        String sort = Optional.ofNullable(settlementSummasryDTO.getSort()).orElse("name");
        String sortOrder = Optional.ofNullable(settlementSummasryDTO.getSortOrder()).orElse("asc");

        return Mono.just(getDatas(false, settlementSummasryDTO.getName(),
                settlementSummasryDTO.getChannel(),
                startDate,
                endDate,
                settlementSummasryDTO.getOffset(),
                settlementSummasryDTO.getLimit(),
                sort,
                sortOrder));
    }

    @ApiIgnore
    @PostMapping("/settlementSummary/download/excel")
    public Mono<Void> download(ServerHttpResponse response) throws IOException {
        List<String> columnNameList = new ArrayList<String>();

        columnNameList.add("序号");
        columnNameList.add("编码");
        columnNameList.add("结算客户");
        columnNameList.add("来源");
        columnNameList.add("发货金额");
        columnNameList.add("收款金额");
        columnNameList.add("应收款");
        columnNameList.add("开票金额");
        columnNameList.add("发票结余");

        Map<String, Object> result = getDatas(true, "",
                "",
                "2018-12-28",
                "2019-07-01",
                0,
                100000,
                "name",
                "asc");

        return new ExportExcelUtils().createExcel(response, "结算客户汇总（线上、线下）.xlsx", "结算客户汇总（线上、线下）", columnNameList, (List<Map<String, String>>) result.get("rows"));
    }

    private Map<String, Object> getDatas(boolean withColumns, String name, String channel, String startDate, String endDate, int offset, int limit, String sort, String sortOrder) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
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

            result = JsonUtils.formatRsToMap(rs, withColumns);

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
