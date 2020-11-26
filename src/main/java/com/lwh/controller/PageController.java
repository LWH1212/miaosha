package com.lwh.controller;

import com.lwh.redis.GoodsKey;
import com.lwh.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/login")
    public String loginPage(){
        redisService.del(GoodsKey.getGoodsList,"");
        return "login";
    }

}
