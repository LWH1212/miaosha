package com.lwh.controller;

import com.lwh.common.Const;
import com.lwh.param.LoginParam;
import com.lwh.pojo.User;
import com.lwh.redis.GoodsKey;
import com.lwh.redis.RedisService;
import com.lwh.redis.UserKey;
import com.lwh.result.Result;
import com.lwh.service.UserService;
import com.lwh.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Result<User> doLogin(HttpServletResponse response, HttpSession session, @Valid LoginParam loginParam){
        Result<User> login = userService.login(loginParam);
        if (login.isSuccess()){
            CookieUtil.writeLoginToken(response,session.getId());
            session.setAttribute("loginUser",login.getData());
            redisService.set(UserKey.getByName,session.getId(),login.getData(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return login;
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        String token = CookieUtil.readLoginToken(request);
        session.removeAttribute("loginUser");
        CookieUtil.delLoginToken(request,response);
        redisService.del(UserKey.getByName,token);
        redisService.del(GoodsKey.getGoodsList,"");
        return "login";
    }

}