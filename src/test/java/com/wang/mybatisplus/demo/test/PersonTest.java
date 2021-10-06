package com.wang.mybatisplus.demo.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.wang.mybatisplus.demo.entity.Person;
import com.wang.mybatisplus.demo.mapper.PersonMapper;
import com.wang.mybatisplus.demo.tools.TimeTransform;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-01 13:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonTest {

    @Autowired
    private PersonMapper personMapper;

    /**
    *
    *@return： 测试mapper  int insert(T entity);
    *@author lucksoul 王吉慧
    *@Date 2019-12-01 
    *@Time 16:12
    */
    @Test
    public void insertByEntity()
    {
        Person person=new Person();
//        person.setFId(1);
//        person.setFName("小菊");
//        person.setFAge(22);
//        person.setFBirthday(LocalDateTime.now());
//        person.setFWeigth(55.5f);
//        person.setFIsSingle(true);
//        person.setFSex("女");
        int insert = personMapper.insert(person);
    }


    /**
    *
    *测试 mapper int deleteById(Serializable id);
    *@author lucksoul 王吉慧
    *@Date 2019-12-01
    *@Time 16:40
    *@return:
    *@params:
    */
    @Test
    public  void deleteById(){
        int deleteId = personMapper.deleteById(1);
        System.out.println("deleteId="+deleteId);
    }

    /**
     * int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
    *@author lucksoul 王吉慧
    *@Date 2019-12-01 
    *@Time 16:44
    *@return: 
    *@params: 
    */
    @Test
    public void deleteByMap(){
        Map map=new HashMap();
        map.put("c_name","小菊");
        int i = personMapper.deleteByMap(map);
        System.out.println("i="+i);//更新的记录条数
    }

    /**int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
     * @author lucksoul 王吉慧
     * @Date 2019-12-01
     * @Time 16:55
     * @return: a
    * @params: a     */
    @Test
    public void delete(){
        QueryWrapper<Person> queryWrapper=new QueryWrapper<>();
        queryWrapper.ge("c_age",17);
        int i = personMapper.delete(queryWrapper);
        System.out.println("i="+i);//更新的记录条数
    }

    @Test
    /**
     *int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01 
     *@Time 16:59
     *@return: 
     *@params: []
     */
    public void deleteBatchIds(){
        List list=new ArrayList();
        list.add(1);
        list.add(3);
        int i = personMapper.deleteBatchIds(list);
        System.out.println("i="+i);//更新的记录条数
    }

    /**
     * int updateById(@Param(Constants.ENTITY) T entity);
     * @author lucksoul 王吉慧
     * @Date 2019-12-01
     * @Time 17:04
     * @return:a
     * @param: a
     */
    @Test
    public void updateById(){
        Person p=new Person();
//        p.setFId(2);
//        p.setFAge(23);
//        p.setFName("哈利");
        int i = personMapper.updateById(p);
        System.out.println("i="+i);//更新的记录条数

    }

    /**
     * * <p>
     *  * 根据 whereEntity 条件，更新记录
     *  * </p>
     *  *
     *  * @param entity        实体对象 (set 条件值,可为 null)
     *  * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     *  * @return 修改成功记录数
     * int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
     * @author lucksoul 王吉慧
     * @Date 2019-12-01
     * @Time 17:11
     * @return a
     * @paras: a
     */
    @Test
    public void  update(){
        Person p=new Person();
//        p.setFAge(22);
//        p.setFName("詹姆");
        QueryWrapper<Person> queryWrapper=new QueryWrapper<>();
        queryWrapper.ge("c_id",3);
        int i = personMapper.update(p, queryWrapper);
        System.out.println("i="+i);//更新的记录条数

    }
    /**
     * /**
     *  * <p>
     *  * 根据 ID 查询
     *  * </p>
     *  *
     *  * @param id 主键ID
     *  * @return 实体
     *
     *selectById(Serializable id);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 17:44
     *@return:
     *@params:
     */
    @Test
    public void  selectById(){
        Person person = personMapper.selectById(1);
        System.out.println(person);

    }
    /**
     ** <p>
     *  * 查询（根据ID 批量查询）
     *  * </p>
     *  *
     *  * @param idList 主键ID列表(不能为 null 以及 empty)
     *  * @return 实体集合
     *  List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 17:48
     *@return:
     *@params:
     */
    @Test
    public void  selectBatchIds(){
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(4);
        List list1 = personMapper.selectBatchIds(list);
        list1.forEach(System.out::println);

    }

    /**
     ** <p>
     *  * 查询（根据 columnMap 条件）
     *  * </p>
     *  *
     *  * @param columnMap 表字段 map 对象
     *  * @return 实体集合
     *  List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 17:53
     *@return:
     *@params:
     */
    @Test
    public void  selectByMap(){
        Map map=new HashMap();
        map.put("c_age",22);
        List list1 = personMapper.selectByMap(map);
        list1.forEach(System.out::println);

    }

    /**
     ** <p>
     *  * 根据 entity 条件，查询一条记录
     *  * </p>
     *  *
     *  * @param queryWrapper 实体对象
     *  * @return 实体
     *  T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 17:56
     *@return:
     *@params:
     */
    @Test
    public void  selectOne(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        Person person = personMapper.selectOne(queryWrapper);
        System.out.println(person);

    }

    /**
     ** <p>
     *  * 根据 Wrapper 条件，查询总记录数
     *  * </p>
     *  *
     *  * @param queryWrapper 实体对象
     *  * @return 满足条件记录数
     *  Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:14
     *@return:
     *@params:
     */
    @Test
    public void  selectCount(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        Integer count = personMapper.selectCount(queryWrapper);
        System.out.println("count="+count);

    }

    /**
     ** <p>
     *  * 根据 entity 条件，查询全部记录
     *  * </p>
     *  *
     *  * @param queryWrapper 实体对象封装操作类（可以为 null）
     *  * @return 实体集合
     *  List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:17
     *@return:
     *@params:
     */
    @Test
    public void  selectList(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        List list = personMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     ** <p>
     *  * 根据 Wrapper 条件，查询全部记录
     *  * </p>
     *  *
     *  * @param queryWrapper 实体对象封装操作类（可以为 null）
     *  * @return 字段映射对象 Map 集合
     *  List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:21
     *@return:
     *@params:
     */
    @Test
    public void  selectMaps(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        List list = personMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     ** <p>
     *  * 根据 Wrapper 条件，查询全部记录
     *  * 注意： 只返回第一个字段的值
     *  * </p>
     *  *
     *  * @param queryWrapper 实体对象封装操作类（可以为 null）
     *  * @return 字段映射对象集合
     *  List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:24
     *@return:
     *@params:
     */
    @Test
    public void  selectObjs(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        List list = personMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);

    }

    /**
     * * <p>
     *  * 根据 entity 条件，查询全部记录（并翻页）
     *  * </p>
     *  *
     *  * @param page         分页查询条件（可以为 RowBounds.DEFAULT）
     *  * @param queryWrapper 实体对象封装操作类（可以为 null）
     *  * @return 实体分页对象
     *  IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:28
     *@return:
     *@params:
     */
    @Test
    public void  selectPage(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        Page<Person> page=new Page<>(1,3);
        IPage iPage = personMapper.selectPage(page, queryWrapper);
        System.out.println("getPages="+iPage.getPages());
        System.out.println("getCurrent="+iPage.getCurrent());
        System.out.println("getSize="+iPage.getSize());
        System.out.println("getTotal="+iPage.getTotal());
        System.out.println("getRecords="+iPage.getRecords());
        iPage.getRecords().forEach(System.out::println);


    }

    /**
     ** <p>
     *  * 根据 Wrapper 条件，查询全部记录（并翻页）
     *  * </p>
     *  *
     *  * @param page         分页查询条件
     *  * @param queryWrapper 实体对象封装操作类
     *  * @return 字段映射对象 Map 分页对象
     *  IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
     *
     *@author lucksoul 王吉慧
     *@Date 2019-12-01
     *@Time 18:42
     *@return:
     *@params:
     */
    @Test
    public void  selectMapsPage(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.ge("c_age",22);
        Page<Person> page=new Page<>(1,3);
        IPage iPage = personMapper.selectPage(page, queryWrapper);
        System.out.println("getPages="+iPage.getPages());
        System.out.println("getCurrent="+iPage.getCurrent());
        System.out.println("getSize="+iPage.getSize());
        System.out.println("getTotal="+iPage.getTotal());
        System.out.println("getRecords="+iPage.getRecords());
        iPage.getRecords().forEach(System.out::println);


    }

}
