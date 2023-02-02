package com.lan.Service;

import com.lan.bean.pojo.Student;
import com.lan.bean.query.UpdateDo;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findOneById(Integer id);

    void insertStudent(Student student);


    Result<List<Student>> selectLike(CommonSearchReq<String> req);

    void updateStudent(UpdateDo req);

    void deleteA(Integer id);
}
