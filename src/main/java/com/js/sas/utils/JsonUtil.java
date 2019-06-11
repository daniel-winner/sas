package com.js.sas.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JsonUtil {

    /**
     * 把ResultSet集合转换成JsonArray数组，包含列名数据。
     *
     * @param rs
     * @return 符合Bootstrap Table格式的数据
     * @throws SQLException
     */
    public static JSONObject formatRsToJsonArrayWithColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        // 数据列数
        int count = rsmd.getColumnCount();
        // 格式化json
        JSONObject resultObject = new JSONObject();
        // 列名数据
        JSONArray columnsArray = new JSONArray();
        for (int i = 1; i <= count; i++) {
            JSONObject mapOfColValues = new JSONObject();
            mapOfColValues.put("field", rsmd.getColumnName(i));
            mapOfColValues.put("title", rsmd.getColumnName(i));
            mapOfColValues.put("align", "center");
            mapOfColValues.put("valign", "middle");
            columnsArray.add(mapOfColValues);
        }
        resultObject.put("columns", columnsArray);

        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= count; i++) {
                mapOfColValues.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            array.add(mapOfColValues);
        }
        resultObject.put("rows", array);

        return resultObject;
    }

    /**
     * 把ResultSet集合转换成JsonArray数组。
     *
     * @param rs
     * @return 符合Bootstrap Table格式的数据
     * @throws SQLException
     */
    public static JSONObject formatRsToJsonArray(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        // 数据列数
        int count = rsmd.getColumnCount();
        // 格式化json
        JSONObject resultObject = new JSONObject();
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject mapOfColValues = new JSONObject();
            for (int i = 1; i <= count; i++) {
                mapOfColValues.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            array.add(mapOfColValues);
        }
        resultObject.put("rows", array);

        return resultObject;
    }

}
