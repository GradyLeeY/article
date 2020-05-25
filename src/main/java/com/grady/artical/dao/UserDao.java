package com.grady.artical.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author grady
 * @date 20-5-25 下午3:10
 */
@Mapper
public interface UserDao {
    int countUser(JSONObject jsonObject);

    List<JSONObject> listUser(JSONObject jsonObject);

    List<JSONObject> getAllRoles();
}
