package com.grady.artical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.dao.ArticalDao;
import com.grady.artical.service.IArticalService;
import com.grady.artical.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author grady
 * @date 20-5-25 上午11:35
 */
@Service
public class ArticalServiceImpl implements IArticalService {

    @Autowired
    private ArticalDao articalDao;

    public JSONObject listArticle(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = articalDao.countArticle(jsonObject);
        List<JSONObject> articalDaos = articalDao.listArticle(jsonObject);
        return CommonUtil.successPage(jsonObject,articalDaos,count);
    }

}
