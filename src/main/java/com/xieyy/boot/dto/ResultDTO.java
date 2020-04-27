package com.xieyy.boot.dto;

import com.alibaba.fastjson.JSON;
import com.xieyy.boot.enums.XieyyExceptionEnum;
import com.xieyy.boot.exception.XieyyException;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = -4036822491938151141L;

    private static final String SUCCESS_CODE = "200";
    private static final String FAIL_CODE = "500";

    private String code;
    private String message;
    private Object data;

    private ResultDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultDTO(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    private ResultDTO() {
    }

    public static ResultDTO success(Object data) {
        return new ResultDTO(SUCCESS_CODE, data);
    }

    public static ResultDTO fail(String code, String message) {
        return new ResultDTO(code, message);
    }

    public static ResultDTO fail(String message) {
        return fail(FAIL_CODE, message);
    }

    public static ResultDTO fail(XieyyExceptionEnum xieyyExceptionEnum) {
        return fail(xieyyExceptionEnum.getCode(), xieyyExceptionEnum.getMessage());
    }

    public static ResultDTO fail(XieyyException xieyyException) {
        return fail(xieyyException.getCode(), xieyyException.getMessage());
    }

    public static ResultDTO success() {
        return new ResultDTO(SUCCESS_CODE, null);
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
