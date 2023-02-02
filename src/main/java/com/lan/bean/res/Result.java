package com.lan.bean.res;

import com.lan.bean.enums.CodeEnum;
import lombok.Data;



/*
 * @注释:统一返回结果
 *  泛型类
 * @Author: Lan
 */
@Data
public class Result<T>{
    /*
     * @注释:响应码
     *
     */
    private  Integer code = CodeEnum.SUCCESS.getCode();

    /*
     * @注释:响应信息
     * @Author: Lan
     */
    private  String msg = CodeEnum.SUCCESS.getMsg();

    /*
     * @注释:用于判断是否响应成功
     *
     *success表示成功,false表示失败
     */

    private Boolean success = Boolean.TRUE;

    private T data;

    private Long total;

    /*
     * 成功类的返回方法
     */
    public static <T> Result<T> buildEmptySuccess(){
        return new Result<>();
    }
    /*
     * @注释:设置响应数据
     * @Author: Lan
     */
    public static <T> Result<T> buildSuccess(T t){
        Result<T> result = buildEmptySuccess();
        result.setData(t);
        return result;
    }

    public static <T> Result<T> buildSuccess(T t ,long total){
        Result<T> result= buildSuccess(t);
        result.setTotal(total);
        return result;
    }
    /*
     * @注释:设置失败响应类
     */
    public static <T> Result<T> buildFailure(String msg){
        Result<T> result= new Result<>();

        result.setCode(null);
        result.setSuccess(false);
        result.setMsg(msg);

        return result;
    }

    public static <T> Result<T> buildFailure(CodeEnum codeEnum){

        Result<T> result= new Result<>();
        result.setCode(codeEnum.getCode());
        result.setSuccess(false);
        result.setMsg(codeEnum.getMsg());

        return result;
    }
    public static <T> Result<T> buildFailure(Integer code,String msg){

        Result<T> result= new Result<>();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);

        return result;
    }

    /*
     * @注释:方便判断是否成功
     */
    public boolean isSuccess(){
        return success;
    }

    public boolean isFailed(){
        return !success;
    }


}
