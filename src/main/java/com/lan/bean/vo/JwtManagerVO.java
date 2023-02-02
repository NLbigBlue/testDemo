package com.lan.bean.vo;

import com.lan.bean.dto.JwtManagerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;


/*
 * @注释:
 * @Author: Lan
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JwtManagerVO extends JwtManagerDTO {
    private String token;
}
