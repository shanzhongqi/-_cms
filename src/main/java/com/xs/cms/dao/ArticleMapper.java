package com.xs.cms.dao;

import java.util.List;

import com.xs.cms.domain.Article;

public interface ArticleMapper {

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
	List<Article> selects(Article article);
	
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
	
	List<Article> list();
}
