package com.grady.artical.controller;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.service.IArticalService;
import com.grady.artical.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author grady
 * @date 20-5-25 上午11:32
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticalService iArticalService;

    @RequiresPermissions("article:list")
    @RequestMapping("/listArticle")
    public JSONObject listArticle(HttpServletRequest request){
        return iArticalService.listArticle(CommonUtil.request2Json(request));
    }

    @RequestMapping(value = "/addArticle",method = RequestMethod.POST)
    public JSONObject addArticle(@RequestBody JSONObject jsonObject){
        CommonUtil.hasAllRequired(jsonObject,"content");
        return iArticalService.addArticle(jsonObject);
    }

    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    public JSONObject updateArtical(@RequestBody JSONObject jsonObject){
        CommonUtil.hasAllRequired(jsonObject,"content");
        return iArticalService.updateArticle(jsonObject);
    }
}
