package com.lan.interceptor;

import com.lan.Ex.BizExc;
import com.lan.bean.enums.CodeEnum;
import com.lan.bean.res.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;
/*
 * @注释:自定义异常处理
 * 指定当前类为指定controller的异常处理器
 * @param:"com.lan.Controllers.ManagerController" 指定当前控制器的全类名
 * @Author: Lan
 */
@RestControllerAdvice
@Slf4j
public class ExAdvice {
    /*
     * @注释:入参异常，MethodArgumentNotValidException
     * MethodArgumentNotValidException--->出现时机:
     * Content-Type:application/json + 后台使用@RequestBody
     *
     * @return: com.lan.bean.res.Result<java.lang.String>
     * @Author: Lan
     * @ExceptionHandler:只明当前要处理的异常类型
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> controllerEx(MethodArgumentNotValidException e){
        log.info("入参校验失败",e);
        /*
         * @注释:通过当前捕获的异常，获取存储异常的信息的类实例
         * @Author: Lan
         */
        BindingResult bindingResult = e.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        /*
         * @注释:获取到没有检验通过的字段的详细内容
         */
        String errMsg = fieldErrors.stream().map(fieldError -> {
            StringBuilder sb = new StringBuilder();
            sb.append("属性:").append(fieldError.getField()).append(",传来的值是:").append(fieldError.getRejectedValue()).append(",校验不通过的原因是：").append(fieldError.getDefaultMessage());
            return sb.toString();
        }).collect(Collectors.joining(";"));


        return Result.buildFailure(errMsg);
    }


    /*
     * @注释:处理BindException类型异常
     * 出现时机： Content-Type ：application/x-www-urlencoded;比如字段名不匹配，字段名不满足条件
     * @Author: Lan
     */
    @ExceptionHandler(BindException.class)
    public Result<String> controllerEx(BindException e){
        log.info("参数绑定异常",e);
        /*
         * @注释:通过当前捕获的异常，获取存储异常的信息的类实例
         * @Author: Lan
         */
        List<FieldError> fieldErrors = e.getFieldErrors();
        /*
         * @注释:获取到没有检验通过的字段的详细内容
         */

        return Result.buildFailure(spliceErrorMsg(fieldErrors));
    }

    /*
     * @注释:抽离方法
     */

    private String spliceErrorMsg(List<FieldError> fieldErrors){

        return fieldErrors.stream().map(fieldError -> {
            StringBuilder sb = new StringBuilder();
            sb.append("属性:").append(fieldError.getField()).append(",传来的值是:").append(fieldError.getRejectedValue()).append(",校验不通过的原因是：").append(fieldError.getDefaultMessage());
            return sb.toString();
        }).collect(Collectors.joining(";"));
    }
    /*
     * @注释:IllegalAccessException
     * @Author: Lan
     */
    @ExceptionHandler(IllegalAccessException.class)
    public Result<String> controllerEx(IllegalAccessException e){
         log.error("参数非法",e);
        return Result.buildFailure(CodeEnum.PARAM_ERR);
    }
    /*
     * @注释:
     * @Author: Lan
     */
    @ExceptionHandler(BadSqlGrammarException.class)
    public Result<String> controllerEx(BadSqlGrammarException e){

        log.error("数据库处理异常",e);
        return Result.buildFailure(CodeEnum.DB_ERR);
    }
    /*
     * @注释:
     * @Author: Lan
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<String> controllerEx(DuplicateKeyException e){

        log.error("唯一性约束校验失败",e);
        return Result.buildFailure(CodeEnum.DUPLICATE_KEY_ERR);
    }

    /*
     * @注释:
     * @Author: Lan
     */
    @ExceptionHandler(BizExc.class)
    public Result<String> controllerEx(BizExc e){
        log.error("业务异常",e);
        return Result.buildFailure(e.getCode(),e.getMessage());
    }
    /*
     * @注释:处理未精准定位异常
     * @return: com.lan.bean.res.Result<java.lang.String>
     * @Author: Lan
     */
    @ExceptionHandler(Exception.class)
    public Result<String> controllerEx(Exception e){
        log.error("未精准定位异常",e);
        return Result.buildFailure(CodeEnum.SYS_ERR);
    }
}
