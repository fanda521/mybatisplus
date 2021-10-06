package com.wang.mybatisplus.demo.entity;


import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.concurrent.locks.Condition;

@Data
public class User {
    private Long id;
    @TableField(condition = SqlCondition.LIKE_LEFT)
    private String name;
    @TableField(condition ="%s&gt;#{%s}" )
    private Integer age;
    private String email;
}

