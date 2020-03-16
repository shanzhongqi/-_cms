package com.xs.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xs.cms.dao.ChannelMapper;
import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;
import com.xs.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{

	@Resource
	private ChannelMapper dao;
	
	@Override
	public List<Channel> selects() {
		return dao.selects();
	}

	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		return dao.selectsByChannelId(channelId);
	}

}
