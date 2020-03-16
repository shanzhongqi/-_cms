package com.xs.cms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Comment 
 * @Description: 评论表
 * @author: 你爹
 * @date: 2020年3月14日 上午7:47:47
 */
public class Comment implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer userId;
	
	private Integer ArticleId;
	
	private String content;
	
	private Date created;
	
	private User user;
	
	private Article article;
	

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getArticleId() {
		return ArticleId;
	}

	public void setArticleId(Integer articleId) {
		ArticleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public Comment(Integer id, Integer userId, Integer articleId, String content, Date created, User user,
			Article article) {
		super();
		this.id = id;
		this.userId = userId;
		ArticleId = articleId;
		this.content = content;
		this.created = created;
		this.user = user;
		this.article = article;
	}

	public Comment() {
		super();
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", ArticleId=" + ArticleId + ", content=" + content
				+ ", created=" + created + ", user=" + user + "]";
	}
	
	
	
}
