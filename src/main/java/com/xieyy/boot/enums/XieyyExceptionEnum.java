package com.xieyy.boot.enums;

public enum XieyyExceptionEnum {


    PARAMS_ERROR("001", "参数错误！"),
    PARAMS_NOT_NULL("002", "参数不能为空！");

    private String code;
    private String message;

    XieyyExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
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
}
