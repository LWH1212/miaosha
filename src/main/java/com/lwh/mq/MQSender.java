package com.lwh.mq;

import com.lwh.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSeckillMessage(SeckillMessage mm){
        String msg = RedisService.beanToString(mm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,msg);
    }

    public void sendOrderMessage(OrderMessage orderMessage){
        String msg = RedisService.beanToString(orderMessage);
        log.info("send orderMessage:"+msg);
        amqpTemplate.convertAndSend(MQConfig.PAY_QUEUE,msg);
    }
}
