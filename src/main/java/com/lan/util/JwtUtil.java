package com.lan.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lan.bean.res.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class JwtUtil {

    private static final String SECRET = "roadJava.com";
    /*
     * @注释:generateToken
     * @return: java.lang.String
     */
    public static <T>String getToken(T t){
        //定义过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH,1);

        JWTCreator.Builder builder = JWT.create().

                withClaim(t.getClass().getSimpleName(),
                        JSON.toJSONString(t));

        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SECRET));
    }
    /*
     * @注释:校验token是否合法
     * @Author: Lan
     */
    public static Result<DecodedJWT> verify(String tokenToVerify) {
        String errMsg;

        /*
         * @注释:token 校验
         */
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(tokenToVerify);

            return Result.buildSuccess(decodedJWT);
        }catch (AlgorithmMismatchException e){
            errMsg = "算法不匹配";
            log.error(errMsg,e);
        }catch (SignatureVerificationException e){
            errMsg = "签名不匹配";
            log.error(errMsg,e);
        }catch (TokenExpiredException e){
            errMsg = "令牌失效 ";
            log.error(errMsg,e);
        }catch (InvalidClaimException e){
            errMsg = "声明无效";
            log.error(errMsg,e);
        }catch (Exception e){
            errMsg = "令牌校验失败";
            log.error(errMsg,e);
        }
        return Result.buildFailure(errMsg);
    }


    public static<T> T parse(DecodedJWT data, Class<T> tClass) {
        //与放入的时候声明的名字相同
        Claim claim = data.getClaim(tClass.getSimpleName());

        if(claim == null){
            return null;
        }

        return JSON.parseObject(claim.asString(),tClass);
    }
}
