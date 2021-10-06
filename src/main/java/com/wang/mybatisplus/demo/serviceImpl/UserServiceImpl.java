package com.wang.mybatisplus.demo.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mybatisplus.demo.entity.User;
import com.wang.mybatisplus.demo.mapper.UserMapper;
import com.wang.mybatisplus.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
