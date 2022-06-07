package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {



    public void add(Setmeal setmeal);

    public void setSetmealAndTravelGroup(Map<String, Integer> map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    Setmeal getSetmealById(int id);

    List<Map<String, Object>> findSetmealCount();
}
