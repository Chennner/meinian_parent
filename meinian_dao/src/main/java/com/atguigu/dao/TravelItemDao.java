package com.atguigu.dao;

import com.atguigu.pojo.TravelItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface TravelItemDao {
    void edit(TravelItem travelItem);

    void add(TravelItem travelItem);

    Page<TravelItem> findPage(String queryString);

    long findCountByTravelItemItemId(Integer id);

    void deleteById(Integer id);

    TravelItem findById(Integer id);

    List<TravelItem> findAll();

    List<TravelItem> findTravelItemListById(int id);


    //Page findPage(String queryString);
}
