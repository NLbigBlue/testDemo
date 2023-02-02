package com.lan.mybatis.mapper;

import com.lan.bean.pojo.Manager;
import com.lan.bean.req.LoginReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ManagerMapper {
   /*
    * @注释:查询
    * @return: java.util.List<com.lan.mybatis.pojo.Manager>
    * @Author: Lan
    */
    List<Manager> selectManagers();

    Manager login(LoginReq loginReq);
}
