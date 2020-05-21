package com.grady.artical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.dao.PermissionDao;
import com.grady.artical.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public JSONObject getUserPermission(String username){
        JSONObject userPermission = getUserPermissionFromDB(username);
        return userPermission;
    }

    private JSONObject getUserPermissionFromDB(String username) {
        JSONObject userPermission = permissionDao.getUserPermission(username);
        //管理员角色ID为1
        int adminRoleId = 1;
        //如果是管理员
        String roleIdKey = "roleId";
        if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
            //查询所有菜单  所有权限
            Set<String> menuList = permissionDao.getAllMenu();
            Set<String> permissionList = permissionDao.getAllPermission();
            userPermission.put("menuList", menuList);
            userPermission.put("permissionList", permissionList);
        }
        return userPermission;
    }
}
