package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {

    public void add(TravelGroup travelGroup);

    public void setTravelGroupAndTravelItem(Map<String, Integer> map);


    Page<TravelGroup> findPage(@Param("queryString") String queryString);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelgroupId(Integer id);

    void edit(TravelGroup travelGroup);


    void deleteTravelGroupAndTravelItemByTravelGroupId(Integer id);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupListById(int id);
}
