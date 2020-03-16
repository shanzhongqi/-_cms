package com.xs.cms.dao;

import java.util.List;

import com.xs.cms.domain.User;

public interface UserMapper {

	/**
	 * 
	 * @Title: selects 
	 * @Description: �û��б�
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(User user);
	/**
	 * 
	 * @Title: update 
	 * @Description: ����
	 * @param user
	 * @return
	 * @return: int
	 */
	int update(User user);
	
	
	
	int insert(User user);
	
	
	User selectByUsername(String username);
}
