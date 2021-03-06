package com.zanclick.prepay.web.dto;

import lombok.Data;

/**
 * 支付创建
 *
 * @author duchong
 * @date 2019-7-8 15:49:20
 */
@Data
public class ApiPayRefund{

    /**
     * 对应 outTradeNo
     * */
    private String orderNo;

    private String outOrderNo;
}
