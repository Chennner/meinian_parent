package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;

import java.util.List;

public interface TravelGroupService {

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(TravelGroup travelGroup, Integer[] travelItemIds);

    TravelGroup findById(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    List<TravelGroup> findAll( );
}
