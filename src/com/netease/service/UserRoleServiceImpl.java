/**
 * 
 */
package com.netease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.UserRoleMapper;
import com.netease.model.UserRole;


/**
 * @author hzzhangyuandao
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public UserRole getPermissionById(int id) {
		
		return userRoleMapper.selectByPrimaryKey(id);
	}

}
