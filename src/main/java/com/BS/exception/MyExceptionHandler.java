package com.BS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
<<<<<<< HEAD
 * 
=======
 *  
>>>>>>> branch 'master' of https://github.com/dingxiaowei1995/bishe-dw.git
 * @Date: 2018/8/8 15:53
 * Describe:
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(MyException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("message", ex.getMessage());
        return result;
    }

}
