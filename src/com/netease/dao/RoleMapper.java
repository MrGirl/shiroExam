package com.netease.dao;

import java.util.List;

import com.netease.model.Role;

public interface RoleMapper {

    public Role selectByPrimaryKey(Integer id);
    
    public List<Role> getRolesByUsername(String username);

}