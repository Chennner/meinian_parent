package com.atguigu.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface PermissionDao {

    Set<PermissionDao> findPermissionsByRoleId(Integer roleId);
}
