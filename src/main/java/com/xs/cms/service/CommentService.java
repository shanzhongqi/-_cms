package com.xs.cms.service;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.Comment;

public interface CommentService {

	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	
	/**
	 * 
	 * @Title: selest 
	 * @Description: 根据文章查询评论
	 * @param article
	 * @return
	 * @return: List<Comment>
	 */
	PageInfo<Comment> selest(Article article, Integer page, Integer pageSize);
	
	
	PageInfo<Article> selectByCommentNum(Integer page,Integer pageSize);
	
	
	
}
