package com.wang.mybatisplus.demo.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.mybatisplus.demo.entity.User;
import com.wang.mybatisplus.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest1 {
    @Autowired
    private UserService userService;
    //使用上面的那个userService时，可以不到service打注解
    //但是一定要到他的实现类打要不然自动注入的时候子啊spring容器里
    //是找不到的，service层打不打注解无所谓，光到service层打也没有用
    //起作用的是实现类

    @Test
    public void test(){
        QueryWrapper<User> userWrapper=new QueryWrapper<>();
        userWrapper.like("name","南").gt("age",12);
        List<User> users = userService.list(userWrapper);
        users.forEach(System.out::println);
    }
}
