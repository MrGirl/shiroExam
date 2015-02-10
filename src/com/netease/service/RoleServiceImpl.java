/**
 * 
 */
package com.netease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.RoleMapper;
import com.netease.model.Role;


/**
 * @author hzzhangyuandao
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	/* (non-Javadoc)
	 * @see com.netease.service.RoleService#getRoleById(int)
	 */
	@Override
	public Role getRoleById(int id) {
		
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> getRolesByUsername(String username) {
		
		return roleMapper.getRolesByUsername(username);
	}

}
