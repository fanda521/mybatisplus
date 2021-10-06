package com.wang.mybatisplus.demo.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lucksoul 王吉慧
 * @version 1.0
 * @date 2019-12-01 14:13
 */
@Data
public class TimeTransform {

    private static String pattern="yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);

    /**将data类型的日期按照pattern的格式转换成String类型的目标日期
    *@author lucksoul 王吉慧
    *@Date 2019-12-01 @time 14:26
     * @param date 需要转化的源日期数据
     * @param pattern 目标日期格式
     * @return String 类型的目标日期
    */
    public static String fromDatetoString(Date date,String pattern ){
        TimeTransform.pattern =pattern;
        String format = simpleDateFormat.format(date);
        return  format;
    }
    /**
     * 将data类型的日期按照pattern的格式转换成String类型的目标日期
    *@author lucksoul 王吉慧
    *@Date 2019-12-01 @time 14:29
    *@param  date  需要转化的源日期数据
    *@return  String 类型的目标日期
    *
    */
    public static String fromDatetoString(Date date ){
        return TimeTransform.fromDatetoString(date,TimeTransform.pattern);
    }

    /**将字符串转换成data类型pattern格式的日期
     * * @param resource 源字符串
     * * @param pattern 转换的格式
     * * @return Data 类型的日期
     * * @throws ParseException
    *@author lucksoul 王吉慧
    *@Date 2019-12-01
    */
    public static Date fromStringtoString(String resource, String pattern) throws ParseException {
        TimeTransform.pattern=pattern;
        Date parse = simpleDateFormat.parse(resource);
        return  parse;
    }

    /**
     * 将字符串转换成data类型的日期
     * @author lucksoul 王吉慧
     * @Date 2019-12-01
     * @param resource
     * @return
     * @throws ParseException
     */
    public static Date fromStringtoString(String resource) throws ParseException {
        return  TimeTransform.fromStringtoString(resource,TimeTransform.pattern);
    }

}
