package com.fanxl.user.controller;

import com.fanxl.user.constant.CookieConstant;
import com.fanxl.user.constant.RedisConstant;
import com.fanxl.user.dataobject.UserInfo;
import com.fanxl.user.enums.ResultEnum;
import com.fanxl.user.enums.RoleEnum;
import com.fanxl.user.service.UserService;
import com.fanxl.user.utils.CookieUtil;
import com.fanxl.user.utils.ResultVOUtil;
import com.fanxl.user.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/17 0017 21:51
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response) {

        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (userInfo.getRole() != RoleEnum.BUYER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        CookieUtil.setCookie(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request,
                           HttpServletResponse response) {

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                StringUtils.isNotEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
            return ResultVOUtil.success();
        }

        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (userInfo.getRole() != RoleEnum.SELLER.getCode()) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid, expire, TimeUnit.SECONDS);

        CookieUtil.setCookie(response, CookieConstant.TOKEN, token, CookieConstant.expire);

        return ResultVOUtil.success();
    }

}
