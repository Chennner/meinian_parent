package com.atguigu.dao;

import com.atguigu.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberDao {

    Member findByTelephone(String telephone);

    void add(Member member);

    int findMemberCountByMonth(String month);

    /*  Integer findMemberCountBeforeDate(String regTime);*/

    public Integer findMemberCountBeforeDate(String date);

    List<Map<String, Object>> findSetmealCount();
    int getTodayNewMember(String date);
    int getTotalMember();
    int getThisWeekAndMonthNewMember(String date);




}
