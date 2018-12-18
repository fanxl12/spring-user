package com.fanxl.user.service;

import com.fanxl.user.dataobject.UserInfo;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 21:48
 */
public interface UserService {

    /**
     * openid查找用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
