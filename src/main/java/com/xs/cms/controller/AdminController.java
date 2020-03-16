package com.xs.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.User;
import com.xs.cms.service.ArticleService;
import com.xs.cms.service.UserService;

/**
 * 
 * @ClassName: AdminController 
 * @Description: 管理员中心
 * @author: 你爹
 * @date: 2020年3月6日 上午10:01:49
 */
@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService service;
	
	@Resource
	private UserService UserService;
	
	
	@RequestMapping(value = {"","/","index"})
	public String  admin() {
		
		return "admin/index";
	}
	
	
	/**
	 * 
	 * @Title: articles 
	 * @Description: 文章审核
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "4")Integer pageSize,Article article) {
		 
		 PageInfo<Article> info = service.selects(article, page, pageSize);
		  
		 model.addAttribute("info", info);
		 model.addAttribute("article", article);
		 
		
		return "admin/articles";
		
	}
	
	@RequestMapping("users")
	public String users(Model model,User user,@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "18") Integer pageSize) {
		
		PageInfo<User> info = UserService.selects(user, page, pageSize);
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		return "admin/users";
		
	}
	
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user) {
		
		return UserService.update(user) >0;
	}
	
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id) {
		
		return service.select(id);
		
	}
	
	
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article) {
		return service.update(article) >0;
	}
	
}
