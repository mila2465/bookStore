package com.zhu.controller.client;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhu.domain.Msg;
import com.zhu.domain.User;
import com.zhu.service.UserService;

/**
 * 用户登录相关
 */
@Controller
@RequestMapping("/client")
public class UserController {

	@Autowired
	private UserService service;

	/**
	 * 用户登录
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Msg login(HttpSession session,@RequestParam String username,@RequestParam String password) {
		
		User user=service.login(username,password);
		 if(user==null) {
			 return new Msg(0,"用户名或密码错误，登陆失败");
		 }else {
			 session.setAttribute("user", user);//给user设置session
			return  new Msg(1,"登陆成功");
		 }
	}

	/**
	 * 用户注册
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public Msg regist(User user,Model model) {
	     if(!service.isUsernameExist(user.getUsername())) {
	    	 service.regist(user);
	    	return new Msg(1,"注册成功");
	    }else {
	    	 return new Msg(0,"该用户名已被注册，请重新填写");
	     } 
	}

	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
