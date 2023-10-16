package com.example.shop.service.impl;

import com.example.shop.bean.UserBean;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.AccountService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserMapper userMapper;


    @Override
    public void createAccount(String username, String password) {
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setUser(username);
        userBean.setStatus("买家");

        userMapper.insert(userBean);

    }
}
