package com.js.sas.utils;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description 接口返回结果类
 * @Author zc
 * @Date 2019/6/13 10:40
 **/
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    // 状态码
    private String code;
    // 状态描述
    private String message;
    // 数据
    private Object data;

    public Result() {

    }

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                ", data:" + data +
                '}';
    }
}
