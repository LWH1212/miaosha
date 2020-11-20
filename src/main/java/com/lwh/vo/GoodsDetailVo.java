package com.lwh.vo;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsDetailVo {

    private int miaoshaStatus = 0;

    private int remainSeconds = 0;

    private GoodsBo goods;

    private User user;

}
