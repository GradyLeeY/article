package com.grady.artical.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author grady
 * @date 20-5-25 上午11:35
 */
public interface IArticalService {

    JSONObject listArticle(JSONObject jsonObject);

    JSONObject addArticle(JSONObject jsonObject);

    JSONObject updateArticle(JSONObject jsonObject);


}
