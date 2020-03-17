package com.xs.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.google.gson.Gson;
import com.xs.cms.domain.Article;
import com.xs.cms.domain.ChooseResult;
import com.xs.cms.domain.ContentType;
import com.xs.cms.domain.User;
import com.xs.cms.service.ArticleService;
import com.xs.cms.service.ChooseService;

@RequestMapping("my")
@Controller
public class MyController {
	
	@Resource 
	private ArticleService service;
	
	@Resource 
	private ChooseService chooseService;

	/**
	 * 
	 * @Title: index 
	 * @Description: ����������ĵ���ҳ
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
	 * @Description: �ҵ�����
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,HttpSession  session, @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "4")Integer pageSize) {
		Article article = new Article();
		
		
		User user =(User) session.getAttribute("user");//��session��ȡ��ǰ��¼�˵���Ϣ
		article.setUserId(user.getId());//ֻ��ʾ��ǰ��¼��������
		PageInfo<Article> info = service.selects(article, page, pageSize);
		
		model.addAttribute("info", info);
		return "my/articles";
		
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: �������²�ѯ
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
	 * @Description: ��������
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,HttpSession  session  ,Article article) {
		if (!file.isEmpty() && null!=file) {
			String path = "d:/pic/";
			
			//ԭ�ļ�����
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
		article.setUserId(user.getId());//������
		article.setCreated(new Date());
		article.setHits(0);//�����Ĭ�� 0
		article.setDeleted(0);//Ĭ��δɾ��
		article.setHot(0);//Ĭ�Ϸ�����
		article.setStatus(0);//Ĭ�ϴ����
		article.setContentType(ContentType.HTML);
		return service.insert(article)>0;
	}
	
	@ResponseBody
	@RequestMapping("pushVote")
	public Object pushVote(String[] content,Article article,HttpSession session) {
		LinkedHashMap<Character,String> map = new LinkedHashMap<Character, String>();
		char x = 'A';
		for (String string : content) {
			map.put(x, string);
			x = (char) (x+1);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		article.setContent(json);
		article.setContentType(ContentType.VOTE);
		
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setCreated(new Date());
		article.setHits(0);
		article.setDeleted(0);
		article.setHot(0);
		article.setStatus(1);
		
		return service.insert(article)>0;
	}
	
	
	@RequestMapping("toChoose")
	public String toChoose(Model m,HttpSession session) {
		List<Article> articles = service.list();
		m.addAttribute("articles", articles);
		
		
		return "my/choose";
	}
	
	@ResponseBody
	@RequestMapping("choose")
	public Object choose(HttpSession session) {
		List<Article> articles = service.list();
		
		
		
		return false;
	}
		
	
	@ResponseBody
	@RequestMapping("chooses")
	public Object chooses(HttpSession session,ChooseResult chooseResult) {
		User user = (User) session.getAttribute("user");
		chooseResult.setUserId(user.getId());
		//����Ƿ�Ͷ��Ʊ
		if (chooseService.select(chooseResult)!=null) {
			return false;
		}
		return chooseService.insertResult(chooseResult)>0;
	}
	
		
}
