package com.wang.mybatisplus.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-08 18:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String fId;
    private Person fPerson;
}
