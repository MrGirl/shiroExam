package com.netease.service;

import com.netease.model.User;

public interface UserService {
	public User getUserById(int id);

	public User findUserByLoginName(String username);
}
