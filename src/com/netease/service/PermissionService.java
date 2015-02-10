/**
 * 
 */
package com.netease.service;

import java.util.List;

import com.netease.model.Permission;

/**
 * @author hzzhangyuandao
 *
 */
public interface PermissionService {

	public Permission getPermissionById(int id);
	
	public List<Permission> getPermissionByRoleId(int roleId);
}
