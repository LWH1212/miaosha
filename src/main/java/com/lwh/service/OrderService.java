package com.lwh.service;

import com.lwh.pojo.OrderInfo;

import java.util.List;

public interface OrderService {

    long addOrder(OrderInfo orderInfo);

    OrderInfo getOrderInfo(long orderId);

    List<OrderInfo> getOrderList(long userId);

    int updateByPrimaryKeySelective(OrderInfo record);

}
