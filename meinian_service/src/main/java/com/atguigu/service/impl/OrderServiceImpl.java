package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.MessageConstant;
import com.atguigu.dao.MemberDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Member;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderService;
import com.atguigu.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;


    @Override
    public Result order(Map map) throws Exception {

        //检查当前日期是否进行了预约设置
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);


        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (orderSetting==null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }else{
            int number = orderSetting.getNumber();
            int reservations = orderSetting.getReservations();
            if (number<=reservations){
                return new Result(false, MessageConstant.ORDER_FULL);
            }
        }
        String telephone = (String) map.get("telephone");


        Member member = memberDao.findByTelephone(telephone);
        if (member!=null){
            Integer memberId = member.getId();
            int setmealId= Integer.parseInt((String)map.get("setmealId"));
            //如果是会员，防止重复预约（一个会员、一个时间、一个套餐不能重复，否则是重复预约）
            //所以只要order的memberId,date，setmealId属性
            Order order =new Order(memberId,date,null,null,setmealId);
            List<Order> list = orderDao.findByCondition(order);
            if (list!=null&&list.size()>0){
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }else{
            // 如果不是会员：注册会员，向会员表中添加数据
            member = new Member();
            member.setName((String)map.get("name"));
            member.setSex((String)map.get("sex"));
            member.setPhoneNumber((String)map.get("telephone"));
            member.setIdCard((String)map.get("idCard"));
            member.setRegTime(new Date()); // 会员注册时间，当前时间
            memberDao.add(member);
        }
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        //（4）可以进行预约，向预约表中添加1条数据
        //保存预约信息到预约表
        Order order = new Order();
        order.setMemberId(member.getId()); //会员id
        order.setOrderDate(date); // 预约时间
        order.setOrderStatus(Order.ORDERSTATUS_NO); // 预约状态（已出游/未出游）
        order.setOrderType((String)map.get("orderType"));
        order.setSetmealId(Integer.parseInt((String)map.get("setmealId")));
        orderDao.add(order);

        return new Result(true, MessageConstant.ORDER_SUCCESS, order);

    }

    @Override
    public Map findByid4Detail(Integer id) {
        Map map=orderDao.findByid4Detail(id);
        try {
            if (map!=null){
                Date orderDate = (Date) map.get("orderDate");
                map.put("orderDate",DateUtils.parseDate2String(orderDate));
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }
}














