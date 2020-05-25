package com.grady.artical.util;

import com.alibaba.fastjson.JSONObject;
import com.grady.artical.config.exception.CommonJsonException;
import com.grady.artical.util.constant.Constants;
import com.grady.artical.util.constant.ErrorEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

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

    /**
     * 将request参数转为json
     * @param request
     * @return
     */
    public static JSONObject request2Json(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paraName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(paraName);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < parameterValues.length; i++) {
                if (parameterValues.length>0){
                    if (i>0){
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(parameterValues[i]);
                }
            }
            jsonObject.put("paramName",stringBuilder.toString());
        }
        return jsonObject;
    }

    /**
     * 在分页查询前，为查询条件添加参数
     * @param jsonObject 查询条件 json
     * @param defaultPageRow 默认的每页条数，即前端不传pageRow参数时的每页条数
     */
    public static void fillPageParam(JSONObject jsonObject,int defaultPageRow){
        int pageNum = jsonObject.getIntValue("pageNum");
        pageNum = pageNum == 0 ? 1:pageNum;
        int pageRow = jsonObject.getIntValue("pageRow");
        pageRow = pageRow == 0 ? defaultPageRow:pageRow;
        jsonObject.put("offSet",(pageNum-1)*pageRow);
        jsonObject.put("pageRow",pageRow);
        jsonObject.put("pageNum",pageNum);
        //删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
        jsonObject.remove("pageSize");
    }
    /**
     * 返回一个info为空对象的成功消息的json
     */
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    public static void fillPageParam(JSONObject jsonObject){
        fillPageParam(jsonObject,10);
    }

    /**
     * 查询分页结果后的封装工具方法
     *
     * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
     * @param list        查询分页对象list
     * @param totalCount  查询出记录的总条数
     */
    public static JSONObject successPage(final JSONObject requestJson, List<JSONObject> list, int totalCount){
        int pageRow = requestJson.getIntValue("pageRow");
        int totalPage = getPageCounts(pageRow,totalCount);
        JSONObject result = successJson();
        JSONObject info = new JSONObject();
        info.put("list", list);
        info.put("totalCount", totalCount);
        info.put("totalPage", totalPage);
        result.put("info", info);
        return result;
    }

    /**
     * 查询分页结果后的封装工具方法
     * @param list
     * @return
     */
    public static JSONObject successPage(List<JSONObject> list){
        JSONObject result = successJson();
        JSONObject info = new JSONObject();
        info.put("list",list);
        result.put("result",info);
        return result;
    }
    /**
     * 获取总页数
     *
     * @param pageRow   每页行数
     * @param itemCount 结果的总条数
     */
    private static int getPageCounts(int pageRow, int itemCount) {
        if (itemCount == 0) {
            return 1;
        }
        return itemCount % pageRow > 0 ?
                itemCount / pageRow + 1 :
                itemCount / pageRow;
    }
}
