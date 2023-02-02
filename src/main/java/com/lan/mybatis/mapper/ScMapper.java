package com.lan.mybatis.mapper;

import com.lan.bean.pojo.Course;
import com.lan.bean.req.NewSc;
import com.lan.bean.req.SCD;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ScMapper {

    List<Course> getAll();

    List<NewSc> getAllSc();
    //删除一个课程的所有选课信息
    void deleteAllChoose(Integer cid);

    void addChoose(NewSc newSc);

    void deleteSingle(SCD scd);

    List<NewSc> selectAllChooseOnly(Integer sid);

    void updateStudentGrade(NewSc newSc);
    //删除一个学生的所有选课信息
    void deleteAllChooseBySid(Integer sid);
}
