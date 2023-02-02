package com.lan.bean.context;

import com.lan.bean.dto.JwtManagerDTO;

/*
 * @注释:
 * @return:
 * @Author: Lan
 */
public class ManagerContext {

    private static ThreadLocal<JwtManagerDTO> threadLocal = new ThreadLocal<>();

    public static void setThreadLocal(JwtManagerDTO jwtManagerDTO){
        threadLocal.set(jwtManagerDTO);
    }

    public static JwtManagerDTO getThreadLocal(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
