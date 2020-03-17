package com.xs.cms.dao;

import java.util.List;

import com.xs.cms.domain.Article;

public interface ArticleMapper {

	/**
	 * 
	 * @Title: insert 
	 * @Description: ��������
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: �����б�
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);
	
	/**
	 * 
	 * @Title: select 
	 * @Description: �������²�ѯ
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article select(Integer id);
	
	
	int update(Article article);
	
	List<Article> list();
}
