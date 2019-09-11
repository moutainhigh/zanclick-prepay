package com.zanclick.prepay.common.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 请求结果返回
 *
 * @author duchong
 * @date 2019-7-5 10:25:29
 */
@Data
public class ResponseParam extends Param implements Serializable {

    private String code;

    private String message;

    private String content;

    public void setSuccess(){
        this.code = ApiResponseCode.SUCCESS;
    }

    public void setFail(){
        this.code = ApiResponseCode.FAIL;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}