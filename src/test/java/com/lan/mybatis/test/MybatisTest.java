package com.lan.mybatis.test;

import com.lan.bean.pojo.Student;
import com.lan.bean.query.UpdateDo;
import com.lan.mybatis.mapper.StudentMapper;


import org.junit.Test;


import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MybatisTest {
    @Resource
    private StudentMapper studentMapper;


    @Test
    public void testMybatis() {

    }
}
