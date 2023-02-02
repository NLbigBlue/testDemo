package com.lan.Controllers;

import com.lan.bean.pojo.Student;
import com.lan.Service.StudentService;

import com.lan.bean.query.UpdateDo;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.res.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
 * @Description:
 * 1.配置学生表访问controller
 * 2.@CrossOrigin解决跨域问题
 * @param null
 * @return:
 * @Author: Lan
 * @Date:  13:37
 */
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = {"*","null"})
public class StudentController {
    @Resource
    private  StudentService studentService;



    /*
    * 配置：访问map接口
    * */
    @GetMapping(value = "/forAll")
    public Result<List<Student>> forAll(){
        List<Student> students = studentService.findAll();

        return Result.buildSuccess(students);
    }
    /*
     * @注释:
     */
    @PostMapping(value = "/add")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> add(@RequestBody @Validated Student student){


        studentService.insertStudent(student);

        return Result.buildSuccess("添加成功");
    }

    /*
     * 配置：访问map接口
     * */
    @PostMapping (value = "/pageSearch")
    public Result<List<Student>> selectLick(@RequestBody CommonSearchReq<String> req){



        return studentService.selectLike(req);
    }

    /*
     * 配置：访问map接口
     * */
    @PostMapping (value = "/updateValue")
    public Result<String> updateValue(@RequestBody UpdateDo updateDo){



        studentService.updateStudent(updateDo);


        return Result.buildSuccess("修改成功");
    }

    @GetMapping (value = "/remove")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> remove(Integer id){

       studentService.deleteA(id);
       return Result.buildSuccess("删除成功");
    }

    @GetMapping (value = "/searchOne")
    public Result<Student> searchOne(Integer id){
        Student searchOne = studentService.findOneById(id);
        return Result.buildSuccess(searchOne);
    }





}
