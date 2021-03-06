package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findByid4Detail(Integer id);


    int getTodayOrderNumber(String date);
    int getTodayVisitsNumber(String date);
    int getThisWeekAndMonthOrderNumber(Map<String, Object> map);
    int getThisWeekAndMonthVisitsNumber(Map<String, Object> map);
    List<Map<String,Object>> findHotSetmeal();

}
