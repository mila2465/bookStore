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

    public Manager login(String name, String password) {
        Manager manager = managerMapper.findByNameAndPwd(name, password);
        return manager;
    }

}
