/**
 * 
 */
package com.netease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.PermissionMapper;
import com.netease.model.Permission;


/**
 * @author hzzhangyuandao
 *
 */
@Service
public class PermissionServiceImpl  implements PermissionService {

	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Permission getPermissionById(int id) {
		
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Permission> getPermissionByRoleId(int roleId) {
		
		return permissionMapper.getPermissionByRoleId(roleId);
	}

}
