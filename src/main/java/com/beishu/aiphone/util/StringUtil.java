package com.beishu.aiphone.util;

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
**  字符串工具类
*******************************************************************************/
public class StringUtil {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param s
    **Return: 
    **  boolean
    **Descriptions: 
    **  判断字符串是否为空
    **---------------------------------------------------------------------------- */
    public boolean isNull(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
