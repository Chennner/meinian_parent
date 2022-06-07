package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.constant.RedisMessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderMobileController {

    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");
        String codeInRedis = jedisPool.getResource().get(telephone+ RedisMessageConstant.SENDTYPE_ORDER);
        if (codeInRedis==null || !codeInRedis.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result =null;
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result=orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        if (result.isFlag()){
            String orderDate = (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage(telephone,orderDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;//接收的result是什么flag，message，data，
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Map map=null;
        try {
            map=orderService.findByid4Detail(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.QUERY_ORDER_FAIL);

        }

    }
}














