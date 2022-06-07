package com.atguigu;

import redis.clients.jedis.Jedis;

public class testRedis {
    public static void main(String[] args) {
        Jedis jedis =new Jedis("192.168.253.100",6379);
        System.out.println(jedis.ping());
    }
}
