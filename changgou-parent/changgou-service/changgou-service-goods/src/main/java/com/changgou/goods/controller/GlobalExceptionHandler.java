package com.changgou.goods.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/20 16:07
 * @description 标题
 * @package com.changgou.goods.controller
 */
@ControllerAdvice//标识 该类是一个异常处理类 交给spring容器的
//@RestControllerAdvice
public class GlobalExceptionHandler {

    //写一个方法  用于 当发生了异常由该方法来捕获并处理 返回符合需求的响应值
    // @ExceptionHandler(value= Exception.class)  修饰方法 标识该方法需要处理异常的信息
    //  Exception.class 只要发生了 exeption的子类的异常，都会被捕获
    //只能针对 有@requestMapping--》getmpping postmping注解修饰的方法
    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public Result handlerException(Exception e){
        //捕获异常
        e.printStackTrace();
        //处理结果
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }

    // 商品找不到 有一个异常类  ItemNotFOUNDException
   /* @ExceptionHandler(value= ItemNotFOUNDException.class)
    @ResponseBody
    public Result handlerException(Exception e){
        //捕获异常
        e.printStackTrace();
        //处理结果
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }*/
    // 订单不到 有一个异常类  OrderNotFOUNDException

}
