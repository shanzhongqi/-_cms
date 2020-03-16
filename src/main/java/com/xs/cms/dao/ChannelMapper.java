package com.xs.cms.dao;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: ��Ŀ�����������
 * @author: ���
 * @date: 2020��3��5�� ����10:20:34
 */

import java.util.List;

import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;

public interface ChannelMapper {

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
