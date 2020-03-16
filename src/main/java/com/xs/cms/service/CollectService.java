package com.xs.cms.service;

import com.xs.cms.domain.Collect;

public interface CollectService {

	int insert(Collect collect);
	
	int delete(Integer id);

	Collect selectByTitleAndUserId(String title, Integer id);
}
