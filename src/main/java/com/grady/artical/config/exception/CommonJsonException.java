package com.grady.artical.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.util.CommonUtil;
import com.grady.artical.util.constant.ErrorEnum;

/**
 * 自定义错误类，比如在校验参数时如果不符合要求，可以抛出该错误类
 * 拦截器可以统一拦截此错误，将其中json返回给前端
 */
public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    public CommonJsonException(ErrorEnum errorEnum){
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }
    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }
}
