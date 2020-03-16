package com.xs.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.cms.dao.ArticleMapper;
import com.xs.cms.domain.Article;
import com.xs.cms.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleMapper dao;
	
	@Override
	public int insert(Article article) {
		return dao.insert(article);
	}

	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list = dao.selects(article);
		return new PageInfo<Article>(list);
	}

	@Override
	public Article select(Integer id) {
		
		return dao.select(id);
	}

	@Override
	public int update(Article article) {
		return dao.update(article);
	}


}
