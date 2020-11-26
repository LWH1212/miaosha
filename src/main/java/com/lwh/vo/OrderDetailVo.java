package com.lwh.vo;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.OrderInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrderDetailVo implements Serializable {

    private GoodsBo goods;

    private OrderInfo order;

}
