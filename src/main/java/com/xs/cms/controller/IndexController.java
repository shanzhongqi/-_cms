package com.xs.cms.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;
import com.xs.cms.domain.Collect;
import com.xs.cms.domain.Comment;
import com.xs.cms.domain.Slide;
import com.xs.cms.domain.User;
import com.xs.cms.service.ArticleService;
import com.xs.cms.service.ChannelService;
import com.xs.cms.service.CollectService;
import com.xs.cms.service.CommentService;
import com.xs.cms.service.SlideService;

@Controller
public class IndexController {

	@Resource
	private ChannelService channelService;
	@Resource
	private ArticleService articleService;
	
	@Resource
	private SlideService slideService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private CollectService collectService;

	@RequestMapping(value = { "", "/", "index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		model.addAttribute("article", article);
		article.setStatus(1);//只显示审核过的文章
		article.setDeleted(0);//只显示未删除
		// 查询左侧栏目
		List<Channel> channels = channelService.selects();
		model.addAttribute("channels", channels);

		if (article.getChannelId() != null) {
			List<Category> categorys = channelService.selectsByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);
		}
		
		if (article.getChannelId()==null) {
			article.setHot(1);
			
			List<Slide> slides = slideService.selects();
			model.addAttribute("slides", slides);
		}
		
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		model.addAttribute("info", info);
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.selects(new Article(), 1, 10);
		model.addAttribute("lastArticles", lastArticles);
		
		
		return "index/index";
	}

	/**
	 * 
	 * @Title: articleDetail
	 * @Description: 文章详情
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(Integer id, Model m,@RequestParam(defaultValue  = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize,HttpSession session) {
		Article article = articleService.select(id);
		m.addAttribute("article", article);
		
		PageInfo<Comment> info = commentService.selest(article, page, pageSize);
		
		PageInfo<Article> info2 = commentService.selectByCommentNum(1, 5);
		m.addAttribute("info", info);
		m.addAttribute("info2", info2);
		User user = (User) session.getAttribute("user");
		  Collect collect =null;
			if(null !=user) {//如果用户已经登录，则查询是否没收藏过
			   collect = collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
			}
			m.addAttribute("collect", collect);
		return "index/article";
	}
	
	
	
	@ResponseBody
	@RequestMapping("addComment")
	public Object addComment(Comment comment,Integer articleId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user==null) {
			return false;
		}
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());
		return commentService.insert(comment)>0;
	}
	
	
	@ResponseBody
	@RequestMapping("collect")
	public Object collect(HttpSession session,Collect collect) {
		User user = (User) session.getAttribute("user");
		if (user==null) {
			return false;
		}
		
		collect.setUser(user);
		collect.setCreated(new Date());
		return collectService.insert(collect)>0;
	}
	
	@ResponseBody
	@RequestMapping("deleteCollect")
	public Object deleteCollect(HttpSession session,Collect collect) {
		User user = (User) session.getAttribute("user");
		if (user==null) {
			return false;
		}
		return collectService.delete(collect.getId())>0;
	}

}


