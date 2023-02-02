package com.lan.bean.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

/*
 *
 * 注释：定义接收对象
 * @Author: Lan
 */
@Data
public class Manager {
    private Long id;
    private String username;
    private String pwd;

}
