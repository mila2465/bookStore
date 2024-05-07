package com.zhu.service;

import com.zhu.domain.Manager;
import com.zhu.domain.User;
import com.zhu.mapper.ManagerMapper;
import com.zhu.mapper.UserMapper;
import com.zhu.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    //登陆
    /*public User login(String name, String password) {

        User user=mapper.findByUsernameAndPwd(name,password);
        return user;
    }*/
    public Manager login(String name, String password) {
        Manager manager = managerMapper.findByNameAndPwd(name, password);
        return manager;
    }

    //判断该用户名是否已被使用
    /*public Boolean  isUsernameExist(String username) {
        User user=mapper.findByUsername(username);
        if(user==null) return false;
        else           return  true;

    }

    public User findUserByUsername(String username) {
        User user=mapper.findByUsername(username);
        return user;
    }*/



}
