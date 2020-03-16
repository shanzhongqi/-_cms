package com.xs.cms.service;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.User;

public interface UserService {

	int update(User user);

	PageInfo<User> selects(User user, Integer page, Integer pageSize);

	int insert(User user);

	User selectByUsername(String username);

	User login(User user);
}
