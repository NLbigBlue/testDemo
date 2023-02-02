package com.lan.Controllers;

import com.lan.Service.ManagerService;
import com.lan.bean.req.LoginReq;
import com.lan.bean.res.Result;
import com.lan.bean.vo.JwtManagerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/manager")
@CrossOrigin(origins = {"*","null"})
@Slf4j
public class ManagerController {
    /*
     * @注释: 注入service
     * @Author: Lan
     */
    @Resource
    private ManagerService managerService;



    /*
     * @注释:@RequestBody 接收响应参数
     * @return: java.lang.String
     * @Author: Lan
     */
    @PostMapping(value = "/login")
    public Result<JwtManagerVO> login(@RequestBody @Validated LoginReq user) throws Exception {
        log.info("login params : {}",user);
        return managerService.login(user);

    }

    /*
     * @注释:@RequestBody 接收响应参数
     * @return: java.lang.String
     * @Author: Lan
     */
    @PostMapping(value = "/login1")
    public Result<JwtManagerVO> login1(@Validated LoginReq user) throws Exception {
        log.info("login params : {}",user);

        return managerService.login(user);

    }


}
