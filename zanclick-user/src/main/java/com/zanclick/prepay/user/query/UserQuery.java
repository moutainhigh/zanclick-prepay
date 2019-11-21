package com.zanclick.prepay.user.query;

import com.zanclick.prepay.user.entity.User;
import lombok.Data;

/**
 * @author duchong
 * @description 用户
 * @date 2019-8-3 10:05:37
 */
@Data
public class UserQuery extends User {

    private Integer page;

    private Integer limit;


    private String pwd;


}
