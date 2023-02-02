package com.lan.mybatis.mapper;

import com.lan.bean.pojo.Course;
import com.lan.bean.pojo.Manager;
import com.lan.bean.req.LoginReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CourseMapper {

    //查询所有的课程

    List<Course> getAll();

    void removeCourse(Integer cid);

    void addNewCourse(Course course);
}
