package com.xs.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.User;
import com.xs.cms.service.ArticleService;

@RequestMapping("my")
@Controller
public class MyController {
	
	@Resource 
	private ArticleService service;

	/**
	 * 
	 * @Title: index 
	 * @Description: 进入个人中心的首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		
		return "my/index";
		
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 我的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,HttpSession  session, @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "4")Integer pageSize) {
		Article article = new Article();
		
		
		User user =(User) session.getAttribute("user");//从session获取当前登录人的信息
		article.setUserId(user.getId());//只显示当前登录的人文章
		PageInfo<Article> info = service.selects(article, page, pageSize);
		
		model.addAttribute("info", info);
		return "my/articles";
		
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 单个文章查询
	 * @param id
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id) {
		
		
		return service.select(id);
	}
	
	
	
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
		
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 发布文章
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,HttpSession  session  ,Article article) {
		if (!file.isEmpty() && null!=file) {
			String path = "d:/pic/";
			
			//原文件名称
			String filename = file.getOriginalFilename();
			
			String start = UUID.randomUUID().toString();
			
			String end = filename.substring(filename.lastIndexOf("."));
			
			String newFileName = start+end;
			
			File f = new File(path, newFileName);
			
			try {
				file.transferTo(f);
				article.setPicture(newFileName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//发布人
		article.setCreated(new Date());
		article.setHits(0);//点击量默认 0
		article.setDeleted(0);//默认未删除
		article.setHot(0);//默认非热门
		article.setStatus(0);//默认待审核
		
		return service.insert(article)>0;
	}
}
