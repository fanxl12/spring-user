package com.fanxl.user.service.impl;

import com.fanxl.user.dataobject.UserInfo;
import com.fanxl.user.repository.UserInfoRepository;
import com.fanxl.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 21:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
