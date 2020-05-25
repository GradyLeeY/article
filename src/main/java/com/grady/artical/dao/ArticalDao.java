package com.grady.artical.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author grady
 * @date 20-5-25 上午11:38
 */
@Mapper
public interface ArticalDao {
    /**
     * 统计文章总数
     */
    int countArticle(JSONObject jsonObject);

    /**
     * 文章列表
     * @param jsonObject
     * @return
     */
    List<JSONObject> listArticle(JSONObject jsonObject);

    int addArticle(JSONObject jsonObject);

    int updateArticle(JSONObject jsonObject);
}
