package com.lan.Service.serviceImpl;

import com.lan.bean.dto.JwtManagerDTO;
import com.lan.bean.res.Result;
import com.lan.bean.vo.JwtManagerVO;
import com.lan.mybatis.mapper.ManagerMapper;
import com.lan.bean.pojo.Manager;
import com.lan.Service.ManagerService;
import com.lan.bean.req.LoginReq;
import com.lan.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;





/*
 * @注释:业务逻辑层通过Mapper接口查询管理员表信息
 * @return: java.util.List<com.lan.mybatis.pojo.Manager>
 * @Author: Lan
 */
    @Override
    public List<Manager> selectManagers() {
        return null;
    }
/*
 * @注释:登入参数校验
 * @return: java.lang.String
 * @Author: Lan
 */
    @Override
    public Result<JwtManagerVO> login(LoginReq loginReq) throws Exception {

        if(loginReq.getUsername()==null){
            throw  new Exception("用户名字段错误");
        }
        if(loginReq.getPassword()==null){
            throw  new Exception("密码字段错误");
        }
        /*
         * @注释:通过mangerMapper查询数据库是否存在该用户，用户校验
         */
        Manager admin = managerMapper.login(loginReq);
        /*
         * @注释:登陆校验
         * @Author: Lan
         */
        if(admin == null){
            return Result.buildFailure("用户名或者密码不正确");
        }
        /*
         * @注释:生成携带token的管理者信息
         */
        JwtManagerVO vo = generateToken(admin);


        /*
         * @注释:打印查询状态
         * @Author: Lan
         */
        log.info("vo {}",vo);
        return Result.buildSuccess(vo);
    }

    /*
     * @注释:生成携带token的管理者信息
     */
    private JwtManagerVO generateToken(Manager manager){

        JwtManagerDTO jwtManagerDTO = new JwtManagerDTO();
        log.info("Dto {}",jwtManagerDTO);
        /*
         * @注释:将拥有的共同属性赋值
         */
        BeanUtils.copyProperties(manager,jwtManagerDTO);

        log.info("Dto {}",jwtManagerDTO);
        String token = JwtUtil.getToken(jwtManagerDTO);
        JwtManagerVO vo = new JwtManagerVO();
        BeanUtils.copyProperties(jwtManagerDTO,vo);
        vo.setToken(token);
        return vo;
    }
}
