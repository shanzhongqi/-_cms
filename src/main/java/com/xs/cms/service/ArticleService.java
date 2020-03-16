package com.xs.cms.service;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;

public interface ArticleService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章列表
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	
	
	/**
	 * 
	 * @Title: select 
	 * @Description: 单个文章查询
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article select(Integer id);
	
	
	int update(Article article);
}
