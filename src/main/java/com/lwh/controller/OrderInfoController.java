package com.lwh.controller;

import com.lwh.pojo.OrderInfo;
import com.lwh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/orderDetail/{orderId}")
    public String orderDetail(@PathVariable Long orderId,Model model){
        OrderInfo orderInfo = orderService.getOrderInfo(orderId);
        model.addAttribute("orderInfo",orderInfo);
        return "order_detail";
    }

    @RequestMapping("/myOrder/{userId}")
    public String getOrderListByUser(@PathVariable Long userId, Model model){
        List<OrderInfo> orderInfoList =  orderService.getOrderList(userId);
        model.addAttribute("orderList",orderInfoList);
        return "mySeckillOrder";
    }

}
