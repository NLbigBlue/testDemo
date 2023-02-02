package com.lan.Ex;

import com.lan.bean.enums.CodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class BizExc extends RuntimeException{
    private Integer code;

    public BizExc(String msg){
        super(msg);
    }

    public BizExc(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }
}
