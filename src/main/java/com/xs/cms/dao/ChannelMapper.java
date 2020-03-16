package com.xs.cms.dao;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目分类二级联动
 * @author: 你爹
 * @date: 2020年3月5日 上午10:20:34
 */

import java.util.List;

import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;

public interface ChannelMapper {

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
