package com.example.controller;

import com.example.entity.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    //默认异常处理页面
    public static final String DEFAUL_ERROR_VIEW  = "error/error.html";

    /**
     * 默认异常处理方法,返回异常请求路径和异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaulErrorHandler(HttpServletRequest request,Exception e) throws  Exception{

        ModelAndView mav = new ModelAndView();
        //异常信息
        mav.addObject("exception",e);
        //异常请求路径
        mav.addObject("url","请求路径：" + request.getRequestURI());
        //返回异常处理页面
        mav.setViewName(DEFAUL_ERROR_VIEW);
        return mav;
    }

    /**
     * @ExceptionHandler 匹配抛出自定义的异常类型 MyException
     * ErrorInfo<String> 为自定义的一个数据封装类，用于返回的json数据
     * 如果返回的是json格式，需要加上@ResponsBody
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request,MyException e) throws Exception{

        ErrorInfo<String> error = new ErrorInfo<>();
        error.setCode(ErrorInfo.ERROR);
        error.setMessage(e.getMessage());
        error.setUrl(request.getRequestURI());
        error.setData("未知错误");
        return error;
    }

}

