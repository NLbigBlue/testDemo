package com.lan.Service;

import com.lan.bean.pojo.Course;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;

import java.util.List;

public interface CourseService {

    Result<List<Course>> getAllCourse(CommonSearchReq<String> commonSearchReq);

    void deleteCourse(Integer cid);

    void addNewCourse(Course course);
}
