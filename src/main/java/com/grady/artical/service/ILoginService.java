package com.grady.artical.service;

import com.alibaba.fastjson.JSONObject;

public interface ILoginService {
    JSONObject authLogin(JSONObject jsonObject);

    JSONObject getUser(String username,String password);

    JSONObject getInfo();

    JSONObject logout();
}
