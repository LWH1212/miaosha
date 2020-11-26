package com.lwh.service.impl;

import com.lwh.mapper.OrderInfoMapper;
import com.lwh.pojo.OrderInfo;
import com.lwh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSeviceImpl implements OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public long addOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insertSelective(orderInfo);
    }

    @Override
    public OrderInfo getOrderInfo(long orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<OrderInfo> getOrderList(long userId) {
        return orderInfoMapper.getOrderList(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKeySelective(record);
    }
}
