package com.lan.Controllers;
import com.lan.Service.CourseService;
import com.lan.bean.pojo.Course;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/course")
@CrossOrigin(origins = {"*","null"})
@Slf4j
public class CourseController {
    /*
     * @注释: 注入service
     * @Author: Lan
     */
    @Resource
    private CourseService courseService;

    //获取所有课程信息
    @PostMapping(value = "/getAllCourses")
    public Result<List<Course>> allCourse(@RequestBody CommonSearchReq<String> commonSearchReq){


      return courseService.getAllCourse(commonSearchReq);
    }



    //删除一条课程
    @GetMapping(value = "/deleteCourseInfoById")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteCourseInfoById(@RequestParam("id") Integer cid){

        courseService.deleteCourse(cid);
        return  Result.buildSuccess("删除成功");
    }


    //删除一条课程
    @PostMapping(value = "/addCourseInfo")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> addCourseInfo(@RequestBody Course course){

        courseService.addNewCourse(course);
        return  Result.buildSuccess("添加成功");
    }


    //修改一条课程
    @PostMapping(value = "/updateCourseInfo")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateCourseInfo(){


        return Result.buildSuccess("修改成功");
    }




}
