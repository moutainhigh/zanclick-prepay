package com.zanclick.prepay.web.dto;

import lombok.Data;

/**
 * @author duchong
 * @description
 * @date
 */
@Data
public class RequestContent {

    private String method;

    private String cipherJson;
}