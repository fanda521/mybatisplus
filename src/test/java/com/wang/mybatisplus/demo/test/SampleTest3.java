package com.wang.mybatisplus.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.mybatisplus.demo.entity.User;
import com.wang.mybatisplus.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest3 {

    @Autowired
    private UserMapper userMapper;

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-29
     * @Time 21:14
     * @return:a
     * @param: a
     * "DEBUG==>  Preparing: SELECT COUNT(1) FROM user WHERE (age > ?)
     * ""DEBUG==> Parameters: 16(Integer)
     * ""TRACE<==    Columns: COUNT(1)
     * ""TRACE<==        Row: 10
     * ""DEBUG==>  Preparing: SELECT id,name,email,age FROM user WHERE (age > ?) LIMIT ?,?
     * ""DEBUG==> Parameters: 16(Integer), 0(Long), 2(Long)
     * ""TRACE<==    Columns: id, name, email, age
     * ""TRACE<==        Row: 1, Jone, test1@baomidou.com, 18
     * ""TRACE<==        Row: 2, Jack, test2@baomidou.com, 20
     * ""DEBUG<==      Total: 2
     */
    @Test
    public void selectPage()
    {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.gt(User::getAge,16);
        Page<User> page=new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(page, lambdaQuery);

        System.out.println("记录总数="+userIPage.getTotal());
        System.out.println("总页数="+userIPage.getPages());
        System.out.println("记录内容="+userIPage.getRecords());
    }

    @Test
    public void selectMapPage()
    {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.gt(User::getAge,16);
        Page<User> page=new Page<>(1,2);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, lambdaQuery);

        System.out.println("记录总数="+mapIPage.getTotal());
        System.out.println("总页数="+mapIPage.getPages());
        System.out.println("记录内容="+mapIPage.getRecords());

    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-29
     * @Time 21:19
     * @return:a
     * @param: a
     * "DEBUG==>  Preparing: SELECT COUNT(1) FROM user WHERE (age > ?)
     * ""DEBUG==> Parameters: 16(Integer)
     * ""TRACE<==    Columns: COUNT(1)
     * ""TRACE<==        Row: 10
     * ""DEBUG==>  Preparing: select * from user WHERE (age > ?) LIMIT ?,?
     * ""DEBUG==> Parameters: 16(Integer), 0(Long), 2(Long)
     * ""TRACE<==    Columns: id, name, age, email
     * ""TRACE<==        Row: 1, Jone, 18, test1@baomidou.com
     * ""TRACE<==        Row: 2, Jack, 20, test2@baomidou.com
     * ""DEBUG<==      Total: 2
     */
    @Test
    public void selectMyPage()
    {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.gt(User::getAge,16);
        Page<User> page=new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectMyPage(page, lambdaQuery);

        System.out.println("记录总数="+userIPage.getTotal());
        System.out.println("总页数="+userIPage.getPages());
        System.out.println("记录内容="+userIPage.getRecords());

    }
}
