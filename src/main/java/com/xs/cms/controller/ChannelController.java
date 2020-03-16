package com.xs.cms.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xs.cms.service.ChannelService;

@RequestMapping("channel")
@Controller
public class ChannelController {

	//ÔÂ¿¼Ìá½»
	@Resource
	private ChannelService service;
	
	@ResponseBody
	@RequestMapping("channels")
	public Object selects() {
		return service.selects();
	}
	
	@ResponseBody
	@RequestMapping("selectsByChannelId")
	public Object selectsByChannelId(Integer channelId) {
		return service.selectsByChannelId(channelId);
	}
}
