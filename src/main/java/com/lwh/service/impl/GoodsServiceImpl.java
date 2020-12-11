package com.lwh.service.impl;

import com.lwh.mapper.GoodsMapper;
import com.lwh.pojo.Goods;
import com.lwh.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAddStatus(long id) {
        return goodsMapper.updateAddStatus(id);
    }

    @Override
    public int batchDelete(String[] ids) {
        return goodsMapper.batchDelete(ids);
    }

    @Override
    public List<Goods> selectByIds(String[] ids) {
        return goodsMapper.selectByIds(ids);
    }
}
