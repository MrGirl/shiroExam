package com.netease.dao;

import java.util.List;

import com.netease.model.Permission;

public interface PermissionMapper {

    Permission selectByPrimaryKey(Integer id);

    
    public List<Permission> getPermissionByRoleId(int roleId);
}