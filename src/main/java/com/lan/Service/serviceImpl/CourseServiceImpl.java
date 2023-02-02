package com.lan.Service.serviceImpl;

import com.lan.Service.CourseService;
import com.lan.bean.pojo.Course;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;
import com.lan.mybatis.mapper.CourseMapper;
import com.lan.mybatis.mapper.ScMapper;
import com.lan.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private ScMapper scMapper;

    @Override
    public Result<List<Course>> getAllCourse(CommonSearchReq<String> commonSearchReq) {
        //分页装载
        PageUtil.starPage(commonSearchReq.getPageNow(),commonSearchReq.getPageSize());
        List<Course> allCourses =  courseMapper.getAll();
        return PageUtil.warpPageData(allCourses);
    }

    @Override
    public void deleteCourse(Integer cid) {
        //先删除选课表中所有此课程的选课信息
        scMapper.deleteAllChoose(cid);

        courseMapper.removeCourse(cid);

    }

    @Override
    public void addNewCourse(Course course) {
        courseMapper.addNewCourse(course);
    }
}
