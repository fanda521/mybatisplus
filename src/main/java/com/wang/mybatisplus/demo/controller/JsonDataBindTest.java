package com.wang.mybatisplus.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.wang.mybatisplus.demo.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-08 19:44
 */
@Controller
@RequestMapping(value = "/com/wang/test")
public class JsonDataBindTest {

    /**
     *
     * 之前一直将前端json格式的数据传给后台，后台接收不到是因为
     * 使用了lombok这个工具的原因，有可能是因为geter和setter没有
     * 检测到
     */
    @RequestMapping(value = "/test1",method = RequestMethod.POST)
    @ResponseBody
    public Person test1(@RequestBody Person person){
        System.out.println(person.toString());
        Person temp=new Person();
        temp.setfName("wang");
        temp.setfSex("男");
        temp.setfIsSingle(true);
        return temp;
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-15
     * @Time 11:37
     * @return a
     * @paras: a
     */
    @RequestMapping(value = "/test2",method = RequestMethod.POST)
    @ResponseBody
    public Person test2(@RequestBody JSONObject param){
        System.out.println("param="+param);
        Set<String> strings = param.keySet();
        for (String value:strings)
        {
            System.out.println("key="+value+":value="+param.get(value));
        }
        Object name = param.get("name");
        System.out.println("name="+name);
        Person temp=new Person();
        temp.setfName("wang");
        temp.setfSex("男");
        temp.setfIsSingle(true);
        return temp;
    }

    @RequestMapping(value = "/test3",method = RequestMethod.POST)
    @ResponseBody
    public Person test3(@RequestBody JSONArray param){
        System.out.println("param="+param);
        for (int i=0;i<param.size();i++)
        {
            System.out.println("key="+param.get(i));
        }

        Person temp=new Person();
        temp.setfName("wang");
        temp.setfSex("男");
        temp.setfIsSingle(true);
        return temp;
    }
}
