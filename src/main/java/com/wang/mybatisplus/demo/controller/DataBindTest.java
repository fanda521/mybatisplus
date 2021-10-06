package com.wang.mybatisplus.demo.controller;

import com.wang.mybatisplus.demo.entity.Order;
import com.wang.mybatisplus.demo.entity.Person;
import com.wang.mybatisplus.demo.entity.PersonList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-07 23:09
 */
@RequestMapping(value = "/com/wang/test")
@Controller
public class DataBindTest {

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 18:22
     * @return:a
     * @param: a
     */
    @RequestMapping(value = "/defaultBind",method = RequestMethod.POST)
    @ResponseBody
    public String defaultBind(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println("id="+id);
        return id;
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 18:28
     * @return:a
     * @param: a
     */
    @RequestMapping(value = "/simpleBind",method = RequestMethod.POST)
    @ResponseBody
    public String simpleBind(int id){
        System.out.println("id="+id);
        return id+"";
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 18:32
     * @return:a
     * @param: a
     */

    @RequestMapping(value = "/requestParamBind",method = RequestMethod.POST)
    @ResponseBody
    public String requestParamBind(@RequestParam(value = "user_id") int id){
        System.out.println("id="+id);
        return id+"";
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 18:44
     * @return:a
     * @param: a
     */
    @RequestMapping(value = "/pojoBind",method = RequestMethod.POST)
    @ResponseBody
    public String pojoBind(Person person){
        System.out.println("person="+person);
        return person.toString()+"";
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 19:08
     * @return a
     * @paras: a
     */
    @RequestMapping(value = "/pagkegPojoBind",method = RequestMethod.POST)
    @ResponseBody
    public String pagkegPojoBind(Order order){
        System.out.println("order="+order);
        return order.toString()+"";
    }

    /**
     * @author lucksoul 王吉慧
     * @Date 2019-12-08
     * @Time 19:22
     * @return a
     * @paras: a
     */
    @RequestMapping(value = "/arrayBind",method = RequestMethod.POST)
    @ResponseBody
    public String arrayBind(int[] ids){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i=0;i<ids.length;i++)
        {
            System.out.println(ids[i]);
            stringBuffer.append(ids[i]+",");
        }


        return stringBuffer.toString();
    }

    @RequestMapping(value = "/collectionBind",method = RequestMethod.POST)
    @ResponseBody
    public String collectionBind(PersonList personList){
        List<Person> persons = personList.getPersons();
        persons.forEach(System.out::println);

        return persons.toString();
    }

}
