package com.netease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.dao.UserMapper;
import com.netease.model.User;




@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public User findUserByLoginName(String username) {
		System.out.println("findUserByLoginName call!");
		return userMapper.findUserByLoginName(username);
	}

}
