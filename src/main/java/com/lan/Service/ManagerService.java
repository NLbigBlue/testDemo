package com.lan.Service;

import com.lan.bean.pojo.Manager;
import com.lan.bean.req.LoginReq;
import com.lan.bean.res.Result;
import com.lan.bean.vo.JwtManagerVO;

import java.util.List;

public interface ManagerService {
    /*
     * @注释:查询所有管理员信息
     * @Author: Lan
     */
    List<Manager> selectManagers();
    /*
     * @注释:查询登入信息
     * @Author: Lan
     */
    Result<JwtManagerVO> login(LoginReq user) throws Exception;
}
