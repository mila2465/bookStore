package com.zhu.controller.client;

import com.zhu.domain.Manager;
import com.zhu.domain.Msg;
import com.zhu.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService service;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Msg login(HttpSession session, @RequestParam String name, @RequestParam String password) {


        Manager manager = service.login(name, password);
        if(manager==null) {
            return new Msg(0,"用户名或密码错误，登陆失败");
        }else {
            session.setAttribute("manager", manager);//给user设置session
            return  new Msg(1,"登陆成功");
        }
    }

    @RequestMapping("/loginout")
    public String loginout(HttpSession session) {
        session.invalidate();
        return "manager";
    }
}
