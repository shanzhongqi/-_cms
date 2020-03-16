package com.xs.cms.service;

import java.util.List;

import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;

public interface ChannelService {


	/**
	 * 
	 * @Title: selects 
	 * @Description: ��ѯ������Ŀ��Ϣ
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
	

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: ������Ŀid���ҷ���
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
