package com.lan.interceptor;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.lan.bean.constants.Constants;
import com.lan.bean.context.ManagerContext;
import com.lan.bean.dto.JwtManagerDTO;
import com.lan.bean.enums.CodeEnum;
import com.lan.bean.res.Result;
import com.lan.util.JwtUtil;
import com.lan.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @注释:配置后台拦截器
 * @Author: Lan
 */
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    /*
     * @注释:前置处理器在controller
     * @Author: Lan
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        /*getServletPath :获取请求的路径 */

        log.info("请求的路径 {}",request.getServletPath());

        /*getMethod :获取请求方式
        *
        *axios对非简单请求 比如(application/json)
        * 有时候会通过options请求来判断服务端是否跨域
        *
        */

        String method = request.getMethod();
        /*matches : 判断两个方法名是否相同 是 返会true 反之返回false*/
        if(HttpMethod.OPTIONS.matches(method)){
            /*
             * @注释:执行其他的filter
             */
            return  true;
        }


        /*
         * @注释:获取token信息
         */
        String tokenToVerify = request.getHeader(HttpHeaders.AUTHORIZATION);
        tokenToVerify=removeDoubleQuotes(tokenToVerify);
        log.info("token信息：{}",tokenToVerify);
        if(StringUtils.isBlank(tokenToVerify)){

             tokenToVerify = request.getParameter(Constants.USER_TOKEN_KEY);

             /*
              * @注释:StringUtils.isBlank(tokenToVerify):当前字符串为空或者包含空格返回true ，不包含空格 返回false
              */
             if(StringUtils.isBlank(tokenToVerify)){

                 //响应失败 todo
                 return false;
             }
        }


        if(StringUtils.isBlank(tokenToVerify)){

            ResponseUtil.respAppJson(response,Result.buildFailure(CodeEnum.AUTH_ERR));
            //响应失败 todo
            return false;
        }

        Result<DecodedJWT> verify = JwtUtil.verify(tokenToVerify);
        if(verify.isFailed()){
            //响应失败
            return false;
        }

        // 从token中还原出放入的对象
        JwtManagerDTO jwtManagerDTO  = JwtUtil.parse(verify.getData(),JwtManagerDTO.class);

        if(jwtManagerDTO == null){
            //响应失败

            return false;
        }

        //放入ThreadLocal之后 在任意层controller
        ManagerContext.setThreadLocal(jwtManagerDTO);

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    public static String removeDoubleQuotes(String result) {
        //去掉""号
        return result.replace("\"", "");
    }

}
