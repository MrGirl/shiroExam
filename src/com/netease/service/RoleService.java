/**
 * 
 */
package com.netease.service;

import java.util.List;

import com.netease.model.Role;

/**
 * @author hzzhangyuandao
 *
 */
public interface RoleService {

	public Role getRoleById(int id);
	
	public List<Role> getRolesByUsername(String username);
	
	
}
