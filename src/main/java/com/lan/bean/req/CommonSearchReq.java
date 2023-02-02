package com.lan.bean.req;

import com.lan.bean.constants.Constants;
import lombok.Data;

//分页查询常用请求参数接收类
@Data
public class CommonSearchReq<T> {

    //查询的关键词
    private T searchWord;

    //当前查询第几页
    private Integer pageNow= Constants.DEFAULT_PAGE_NUM;
    //单个页面的大小
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
}
