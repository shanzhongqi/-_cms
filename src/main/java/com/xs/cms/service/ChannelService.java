package com.xs.cms.service;

import java.util.List;

import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;

public interface ChannelService {


	/**
	 * 
	 * @Title: selects 
	 * @Description: 查询所有栏目信息
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
	

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 根据栏目id查找分类
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
