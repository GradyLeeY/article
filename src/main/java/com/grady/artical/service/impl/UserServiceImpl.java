package com.grady.artical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.dao.UserDao;
import com.grady.artical.service.IUserService;
import com.grady.artical.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    public JSONObject listUser(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> objectList = null;
        if (count>0){
            objectList =  userDao.listUser(jsonObject);
        }
        return CommonUtil.successPage(jsonObject,objectList,count);
    }

    public JSONObject getAllRoles(){
        List<JSONObject> allRoles = userDao.getAllRoles();
        return CommonUtil.successPage(allRoles);
    }










}
