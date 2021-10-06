package com.wang.mybatisplus.demo.serviceImpl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.mybatisplus.demo.entity.Person;
import com.wang.mybatisplus.demo.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-22 21:25
 */
@Service
public class PersonServiceImpl extends ServiceImpl<BaseMapper<Person>,Person> implements PersonService {
}
