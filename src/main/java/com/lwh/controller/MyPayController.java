package com.lwh.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lwh.config.AlipayConfig;
import com.lwh.mq.*;
import com.lwh.pojo.OrderInfo;
import com.lwh.redis.RedisService;
import com.lwh.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller(value="MyPayController")
@RequestMapping("/myPay")
public class MyPayController {
	
	//private static final Logger LOG = LoggerFactory.getLogger(MyPayController.class);
    private static Logger log = LoggerFactory.getLogger(MyPayController.class);


	@Autowired
    private MQSender mqSender;

	@Autowired
    OrderService orderService;

	
	@RequestMapping("toNotify")
	public String toNotify() {
		return "index/center/notify";
	}


	@RequestMapping("succ")
	public String succ(HttpSession session) {
        Object orderId = session.getAttribute("orderId");
        //入队
        OrderMessage om = new OrderMessage();
        om.setOrderId((long)orderId);
        mqSender.sendOrderMessage(om);
        return "redirect:/orderInfo/orderDetail/"+orderId;
	}
	
	@RequestMapping("payOrder/{orderId}")
	public void pay(@PathVariable("orderId") long orderId, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, AlipayApiException {
        session.setAttribute("orderId", orderId);
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("tradeNo").getBytes("UTF-8"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("amount").getBytes("UTF-8"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("name").getBytes("UTF-8"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("desc").getBytes("UTF-8"),"UTF-8");
        
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        
        //请求
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }




        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
	}
	
}
