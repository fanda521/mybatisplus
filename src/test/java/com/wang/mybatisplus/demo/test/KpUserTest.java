package com.wang.mybatisplus.demo.test;

import com.wang.mybatisplus.demo.entity.Kp_User;
import com.wang.mybatisplus.demo.mapper.KpUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KpUserTest {
    @Resource
    private KpUserMapper kpUserMapper;

    @Test
    public void insert(){
        Kp_User kp_user=new Kp_User();
        kp_user.setId(12);
        kp_user.setAge(20);
        kp_user.setName("linlin");
        kpUserMapper.insert(kp_user);
    }

}
