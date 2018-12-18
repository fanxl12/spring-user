package com.fanxl.user.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 21:43
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;

}
