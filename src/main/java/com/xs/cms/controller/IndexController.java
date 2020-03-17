package com.xs.cms.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.xiaoshan.common.utils.NumberUtils;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.Category;
import com.xs.cms.domain.Channel;
import com.xs.cms.domain.Collect;
import com.xs.cms.domain.Comment;
import com.xs.cms.domain.ContentType;
import com.xs.cms.domain.Slide;
import com.xs.cms.domain.User;
import com.xs.cms.domain.Vote;
import com.xs.cms.service.ArticleService;
import com.xs.cms.service.ChannelService;
import com.xs.cms.service.CollectService;
import com.xs.cms.service.CommentService;
import com.xs.cms.service.SlideService;
import com.xs.cms.service.VoteService;

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
	
	@Resource
	private VoteService voteService;

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
		
		Article article3 = new Article();
		
		article3.setContentType(ContentType.VOTE);
		
		PageInfo<Article> info2 = articleService.selects(article3, 1, 5);
		model.addAttribute("info2", info2);
		
		
		
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
	
	
	//去投票
	@RequestMapping("voteDo")
	public String voteDo(Integer id, Model m,@RequestParam(defaultValue  = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize,HttpSession session) {
		
		Article article = articleService.select(id);
		
		String content = article.getContent();
		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		LinkedHashMap<Character, String> mapVote = gson.fromJson(content, LinkedHashMap.class);
		
		m.addAttribute("article", article); 
		m.addAttribute("mapVote", mapVote); 
		
		//查询投票情况
		List<Vote> list = voteService.selects(article.getId());
		for (Vote vote : list) {
			vote.setOption(vote.getOption());
			
			//计算百分比
			vote.setPercent(new BigDecimal(NumberUtils.getPercent(vote.getOptionNum(), vote.getTotalNum())));
		}
		m.addAttribute("votes", list);
		
		return "index/vote";
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
	
	@RequestMapping("toVote")
	public String toVote() {
		
		return "/my/pushVote";
		 
	}
	
	@ResponseBody
	@RequestMapping("vote")
	public Object vote(Vote vote,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(null ==user)
		return false;//没有登录的用户不能收藏
		vote.setUserId(user.getId());
		//检查用户是否已经投过票
		if(voteService.select(vote)!=null)
			return false;
		
		return voteService.insert(vote)>0;
		 
	}
	
	

}


