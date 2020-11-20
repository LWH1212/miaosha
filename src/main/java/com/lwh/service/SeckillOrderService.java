package com.lwh.service;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.OrderInfo;
import com.lwh.pojo.SeckillOrder;
import com.lwh.pojo.User;

public interface SeckillOrderService {

    SeckillOrder getSeckillOrderByUserIdGoodsId(long userId,long goodsId);

    OrderInfo insert(User user, GoodsBo goodsBo);

    OrderInfo getOrderInfo(long orderId);

    long getSeckillResult(Long userId,long goodsId);

    boolean checkPath(User user,long goodsId,String path);

    String createMiaoshaPath(User user,long goodsId);
}
