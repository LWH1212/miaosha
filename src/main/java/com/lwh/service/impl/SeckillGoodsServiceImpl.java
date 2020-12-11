package com.lwh.service.impl;

import com.lwh.bo.GoodsBo;
import com.lwh.mapper.GoodsMapper;
import com.lwh.mapper.SeckillGoodsMapper;
import com.lwh.pojo.SeckillGoods;
import com.lwh.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SeckillGoodsMapper seckillGoodsMapper;

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

    @Override
    public int insert(SeckillGoods seckillGoods) {
        return seckillGoodsMapper.insert(seckillGoods);
    }

    @Override
    public SeckillGoods selectByGoodsId(Long goodsId) {
        return seckillGoodsMapper.selectByGoodsId(goodsId);
    }

    @Override
    public int batchInsert(List<SeckillGoods> seckillGoodsList) {
        return seckillGoodsMapper.batchInsert(seckillGoodsList);
    }
}
