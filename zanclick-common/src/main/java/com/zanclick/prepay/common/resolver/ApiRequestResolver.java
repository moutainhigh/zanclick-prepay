package com.zanclick.prepay.common.resolver;


/**
 * 请求通用接口
 *
 * @author duchong
 * @date 2019-9-5 16:41:15
 **/
public interface ApiRequestResolver {

    /**
     * 通用接口
     *
     * @param request
     * @return
     */
     String resolve(String request);

    /**
     * json字符串，转实体类
     *
     * @param content
     * @param tClass
     * @return
     */
//    public <T> T parser(String content, Class<T> tClass){
//        return JSONObject.parseObject(content,tClass);
//    }
}