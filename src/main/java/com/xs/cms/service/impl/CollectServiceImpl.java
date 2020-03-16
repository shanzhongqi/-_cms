package com.xs.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoshan.common.utils.StringUtil;
import com.xs.cms.dao.CollectMapper;
import com.xs.cms.domain.Collect;
import com.xs.cms.service.CollectService;
import com.xs.cms.util.CMSException;
@Service
public class CollectServiceImpl implements CollectService {

	@Resource
	private CollectMapper collectMapper;
	
	@Override
	public int insert(Collect collect) {
		boolean b = StringUtil.isHttpUrl(collect.getUrl());
		if (!b) {
			throw new CMSException("不是合法的url");
			
		}
		
		
		return collectMapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		return collectMapper.delete(id);
	}

	@Override
	public Collect selectByTitleAndUserId(String title, Integer id) {
		return collectMapper.selectByTitleAndUserId(title,id);
	}

}
