package com.xs.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (null!=obj)
			return true;
		request.setAttribute("msg", "请登录后重试");
			request.getRequestDispatcher("WEB-INF/view/passport/login.jsp").forward(request, response);
		
		return false;
	}
}
