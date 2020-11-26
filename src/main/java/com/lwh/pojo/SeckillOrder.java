package com.lwh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SeckillOrder implements Serializable {

    private Long id;

    private Long userId;

    private Long orderId;

    private Long goodsId;

}
