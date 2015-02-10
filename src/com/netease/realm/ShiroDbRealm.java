package com.netease.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netease.model.Permission;
import com.netease.model.Role;
import com.netease.model.User;
import com.netease.service.PermissionService;
import com.netease.service.RolePermissionService;
import com.netease.service.RoleService;
import com.netease.service.UserRoleService;
import com.netease.service.UserService;
import com.netease.util.CipherUtil;
import com.netease.util.EncryptUtils;




public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	private static final String ALGORITHM = "MD5";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RolePermissionService rolePermissionService;

	public ShiroDbRealm() {
		super();
	}
	
	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token.getUsername());
		User user = null;
		try{
		user = userService.findUserByLoginName(token.getUsername());
		}catch(Exception e){
			logger.error("db query error.", e);
		}
		System.out.println(user);
		if (user != null) {
			return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
		}else{
			throw new AuthenticationException();
		}
		
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/* 授权校验代码 */
		Set<String> roleNames = new HashSet<String>();
	    Set<String> permissions = new HashSet<String>();
	    
	    //get roles and permission
	    String username = (String) this.getAvailablePrincipal(principals);
	    logger.debug("user "+username+"logged.");
	    List<Role> roles= roleService.getRolesByUsername(username);
	    
	    List<Permission> allPermission = new ArrayList<Permission>();
	    for(Role role:roles){
	    	roleNames.add(role.getName());
	    	List<Permission> tempRoles = permissionService.getPermissionByRoleId(role.getId());
	    	allPermission.addAll(tempRoles);
	    }
	    
	    for(Permission permission:allPermission){
	    	String permissionStr = permission.getPermission();
	    	
	    	String resource = permission.getResource();
	    	if(resource !=null && resource.length() != 0){
	    		permissionStr += ":" +resource; 
	    	}
	    	
	    	permissions.add(permissionStr);
	    }
	       
	    
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);

		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

//	@PostConstruct
//	public void initCredentialsMatcher() {//MD5加密
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
//		setCredentialsMatcher(matcher);
//	}
}
