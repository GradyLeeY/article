package com.grady.artical.service;

import com.alibaba.fastjson.JSONObject;

public interface IUserService {

    JSONObject listUser(JSONObject jsonObject);

    JSONObject getAllRoles();
}
