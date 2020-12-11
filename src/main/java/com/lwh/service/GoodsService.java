package com.lwh.service;

import com.lwh.pojo.Goods;

import java.util.List;

public interface GoodsService {

    int insert(Goods goods);

    List<Goods> findAll();

    Goods selectByPrimaryKey(Long id);

    int updateAddStatus(long id);

    int batchDelete(String[] ids);

    List<Goods> selectByIds(String[] ids);

}
