package com.lan.bean.enums;
/*
 * @注释:
 * @return: 定义枚举，指定可能出现的状态
 * @Author: Lan
 */

public enum CodeEnum {

    SUCCESS(200,"成功"),

    PARAM_ERR(10000,"参数不正确"),

    DB_ERR(20000,"参数不正确"),
    DUPLICATE_KEY_ERR(20001,"唯一性约束校验失败"),

    BIZ_ERR(30000,"业务异常"),

    AUTH_ERR(30001,"权限不足"),

    SYS_ERR(90000,"系统异常")
    ;


    CodeEnum(Integer code,String msg){
        this.code = code;
        this.msg =msg;
    }
    private  final Integer code;
    private  final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

