package com.xs.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xs.cms.dao.CommentMapper;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.Comment;
import com.xs.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper;
	@Override
	public int insert(Comment comment) {
		
		try {
			commentMapper.insert(comment);
			commentMapper.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public PageInfo<Comment> selest(Article article,Integer page,Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selest(article);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Article> selectByCommentNum(Integer page, Integer pageSize) {
			PageHelper.startPage(page, pageSize);
			
			List<Article> list = commentMapper.selectByCommentNum();
		
		
		return new PageInfo<Article>(list);
	}

}
