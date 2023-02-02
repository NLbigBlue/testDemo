package com.lan.bean.constants;

import java.nio.charset.StandardCharsets;


/*
 * @注释:接口存储
 */
public interface Constants {
    /*
     * @注释:StandardCharsets :字符集类
     */
    String USER_TOKEN_KEY = "user_token";
    String UTF_8_NAME = StandardCharsets.UTF_8.name();
    Integer DEFAULT_PAGE_NUM = 1;
    Integer DEFAULT_PAGE_SIZE = 10;
}
