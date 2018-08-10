package com.zsz.controller.backend;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsz.pojo.BackendUser;
import com.zsz.service.backend.BackendUserService;
import com.zsz.utils.Constants;

@Controller
@RequestMapping("/backendLogin")
public class BackendLoginController {

	@Autowired
	private BackendUserService backendUserService;
	
	//后台管理页面
	@RequestMapping("/login.html")
	public String backendLogin(){
		return "backendlogin";	
	}
	
	//后台管理登录验证
	@RequestMapping("/dologin.html")
	public String backendDoLogin(String userCode,String userPassword,String txtSN,Model model,HttpSession session){
		BackendUser backendUser=backendUserService.backendLogin(userCode, userPassword);
		
		//取出验证码
		String YZM=(String) session.getAttribute("verifyCode");
		if(YZM.equalsIgnoreCase(txtSN)){
			if(null!=backendUser){
				session.setAttribute(Constants.USER_SESSION, backendUser);
				return "backend/main";			
			}else{
				model.addAttribute(Constants.SYS_MESSAGE,"您的用户名或密码输入错误");
				return "backendlogin";
			}
		}else{
			model.addAttribute("YZMerr","验证码输入错误");
			return "backendlogin";
	}
}
	//后台管理注销
	@RequestMapping("/logout.html")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.USER_SESSION);
		return "backendlogin";
		
	}
	
}
