package com.lwh.vo;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GoodsDetailVo implements Serializable {

    private int miaoshaStatus = 0;

    private int remainSeconds = 0;

    private GoodsBo goods;

    private User user;

}
