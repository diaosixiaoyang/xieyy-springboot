package com.xieyy.boot.handler;

import com.xieyy.boot.dto.ResultDTO;
import com.xieyy.boot.exception.XieyyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(value = {"com.xieyy.boot.api"})
public class XieyyExceptionHandler {

    @ExceptionHandler(value = XieyyException.class)
    @ResponseBody
    public ResultDTO handler(Exception e) {
        if (e instanceof XieyyException) {
            XieyyException xieyyException = (XieyyException) e;
            return ResultDTO.fail(xieyyException);
        }
        return ResultDTO.fail(e.getMessage());
    }


}
