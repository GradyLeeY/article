package com.grady.artical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.dao.LoginDao;
import com.grady.artical.service.ILoginService;
import com.grady.artical.service.IPermissionService;
import com.grady.artical.util.CommonUtil;
import com.grady.artical.util.constant.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private IPermissionService permissionService;

    public JSONObject authLogin(JSONObject jsonObject){
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject jsonObjectInfo = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            currentUser.login(token);
            jsonObjectInfo.put("result","success");
        }catch (AuthenticationException e){
            jsonObjectInfo.put("result", "fail");
        }
        return CommonUtil.successJson(jsonObjectInfo);
    }

    public JSONObject getUser(String username,String password){
        return loginDao.getUser(username, password);
    }

    public JSONObject getInfo(){
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject info = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        info.put("userPermission", userPermission);
        return CommonUtil.successJson(info);
    }
}
