package com.wang.mybatisplus.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.mybatisplus.demo.entity.User;
import com.wang.mybatisplus.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest2 {
    @Resource
    private UserMapper userMapper;

    /**
     * SELECT id,name,email
     * FROM user WHERE (name LIKE ? AND age > ?)
     */
    @Test
    public void selectFileds()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id","name","email")
                .likeRight("name","南")
                .gt("age",18);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * SELECT id FROM user WHERE (age > ?)
     * 特别说明，id好像无法过滤掉
     */
    @Test
    public void selectFileds2()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select(User.class,info->!info.getColumn().equals("email")
                &&!info.getColumn().equals("name")
                &&!info.getColumn().equals("id"))
                .gt("age",18);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }


    /**
     * SELECT id FROM user WHERE (age > ?)
     * 特别说明，id好像无法过滤掉
     *
     * SELECT id,name,email,age FROM user WHERE
     * (name LIKE ? AND age > ?)
     */
    @Test
    public void testCondition()
    {
        String email="";
        condition("小南",email);
    }

    public  void condition(String name,String email)
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),"name",name)
                .like(StringUtils.isNotEmpty(email),"email",email)
                .gt("age",18);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * 条件构造器是，实体类,等值
     * SELECT id,name,email,age FROM user WHERE name=?
     *
     * DEBUG==>  Preparing: SELECT id,name,email,age
     * FROM user WHERE name LIKE CONCAT('%',?) AND age>?
     */
    @Test
    public  void selectByMapperEntity()
    {
        User whereUser=new User();
        whereUser.setName("南");
        whereUser.setAge(20);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>(whereUser);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * true
     * SELECT id,name,email,age FROM user WHERE
     * (name = ? AND age IS NULL)
     *false
     *SELECT id,name,email,age FROM user WHERE (name = ?)
     */
    @Test
    public  void selectByMapAllEq()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Map<String,Object> map=new HashMap<>();
        map.put("name","小南");
        map.put("age",null);
        queryWrapper.allEq(map,false);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public  void selectByMaps()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","南").gt("age",10);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        //List<User> users = userMapper.selectList(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public  void selectByMaps2()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","南").gt("age",10)
        .groupBy("name").having("sum(age)>{0}",14);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        //List<User> users = userMapper.selectList(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public  void selectByObjs()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","南").gt("age",10);

        List<Object> list = userMapper.selectObjs(queryWrapper);
        //List<User> users = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public  void selectByLambda()
    {
        //LambdaQueryWrapper<User> lambda=new QueryWrapper<User>().lambda();
        //LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        LambdaQueryWrapper<User> userLambda = Wrappers.lambdaQuery();
        userLambda.like(User::getName,"南").gt(User::getAge,16);
        List<Object> list = userMapper.selectObjs(userLambda);
        list.forEach(System.out::println);
    }

    @Test
    public  void selectByLambda2()
    {
        //LambdaQueryWrapper<User> lambda=new QueryWrapper<User>().lambda();
        //LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        LambdaQueryWrapper<User> userLambda = Wrappers.lambdaQuery();
        userLambda.like(User::getName,"南").gt(User::getAge,16);
        List<User> list = userMapper.selectAll(userLambda);
        list.forEach(System.out::println);
    }

    @Test
    public  void selectByLambda3()
    {
        //LambdaQueryWrapper<User> lambda=new QueryWrapper<User>().lambda();
        //LambdaQueryWrapper lambdaQueryWrapper=new LambdaQueryWrapper();
        LambdaQueryWrapper<User> userLambda = Wrappers.lambdaQuery();
        userLambda.like(User::getName,"南").gt(User::getAge,16);
        List<User> list = userMapper.selectAll2(userLambda);
        list.forEach(System.out::println);
    }






}
