package com.lan.util;

import com.alibaba.fastjson.JSON;
import com.lan.bean.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Slf4j

public class ResponseUtil {
    /*
       * @功能：浏览器向前端写入响应数据
     */
    public static void respAppJson(HttpServletResponse response, Object obj){
        /*
         * @注释:
         * 1.setCharacterEncoding 设置字符集编码
         *
         * 2.setContentType 设置请求方式
         *

         */

        response.setCharacterEncoding(Constants.UTF_8_NAME);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try(PrintWriter rw = response.getWriter()){
            rw.print(JSON.toJSONString(obj));
            rw.flush();
        }catch (IOException e){

        log.error("前端异常",e);
        }


    }
}
