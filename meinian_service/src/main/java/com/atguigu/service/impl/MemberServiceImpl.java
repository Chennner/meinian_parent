package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.MemberDao;
import com.atguigu.pojo.Member;
import com.atguigu.service.MemberService;
import com.atguigu.util.DateUtils;
import com.atguigu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);

    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
/*        List<Integer> memberCountList = new ArrayList<>();
        if (monthsList!=null && monthsList.size()>0) {
            for (String months : monthsList) {
                String regTime = DateUtils.getLastDayOfMonth(months);
                Integer memberCount = memberDao.findMemberCountBeforeDate(regTime);
                memberCountList.add(memberCount);
            }
        }
        return memberCountList;*/
        List<Integer> list = new ArrayList<>();

        if(months!=null && months.size()>0){
            for (String month : months) {
                int count = memberDao.findMemberCountByMonth(month);
                list.add(count);
            }
        }

        return list;


    }
}
