package com.js.sas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @ClassName CommonUtils
 * @Description
 * @Author zc
 * @Date 2019/6/12 15:07
 **/
public class CommonUtils {

    /**
     * 字符串转换为Integer，异常则返回空。
     *
     * @param str
     * @return Optional<Integer>
     */
    public static Optional<Integer> toInt(String str) {
        try {
            return Optional.ofNullable(Integer.parseInt(str));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * 判断字符串是否为yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static String isValidDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            return null;
        }
        return str;
    }

}
