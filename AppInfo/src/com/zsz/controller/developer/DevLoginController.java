package com.zsz.controller.developer;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zsz.pojo.DevUser;
import com.zsz.service.developer.DevUserService;
import com.zsz.utils.Constants;
import com.zsz.utils.VerifyCode;

@Controller
@RequestMapping("/dev")
public class DevLoginController {
	
	@Autowired
	private DevUserService devUserService;
	
	//跳转登录页面
	@RequestMapping("/devlogin.html")
	public String devLogin() throws IOException{
		
		return "devlogin";	
	}
	
	// 登录判断
	@RequestMapping("/dologin.html")
	public String doLogin(String devCode,String devPassword,String txtSN,Model model,HttpSession session){
		System.out.println("进入登录判断");
		DevUser user=devUserService.devLogin(devCode, devPassword);
		//取出验证码
		String YZM=(String) session.getAttribute("verifyCode");
		System.out.println("YZM==="+YZM);
		System.out.println("00000000"+txtSN);
		if(YZM.equalsIgnoreCase(txtSN)){
			if(user!=null){
				System.out.println("1");
				session.setAttribute(Constants.DEV_USER_SESSION,user);
				return "developer/main";
			}else{
				System.out.println("2");
				model.addAttribute(Constants.SYS_MESSAGE,"您的用户名或密码输入错误");
				return "devlogin";
				}
		}else{
			System.out.println("3");
			model.addAttribute("YZMerr","验证码输入错误");
			return "devlogin";
		}
}
	//注销账户
	@RequestMapping("/logout.html")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "redirect:/dev/devlogin.html";
		
	}
	
	@RequestMapping("/YZM")
	public void YZM(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		  VerifyCode vc = new VerifyCode();  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setContentType("image/jpeg");  
	        BufferedImage bim = vc.getImage();  
	        ImageIO.write(bim, "JPEG", response.getOutputStream());
	        String verifycode = vc.getText();
	        //把验证码存入session
	        session.setAttribute("verifyCode", verifycode);  
	}
	
	//跳转到个人信息维护页面
	@RequestMapping("/weihu.html")
	public String devWeiHu(Model model,HttpSession session){
		//获取当前登录id
		DevUser devUser=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int createdBy=devUser.getId();
		
		//根据id查询信息
		DevUser user=devUserService.getByDevUser(createdBy);
		model.addAttribute("user",user);
		
		return "developer/appweihu";	
	}
	
	//修改个人信息
	@RequestMapping("/updateUser.html")
	public String updateUser(DevUser devUser,HttpSession session){
		//获取当前登录id
		DevUser devUsers=(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		int createdBy=devUser.getId();
		
		devUser.setModifyBy(createdBy);
		
		devUser.setModifyDate(new Date());
		devUserService.updateDevUser(devUser);
		
		return "redirect:/dev/weihu.html";		
	}
	
	
}
