package com.lwh.vo;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.OrderInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDetailVo {

    private GoodsBo goods;

    private OrderInfo order;

}
