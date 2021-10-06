package com.wang.mybatisplus.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.mybatisplus.demo.entity.User;
import com.wang.mybatisplus.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelect2() {
        System.out.println(("----- selectAll method test ------"));
        User user = userMapper.selectById(1);
        Assert.assertEquals(1, 1);
        System.out.println(user);
    }
    ////insert支持回填
    @Test
    public void insert() {
        System.out.println(("----- insert method test ------"));
        User user=new User();
        user.setAge(14);
        user.setEmail("1056015243@qq.com");
        //user.setId(10l);
        user.setName("lucksoul");
        System.out.println("before user="+user);
        userMapper.insert(user);
        System.out.println("after user="+user);
    }

    /**
     * select
     * SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? , ? )
     */
    @Test
    public void selectIds(){
        List<Integer> integers = Arrays.asList(1, 3, 5,12);
        List<User> users = userMapper.selectBatchIds(integers);
        users.forEach(System.out::println);

    }

    /**
     * map就相当于where name=？ and age=?
     *  SELECT id,name,email,age FROM user WHERE name = ? AND age = ?
     */
    @Test
    public void selectByMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("name","Jone");
        map.put("age",18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * wrapper
     * SELECT id,name,email,age FROM user WHERE
     * (name LIKE ? AND age < ?)
     */
    @Test
    public void selectByWrapper()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        QueryWrapper<User> query = Wrappers.query();
        queryWrapper.like("name","南").lt("age",69);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 3
     *
     "DEBUG==>  Preparing: SELECT id,name,email,age FROM user WHERE
     (name LIKE ? OR age >= ?) ORDER BY age DESC , id ASC
     */
    @Test
    public void selectByWrapper3()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.likeRight("name","南")
                .or().ge("age","13")
                .orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 2
     * SELECT id,name,email,age FROM user WHERE
     * (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
     */
    @Test
    public void selectByWrapper2()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        QueryWrapper<User> query = Wrappers.query();
        queryWrapper.like("name","南").between("age",12,45).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }


    /**
     * wrapper 4
     * Preparing: SELECT id,name,email,age FROM user WHERE
     * (age>? AND age IN (select age from user where id>6))
     */
    @Test
    public void selectByWrapper4()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.apply("age>{0}","12").inSql("age","select age from user where id>6");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 5
     *SELECT id,name,email,age FROM user WHERE
     * (name LIKE ? AND ( (age >= ? OR email IS NOT NULL) ))
     */
    @Test
    public void selectByWrapper5()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.likeRight("name","南").
                and(wq->wq.ge("age","14")
                        .or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }


    /**
     * wrapper 6
     *SELECT id,name,email,age FROM user WHERE
     * (name LIKE ? OR
     * ( (age < ? AND age > ? AND email IS NOT NULL) ))
     */
    @Test
    public void selectByWrapper6()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.likeRight("name","南")
        .or(wq->wq.lt("age",40).gt("age",13).isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 7
     *SELECT id,name,email,age FROM user WHERE
     *(name LIKE ? OR ( (age > ?) ) OR ( (email IS NOT NULL) ))
     *
     *SELECT id,name,email,age FROM user WHERE
     *  (name LIKE ? AND ( (age > ? OR email IS NOT NULL) ))
     *
     *  SELECT id,name,email,age FROM user WHERE
     *  (name LIKE ? AND ( (age > ? OR email IS NOT NULL) ))
     */
    @Test
    public void selectByWrapper7()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.likeRight("name","南")
//                .or(wq->wq.gt("age",18)).or(wq->wq.isNotNull("email"));
//        queryWrapper.likeRight("name","南")
//                .nested(wq->wq.gt("age",18).or().isNotNull("email"));
        queryWrapper.likeRight("name","南")
                .and(wq->wq.gt("age",18).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 8
     *SELECT id,name,email,age FROM user WHERE (age IN (?,?,?,?))
     */
    @Test
    public void selectByWrapper8()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("age",Arrays.asList(12,14,22,13));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     * wrapper 9
     *SELECT id,name,email,age FROM user WHERE
     * (age IN (?,?,?,?)) limit 1
     */
    @Test
    public void selectByWrapper9()
    {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.in("age",Arrays.asList(12,14,22,13)).last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

}

