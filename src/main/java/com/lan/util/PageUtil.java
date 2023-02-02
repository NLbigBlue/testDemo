package com.lan.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.bean.res.Result;

import java.util.List;

public class PageUtil {

    public static void  starPage(Integer pagNow, Integer pageSize){
        PageHelper.startPage(pagNow,pageSize);
    }

    public static <T> Result<List<T>> warpPageData(List<T> queryList){
        PageInfo<T> pageInfo = new PageInfo<>(queryList);
        System.out.println(pageInfo);

        return Result.buildSuccess(queryList,pageInfo.getTotal());

    }
}
