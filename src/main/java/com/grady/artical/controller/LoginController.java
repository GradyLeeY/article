package com.grady.artical.controller;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.service.ILoginService;
import com.grady.artical.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @RequestMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson,"username,password");
        return iLoginService.authLogin(requestJson);
    }

    @RequestMapping("/getInfo")
    public JSONObject getInfo(){
        return iLoginService.getInfo();
    }
}
