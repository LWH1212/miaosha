package com.lwh.controller;

import com.lwh.bo.GoodsBo;
import com.lwh.common.Const;
import com.lwh.pojo.Goods;
import com.lwh.pojo.User;
import com.lwh.redis.GoodsKey;
import com.lwh.redis.RedisService;
import com.lwh.redis.UserKey;
import com.lwh.result.CodeMsg;
import com.lwh.result.Result;
import com.lwh.service.GoodsService;
import com.lwh.service.SeckillGoodsService;
import com.lwh.util.CookieUtil;
import com.lwh.util.UploadImageUtil;
import com.lwh.vo.GoodsDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    RedisService redisService;

    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/goodsList")
    public String goodsList(Model model){
        List<Goods> goodsAll = (List<Goods>) redisService.getList("goodsAll");
        model.addAttribute("goodsAll",goodsAll);
        if (goodsAll == null || goodsAll.size() == 0 || goodsAll.isEmpty()){
            List<Goods> goods = goodsService.findAll();
            redisService.setList("goodsAll",goods,60*30);
            model.addAttribute("goodsAll",goods);
        }
        return "goods";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session){
//        String html = redisService.get(GoodsKey.getGoodsList,"",String.class);
//        if (!StringUtils.isEmpty(html)){
//            return html;
//        }

        List<GoodsBo> goodsBos = (List<GoodsBo>) redisService.getList("goodsList");
        model.addAttribute("goodsList",goodsBos);
        if (goodsBos == null || goodsBos.size() == 0 || goodsBos.isEmpty()){
            List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
            redisService.setList("goodsList",goodsList,60*30);
            model.addAttribute("goodsList",goodsList);
        }

        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap(),applicationContext);
        //手动渲染
        String html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
//        if (!StringUtils.isEmpty(html)){
////            redisService.set(GoodsKey.getGoodsList,"",html, Const.RedisCacheExtime.GOODS_LIST);
//        }
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

    @RequestMapping("/addGoods")
    public String addGoods(@RequestParam("fileName")MultipartFile file, Goods goods,HttpServletRequest request){
        UploadImageUtil uploadImageUtil = new UploadImageUtil();
        uploadImageUtil.uploadOneImage(goods,request,file,"D:/lwh/Seckill-master/miaosha/src/main/resources/static/img");
        String fileName = uploadImageUtil.getFileName();
        goods.setGoodsImg("/img/"+fileName);
        goods.setCreateDate(new Date());
        goods.setUpdateDate(new Date());
        goods.setAddStatus(0);
        String goodsDetail = request.getParameter("goodsTitle");
        goods.setGoodsDetail(goodsDetail);
        goodsService.insert(goods);
        redisService.delRedis("goodsAll");
        return "redirect:/goods/goodsList";
    }

    @RequestMapping("/goodsDetail/{id}")
    public String goods_detail(@PathVariable("id") long goodsId,Model model){
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        model.addAttribute("goods",goods);
        return "goods_detail";
    }

    @RequestMapping("batchDelete")
    @ResponseBody
    public void batchDelete(String[] ids){
        goodsService.batchDelete(ids);
        redisService.delRedis("goodsAll");
    }

    @RequestMapping("selectByIds")
//    @ResponseBody
    public String selectByIds(String[] ids,Model model){
       List<Goods> goodsByIds =  goodsService.selectByIds(ids);
       model.addAttribute("goodsByIds",goodsByIds);
        return "addmore";

//        redisService.delRedis("goodsAll");
    }



}
