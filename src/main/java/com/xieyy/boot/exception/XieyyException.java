package com.xieyy.boot.exception;

import com.xieyy.boot.enums.XieyyExceptionEnum;
import lombok.Data;

@Data
public class XieyyException extends RuntimeException {

    private String code;
    private String message;

    public XieyyException(XieyyExceptionEnum xieyyExceptionEnum) {
        this.code = xieyyExceptionEnum.getCode();
        this.message = xieyyExceptionEnum.getMessage();
    }

    public XieyyException(String message) {
        super(message);
    }

    public XieyyException(Throwable throwable) {
        super(throwable);
    }

}
