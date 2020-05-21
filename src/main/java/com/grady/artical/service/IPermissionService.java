package com.grady.artical.service;

import com.alibaba.fastjson.JSONObject;

public interface IPermissionService {
    JSONObject getUserPermission(String username);
}
