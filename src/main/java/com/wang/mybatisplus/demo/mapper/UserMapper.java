package com.wang.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.mybatisplus.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER)Wrapper<User> wrapper);

    List<User> selectAll2(@Param(Constants.WRAPPER)Wrapper<User> wrapper);

    IPage<User> selectMyPage(Page<User>page,@Param(Constants.WRAPPER)Wrapper<User> wrapper);

}

