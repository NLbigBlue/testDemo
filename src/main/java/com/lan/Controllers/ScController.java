package com.lan.Controllers;


import com.lan.Service.ScService;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.req.NewSc;
import com.lan.bean.req.SCD;
import com.lan.bean.res.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/s_c")
@CrossOrigin(origins = {"*","null"})
@Slf4j
public class ScController {
    /*
     * @注释: 注入service
     * @Author: Lan
     */
    @Resource
    private ScService scService;

    @PostMapping(value = "/allS_cOrOnly")
    @Transactional(readOnly = true)
    public Result<List<NewSc>> getAllSelections(@RequestBody CommonSearchReq<Integer> Req){

        return scService.selectScValues(Req);
    }

    @PostMapping(value = "/addNewChoose")
    public Result<String> addNewChoose(@RequestBody NewSc newSc){

        scService.addOneNewChoose(newSc);
        return Result.buildSuccess("添加成功");
    }

    @GetMapping(value = "/removeSingle")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> removeSingle(SCD scd){

        scService.removeS(scd);

        return Result.buildSuccess("删除成功");
    }

    @GetMapping(value = "/searchAllBySid")
    @Transactional(rollbackFor = Exception.class)
    public Result<List<NewSc>> searchAllBySid(@RequestParam("sid") Integer sid){

        List<NewSc> allChooses = scService.searchAllByS(sid);

        return Result.buildSuccess(allChooses);
    }

    @PostMapping(value = "/updateGrade")
    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateGrade(@RequestBody NewSc newSc){

        scService.updateGrade(newSc);
        return Result.buildSuccess("修改成功");
    }






}
