package com.wd.mapper;

import com.wd.entity.Role;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
