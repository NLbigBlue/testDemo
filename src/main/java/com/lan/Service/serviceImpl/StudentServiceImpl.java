package com.lan.Service.serviceImpl;

import com.lan.Ex.BizExc;
import com.lan.bean.query.UpdateDo;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;
import com.lan.mybatis.mapper.ScMapper;
import com.lan.mybatis.mapper.StudentMapper;
import com.lan.bean.pojo.Student;
import com.lan.Service.StudentService;
import com.lan.util.PageUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/*
 * @学生业务处理层
 * @Author: Lan
 * @Date:  15:13
 */
@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    private  StudentMapper studentMapper;

    @Resource
    private ScMapper scMapper;


    /*
     * 查询所有学生信息
     * @return: java.util.List<com.lan.mybatis.pojo.Student>
     * @Author: Lan
     */
    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
    /*
     * @注释:通过id查询指定学生信息
     * @return: com.lan.mybatis.pojo.Student
     * @Author: Lan
     */
    @Override
    public Student findOneById(Integer id) {

        return studentMapper.findOneById(id);
    }


    /*
     * @注释:插入异常处理
     */
    @Override
    public void insertStudent(Student student) {
        Student dbStudent = studentMapper.findOneById(student.getId());
        if(dbStudent!=null){
            throw new BizExc("用户已存在");
        }
        studentMapper.insertStudent(student);
    }

    @Override
    public Result<List<Student>> selectLike(CommonSearchReq req) {
        /*
         * @注释:
         * @return: com.lan.bean.res.Result<java.util.List<com.lan.bean.pojo.Student>>
         * @Author: Lan
         * @params:
         *         pageNow===当前页码
         *         pageSize===查询页的大小
         */
        PageUtil.starPage(req.getPageNow(),req.getPageSize());
        log.info("修饰后的请求参数：{}",req);
        List<Student> List = studentMapper.queryList(req);

        return PageUtil.warpPageData(List);
    }

    @Override
    public void updateStudent(UpdateDo req) {
        Student value = new Student();
        BeanUtils.copyProperties(req,value);
        //如果id 发生改变
       if(req.getId()!=req.getOldId()){
           studentMapper.remove(req.getOldId());
           studentMapper.insertStudent(value);
       }
       studentMapper.update(req);
    }

    @Override
    public void deleteA(Integer id) {

        scMapper.deleteAllChooseBySid(id);

        studentMapper.remove(id);

    }
}
