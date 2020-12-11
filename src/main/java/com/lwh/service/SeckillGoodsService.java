package com.lwh.service;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.SeckillGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeckillGoodsService {

    List<GoodsBo> getSeckillGoodsList();

    GoodsBo getseckillGoodsBoByGoodsId(long goodsId);

    int reduceStock(long goodsId);

    int insert(SeckillGoods seckillGoods);

    SeckillGoods selectByGoodsId(Long goodsId);

    int batchInsert(List<SeckillGoods> seckillGoodsList);

}
