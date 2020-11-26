package com.lwh.controller;

import com.lwh.bo.GoodsBo;
import com.lwh.common.Const;
import com.lwh.pojo.User;
import com.lwh.redis.GoodsKey;
import com.lwh.redis.RedisService;
import com.lwh.redis.UserKey;
import com.lwh.result.CodeMsg;
import com.lwh.result.Result;
import com.lwh.service.SeckillGoodsService;
import com.lwh.util.CookieUtil;
import com.lwh.vo.GoodsDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    RedisService redisService;

    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/list")
    @ResponseBody
    public String list(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String html = redisService.get(GoodsKey.getGoodsList,"",String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }
        List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
        model.addAttribute("goodsList",goodsList);
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        if (!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList,"",html, Const.RedisCacheExtime.GOODS_LIST);
        }
        return html;
    }

    @RequestMapping("/to_detail2/{goodsId}")
    @ResponseBody
    public  String detail2(Model model, @PathVariable("goodsId")long goodsId,HttpServletRequest request,HttpServletResponse response){

        String loginToken = CookieUtil.readLoginToken(request);
        User user = redisService.get(UserKey.getByName,loginToken,User.class);
        model.addAttribute("user",user);
        //取缓存
        String html  = redisService.get(GoodsKey.getGoodsDetail,""+goodsId,String.class);
        if (!StringUtils.isEmpty(html)){
            return html;
        }
        GoodsBo goods = seckillGoodsService.getseckillGoodsBoByGoodsId(goodsId);
        if (goods == null){
            return "没有找到该页面";
        }else {
            model.addAttribute("goods",goods);
            long startAt = goods.getStartDate().getTime();
            long endAt = goods.getEndDate().getTime();
            long now = System.currentTimeMillis();

            int miaoshaStatus = 0;
            int remainSeconds = 0;
            if (now < startAt){ //秒杀还没开始，倒计时
                miaoshaStatus = 0;
                remainSeconds = (int)((startAt - now) / 1000);
            }else if (now > endAt){ //秒杀已经结束
                miaoshaStatus = 2;
                remainSeconds = -1;
            }else { //秒杀进行中
                miaoshaStatus = 1;
                remainSeconds = 0;
            }
            model.addAttribute("seckillStatus",miaoshaStatus);
            model.addAttribute("remainSeconds",remainSeconds);
            SpringWebContext ctx = new SpringWebContext(request,response,
                    request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
            html = thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);
            if (!StringUtils.isEmpty(html)){
                redisService.set(GoodsKey.getGoodsDetail,""+goodsId,html,Const.RedisCacheExtime.GOODS_iNFO);
            }
            return html;
        }
    }

    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(Model model,
                                        @PathVariable("goodsId")long goodsId,HttpServletRequest request){
        String loginToken = CookieUtil.readLoginToken(request);
        User user = redisService.get(UserKey.getByName,loginToken,User.class);

        GoodsBo goods = seckillGoodsService.getseckillGoodsBoByGoodsId(goodsId);
        if (goods == null){
            return Result.error(CodeMsg.NO_GOODS);
        }else {
            model.addAttribute("goods",goods);
            long startAt = goods.getStartDate().getTime();
            long endAt = goods.getEndDate().getTime();
            long now = System.currentTimeMillis();

            int miaoshaStatus = 0;
            int remainSeconds = 0;
            if (now < startAt){ //秒杀还还没开始，倒计时
                miaoshaStatus = 0;
                remainSeconds = (int)((startAt - now)/1000);
            }else if (now > endAt){
                miaoshaStatus = 2;
                remainSeconds = -1;
            }else {
                miaoshaStatus = 1;
                remainSeconds = 0;
            }
            GoodsDetailVo vo = new GoodsDetailVo();
            vo.setGoods(goods);
            vo.setUser(user);
            vo.setRemainSeconds(remainSeconds);
            vo.setMiaoshaStatus(miaoshaStatus);
            return Result.success(vo);
        }
    }

}
