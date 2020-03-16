package com.xs.cms.dao;

import org.apache.ibatis.annotations.Param;

import com.xs.cms.domain.Collect;

public interface CollectMapper {

	int insert(Collect collect);
	
	int delete(Integer id);

	int select(String text);

	Collect selectByTitleAndUserId(@Param(value = "text")String title, @Param(value = "userId")Integer id);
}
