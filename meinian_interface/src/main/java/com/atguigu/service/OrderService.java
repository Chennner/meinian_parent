package com.atguigu.service;

import com.atguigu.entity.Result;

import java.util.Map;

public interface OrderService {

    Result order(Map map) throws Exception;

    Map findByid4Detail(Integer id);
}
