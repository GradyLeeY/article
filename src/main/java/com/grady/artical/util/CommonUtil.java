package com.grady.artical.util;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.config.exception.CommonJsonException;
import com.grady.artical.util.constant.Constants;
import com.grady.artical.util.constant.ErrorEnum;

public class CommonUtil {

    public static void hasAllRequired(final JSONObject jsonObject,String requiredColumns){
        if (StringTools.isNullOrEmpty(requiredColumns)){
            String[] columns = requiredColumns.split(",");
            String misscol = "";
            for (String col:columns
                 ) {
                Object value = jsonObject.get(col.trim());
                if (StringTools.isNullOrEmpty(value)){
                    misscol += value+" ";
                }
            }
            if (!StringTools.isNullOrEmpty(misscol)) {
                jsonObject.clear();
                jsonObject.put("code", ErrorEnum.E_90003.getErrorCode());
                jsonObject.put("msg", "缺少必填参数:" + misscol.trim());
                jsonObject.put("info", new JSONObject());
                throw new CommonJsonException(jsonObject);
            }
        }
    }

    /**
     * 返回一个返回码为100的json
     */
    public static JSONObject successJson(Object info) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("info", info);
        return resultJson;
    }

    /**
     * 返回错误信息JSON
     */
    public static JSONObject errorJson(ErrorEnum errorEnum) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", errorEnum.getErrorCode());
        resultJson.put("msg", errorEnum.getErrorMsg());
        resultJson.put("info", new JSONObject());
        return resultJson;
    }
}
