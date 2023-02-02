package com.lan.bean.req;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class LoginReq {
    private String username;
    private String password;

}
