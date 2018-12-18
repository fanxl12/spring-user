package com.fanxl.user.enums;

import lombok.Getter;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 22:03
 */
@Getter
public enum RoleEnum {

    BUYER(1, "买家"),
    SELLER(2, "卖家")
    ;

    private Integer code;

    private String name;

    RoleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
