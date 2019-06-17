package com.js.sas.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonUtils
 * @Description
 * @Author zc
 * @Date 2019/6/12 8:00
 **/
public class JsonUtils {

    /**
     * 把ResultSet集合转换成JsonArray数组，包含列名数据。
     *
     * @param rs
     * @return 符合Bootstrap Table格式的数据
     * @throws SQLException
     */
    public static Map<String, Object> formatRsToMap(ResultSet rs, boolean withColumns) throws SQLException {

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        ResultSetMetaData rsmd = rs.getMetaData();
        // 数据列数
        int count = rsmd.getColumnCount();

        if(withColumns) {
            // 列名数据
            List<Map<String, String>> columnsList = new ArrayList<Map<String, String>>();
            for (int i = 1; i <= count; i++) {
                Map<String, String> columnsMap = new LinkedHashMap<String, String>();
                columnsMap.put("field", rsmd.getColumnName(i));
                columnsMap.put("title", rsmd.getColumnName(i));
                columnsMap.put("align", "center");
                columnsMap.put("valign", "middle");
                columnsList.add(columnsMap);
            }
            resultMap.put("columns", columnsList);
        }

        JSONArray array = new JSONArray();
        // 数据
        List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
        while (rs.next()) {
            Map<String, String> rowsMap = new LinkedHashMap<String, String>();
            for (int i = 1; i <= count; i++) {
                rowsMap.put(rsmd.getColumnName(i), rs.getString(i));
            }
            rowsList.add(rowsMap);
        }
        resultMap.put("rows", rowsList);

        return resultMap;
    }

}
