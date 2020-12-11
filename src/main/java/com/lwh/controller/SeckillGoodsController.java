package com.lwh.controller;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.SeckillGoods;
import com.lwh.redis.GoodsKey;
import com.lwh.redis.RedisService;
import com.lwh.result.CodeMsg;
import com.lwh.result.Result;
import com.lwh.service.GoodsService;
import com.lwh.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @PostMapping("/add")
    @ResponseBody
    public Result<SeckillGoods> add(SeckillGoods seckillGoods, @RequestParam("goodsId") long goodsId){
        SeckillGoods goods = seckillGoodsService.selectByGoodsId(goodsId);
        if (goods == null){
            seckillGoodsService.insert(seckillGoods);
            goodsService.updateAddStatus(goodsId);
            redisService.del(GoodsKey.getGoodsList,"");
            redisService.del(GoodsKey.getGoodsAllList,"");
            return Result.success(seckillGoods);
        }
        return Result.error(CodeMsg.REPEATE_ADDMIAOSHA);
    }

    @PostMapping("/batchAdd")
    @ResponseBody
    public Result batchAdd(@RequestBody List<SeckillGoods> seckillGoodsList)  {
        seckillGoodsService.batchInsert(seckillGoodsList);
        long goodsId = seckillGoodsList.get(0).getGoodsId();
        GoodsBo good = seckillGoodsService.getseckillGoodsBoByGoodsId(goodsId);
        if (good != null){
            return Result.error(CodeMsg.REPEATE_ADDMIAOSHA);
        }
        goodsService.updateAddStatus(goodsId);
        System.out.println(goodsId);
        return Result.success(goodsId);

    }

}
