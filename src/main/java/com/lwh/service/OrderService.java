package com.lwh.service;

import com.lwh.pojo.OrderInfo;

public interface OrderService {

    long addOrder(OrderInfo orderInfo);

    OrderInfo getOrderInfo(long orderId);

}
