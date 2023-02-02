package com.lan.mybatis.test;

import com.lan.Service.CourseService;
import com.lan.StudentApplication;
import com.lan.bean.pojo.Course;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*依赖注入测试类*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApplication.class)
public class TestService {

    @Autowired
    private CourseService courseService;

    @Test
    public void test(){

        CommonSearchReq commonSearchReq = null;
        Result<List<Course>> allCourse = courseService.getAllCourse(commonSearchReq);
        System.out.println(allCourse);
    }
}
