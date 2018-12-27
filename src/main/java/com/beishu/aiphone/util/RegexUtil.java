package com.beishu.aiphone.util;

import java.util.regex.Pattern;

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
**  正则校验工具类
*******************************************************************************/
public class RegexUtil {
    
    
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param digit
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验非负整数
    **---------------------------------------------------------------------------- */
    public static boolean checkInteger(String digit) {
        String regex = "^[1-9]\\d*|0$";
        return Pattern.matches(regex, digit);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param email
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验邮箱
    **---------------------------------------------------------------------------- */
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param mobile
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验移动手机号
    **---------------------------------------------------------------------------- */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param phone
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验固话
    **---------------------------------------------------------------------------- */
    public static boolean checkPhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param chinese
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验中文
    **---------------------------------------------------------------------------- */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param ipAddress
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验IP地址
    **---------------------------------------------------------------------------- */
    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }
}
