package com.xs.cms.service;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;

public interface ArticleService {

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
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize);
	
	
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
}
