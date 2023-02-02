package com.lan.mybatis.mapper;


import com.lan.bean.pojo.Student;
import com.lan.bean.query.UpdateDo;
import com.lan.bean.req.CommonSearchReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/*数据操作层*/
@Mapper
@Repository
public interface StudentMapper{
  /*
   *插入一条学生信息
   * @return: int
   * @Author: Lan
   */
  void insertStudent(Student student);

  /*
   *通过id查询一条学生信息
   * @return: com.lan.mybatis.pojo.Student
   * @Author: Lan
   */
  Student findOneById(Integer id);

  List<Student> findAll();


  List<Student> queryList(CommonSearchReq req);

  Integer update(UpdateDo req);

  void remove(Integer id);
}
