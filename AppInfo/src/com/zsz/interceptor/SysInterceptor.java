package com.zsz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zsz.pojo.BackendUser;
import com.zsz.pojo.DevUser;
import com.zsz.utils.Constants;

/**
 * 
 * <p>Title: SysInterceptor</p>
 * <p>Description: 自定义拦截器：拦截所有用户注册、登录以外的任何请求，保证安全性 </p>
 * @author MuGuijun 
 * @date 2017年6月7日 上午8:35:53
 */
public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session = request.getSession();
		//获取session中的开发者或管理员的信息
		
		//开发者信息
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		
		//管理员信息
		BackendUser backendUser=(BackendUser) session.getAttribute(Constants.USER_SESSION);
		
		//判断是否是开发者登录
		if(null != devUser || null!=backendUser){ //dev SUCCESS
			return true;
		}else{
			//请求中没有包含用户信息，说明当前请求没有用户登录，页面跳转到警告页
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return false;
		}
		
	}
}
