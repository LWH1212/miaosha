package com.lwh.redis;

public class GoodsKey extends BasePrefix {

    private GoodsKey(String prefix){
        super(prefix);
    }

//    public static GoodsKey getGoodsList = new GoodsKey("gl");

    public static GoodsKey getGoodsList = new GoodsKey("goodsList");

    public static GoodsKey getGoodsAllList = new GoodsKey("goodsAll");

    public static GoodsKey getGoodsDetail = new GoodsKey("gd");

    public static GoodsKey getGoodsEntity= new GoodsKey("ge");

    public static GoodsKey getSeckillGoodsStock = new GoodsKey("gs");

}
