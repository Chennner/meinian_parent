package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/travelItem")
public class TravelItemController {
    @Reference
    private TravelItemService traveltemService;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('TRAVELITEM_ADD')")//权限校验
    public Result add(@RequestBody TravelItem travelItem){
        try {
            traveltemService.add(travelItem);

        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }

        return new Result(true,MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = traveltemService.findPage(
                queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString()
        );
        return  pageResult;
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('TRAVELITEM_DELETE')")//权限校验
    public Result delete(Integer id){
        try {
            traveltemService.deleteById(id);
            return new Result(true,MessageConstant.DELETE_MEMBER_SUCCESS);
        }catch (RuntimeException e) {
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_MEMBER_FAIL);
        }
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            TravelItem travelItem = traveltemService.findById(id);
            return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
    }
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('TRAVELITEM_EDIT')")//权限校验
    public Result edit(@RequestBody TravelItem travelItem){

        try {
            traveltemService.edit(travelItem);
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<TravelItem> travelItems = traveltemService.findAll();
            return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelItems);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
    }






}
