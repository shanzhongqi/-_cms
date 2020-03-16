package com.xs.cms.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;
import com.xs.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleServiceImplTest {

	@Resource
	ArticleService articleService;
	
	@Test
	public void testInsert() {
		Article article = new Article();
		article.setTitle("test");
		article.setUserId(1);
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setContent("aaaaaaaaaaaaaaaaaaaa");
		article.setCreated(new Date());
		
		articleService.insert(article);
		
		
	}

	@Test
	public void testSelects() {
		PageInfo<Article> info = articleService.selects(null, 1, 10);
		System.out.println(info.getList());
	}

}
