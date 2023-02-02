package com.lan.Service.serviceImpl;

import com.lan.Service.ScService;
import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.req.NewSc;
import com.lan.bean.req.SCD;
import com.lan.bean.res.Result;
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
public class ScServiceImpl implements ScService {
    @Resource
    private ScMapper scMapper;


    @Override
    public Result<List<NewSc>> selectScValues(CommonSearchReq<Integer> req) {

        PageUtil.starPage(req.getPageNow(),req.getPageSize());


        //当搜索关键字段为空的时候，查询所有信息
        List<NewSc> newScs;
        if(req.getSearchWord()==null){
            newScs = scMapper.getAllSc();

        }else{
            newScs = scMapper.selectAllChooseOnly(req.getSearchWord());
        }
        return PageUtil.warpPageData(newScs);

    }

    @Override
    public void addOneNewChoose(NewSc newSc) {

        System.out.println(newSc);


        scMapper.addChoose(newSc);
    }

    @Override
    public void removeS(SCD scd) {
        scMapper.deleteSingle(scd);
    }

    @Override
    public List<NewSc> searchAllByS(Integer sid) {

        return scMapper.selectAllChooseOnly(sid);
    }

    @Override
    public void updateGrade(NewSc newSc) {
        scMapper.updateStudentGrade(newSc);
    }
}
