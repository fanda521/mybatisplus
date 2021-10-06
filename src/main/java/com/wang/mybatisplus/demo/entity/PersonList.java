package com.wang.mybatisplus.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-08 19:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonList {
    private List<Person> persons;
}
