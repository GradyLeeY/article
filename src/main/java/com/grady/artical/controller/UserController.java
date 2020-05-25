package com.grady.artical.controller;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.service.IUserService;
import com.grady.artical.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequiresPermissions("user:list")
    @RequestMapping("/list")
    public JSONObject listUser(HttpServletRequest request){
        return iUserService.listUser(CommonUtil.request2Json(request));
    }

    @RequiresPermissions(value = {"user:add", "user:update"},logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public JSONObject getAllRoles(){
        return iUserService.getAllRoles();
    }
}
