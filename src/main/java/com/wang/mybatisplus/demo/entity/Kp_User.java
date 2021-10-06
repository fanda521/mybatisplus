package com.wang.mybatisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("kp_user")
public class Kp_User {
    @TableId("userid")
    private Integer id;
    private String name;
    private Integer age;
}
