package com.lwh.mq;

import com.lwh.bo.GoodsBo;
import com.lwh.pojo.OrderInfo;
import com.lwh.pojo.SeckillOrder;
import com.lwh.pojo.User;
import com.lwh.redis.RedisService;
import com.lwh.service.OrderService;
import com.lwh.service.SeckillGoodsService;
import com.lwh.service.SeckillOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    SeckillGoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillOrderService seckillOrderService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message){
        log.info("receive message: "+message);
        SeckillMessage mm = RedisService.stringToBean(message,SeckillMessage.class);
        User user = mm.getUser();
        long goodsId = mm.getGoodsId();

        GoodsBo goods = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0){
            return;
        }
        //判断是否已经秒杀到了
        SeckillOrder order = seckillOrderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null){
            return;
        }
        //减库存 下订单 写入秒杀订单
        seckillOrderService.insert(user,goods);
    }

    @RabbitListener(queues = MQConfig.PAY_QUEUE)
    public void receiveOrder(String message){
        log.info("receive orderMessage: "+message);
        OrderMessage mm = RedisService.stringToBean(message,OrderMessage.class);
        long orderId = mm.getOrderId();
        OrderInfo order = orderService.getOrderInfo(orderId);
        order.setPayDate(new Date());
        order.setStatus(1);
        orderService.updateByPrimaryKeySelective(order);
    }

}
