package com.lwh.service.impl;

import com.lwh.bo.GoodsBo;
import com.lwh.mapper.GoodsMapper;
import com.lwh.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<GoodsBo> getSeckillGoodsList() {
        return goodsMapper.selectAllGoods();
    }

    @Override
    public GoodsBo getseckillGoodsBoByGoodsId(long goodsId) {
        return goodsMapper.getseckillGoodsBoByGoodsId(goodsId);
    }

    @Override
    public int reduceStock(long goodsId) {
        return goodsMapper.updateStock(goodsId);
    }
}
