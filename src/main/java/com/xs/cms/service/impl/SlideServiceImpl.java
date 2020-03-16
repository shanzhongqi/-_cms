package com.xs.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xs.cms.dao.SlideMapper;
import com.xs.cms.domain.Slide;
import com.xs.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Resource
	private SlideMapper slideMapper;
	
	
	@Override
	public List<Slide> selects() {
		return slideMapper.selects();
	}

}
