package com.fanxl.user.repository;

import com.fanxl.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 21:46
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    /**
     * openid查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
