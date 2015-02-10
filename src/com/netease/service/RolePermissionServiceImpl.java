/**
 * 
 */
package com.netease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.RolePermissionMapper;
import com.netease.model.RolePermission;


/**
 * @author hzzhangyuandao
 *
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public RolePermission getPermissionById(int id) {
		
		return rolePermissionMapper.selectByPrimaryKey(id);
	}

}
