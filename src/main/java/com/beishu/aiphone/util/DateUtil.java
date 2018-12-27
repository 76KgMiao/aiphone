package com.beishu.aiphone.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
******************************************************************************* 
**Copyright (C) 
**  BeiShu Co., Ltd. All Rights Reserved. 
**Version: 
**  1.0 
**Author: 
**  Miaoqx
**Date: 
**  Dec 27, 2018
**Modified History: 
**  <Author>  <Version>  <Date>       <Description> 
**  Miaoqx    V1.0       Dec 27, 2018      setup 
**Descriptions: 
**  日期工具类
*******************************************************************************/
public class DateUtil {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param date
    **Return: 
    **  String
    **Descriptions: 
    **  获取指定日期的yyyy-MM-dd
    **---------------------------------------------------------------------------- */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param dateTime
    **Return: 
    **  String
    **Descriptions: 
    **  获取指定日期的yyyy-MM-dd HH:mm:ss
    **---------------------------------------------------------------------------- */
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DateStyle.Y_M_D_H_M_S.V()));
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param dateTime
    **  @param pattern
    **Return: 
    **  String
    **Descriptions: 
    **  获取指定的指定格式的字符串
    **---------------------------------------------------------------------------- */
    public static String dateToString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param dateStr
    **Return: 
    **  LocalDate
    **Descriptions: 
    **  获取指定字符串对应的日期 yyyy-MM-dd
    **---------------------------------------------------------------------------- */
    public static LocalDate stringToDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param date1
    **  @param date2
    **Return: 
    **  int
    **Descriptions: 
    **  获取两个日期的天数差
    **---------------------------------------------------------------------------- */
    public static int daysBetween(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        return period.getDays();
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param localDate
    **Return: 
    **  int
    **Descriptions: 
    **  获取指定日期所在月的第一天
    **---------------------------------------------------------------------------- */
    public static int getFirstDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return result.getDayOfMonth();
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param localDate
    **Return: 
    **  int
    **Descriptions: 
    **  获取指定日期所在月的最后一天
    **---------------------------------------------------------------------------- */
    public static int getLastDayInMonth(LocalDate localDate) {
        LocalDate result = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return result.getDayOfMonth();
    }
    
    /**
     **------------------------------------------------------------------------------
     **Parameters: 
     **  @param time1
     **  @param time2
     **Return: 
     **  int
     **Descriptions: 
     **  获取两个时间的顺序：0-相等；1-time1在time2之后；-1-time1在time2之前。
     **---------------------------------------------------------------------------- */
    public static int getOrderOfDate(LocalTime time1, LocalTime time2) {
        if (time1.equals(time2)) {
            return 0;
        } else if (time1.isAfter(time2)) {
            return 1;
        } else {
            return -1;
        }
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param date1
    **  @param date2
    **Return: 
    **  int
    **Descriptions: 
    **  获取两个日期的顺序：0-相等；1-date1在date2之后；-1-date1在date2之前。
    **---------------------------------------------------------------------------- */
    public static int getOrderOfDate(LocalDate date1, LocalDate date2) {
        if (date1.equals(date2)) {
            return 0;
        } else if (date1.isAfter(date2)) {
            return 1;
        } else {
            return -1;
        }
    }
    
    /**
     **------------------------------------------------------------------------------
     **Parameters: 
     **  @param dateTime1
     **  @param dateTime2
     **Return: 
     **  int
     **Descriptions: 
     **  获取两个日期时间的顺序：0-相等；1-dateTime1在dateTime2之后；-1-dateTime1在dateTime2之前。
     **---------------------------------------------------------------------------- */
    public static int getOrderOfDate(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        if (dateTime1.equals(dateTime2)) {
            return 0;
        } else if (dateTime1.isAfter(dateTime2)) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public enum DateStyle {
        H_M_S                   ("HH:mm:ss"),
        Y_M_D                   ("yyyy-MM-dd"),
        Y_M_D_H_M_S             ("yyyy-MM-dd HH:mm:ss"),
        Y_M_D_H_M_S_C           ("yyyy年MM月dd日  HH:mm:ss"),
        Y_M_D_H_M_S_E           ("yyyy年MM月dd日  HH:mm:ss EEEE");
        
        private String pattern;
        
        private DateStyle(String pattern) {
            this.pattern = pattern;
        }
        
        public String V() {
            return this.pattern;
        }
    }
    
}
