package com.xs.cms.dao;

import java.util.List;

import com.xs.cms.domain.Article;
import com.xs.cms.domain.Comment;

public interface CommentMapper {

	/**
	 * 
	 * @Title: insert 
	 * @Description: ��������
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	
	/**
	 * 
	 * @Title: selest 
	 * @Description: �������²�ѯ����
	 * @param article
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selest(Article article);
	
	
	List<Article> selectByCommentNum();
	
	int updateArticle(Integer artcileId);
}
