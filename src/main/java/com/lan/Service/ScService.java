package com.lan.Service;

import com.lan.bean.req.CommonSearchReq;
import com.lan.bean.req.NewSc;
import com.lan.bean.req.SCD;
import com.lan.bean.res.Result;

import java.util.List;

public interface ScService {


    Result<List<NewSc>> selectScValues(CommonSearchReq<Integer> req);

    void addOneNewChoose(NewSc newSc);

    void removeS(SCD scd);

    List<NewSc> searchAllByS(Integer sid);

    void updateGrade(NewSc newSc);
}
