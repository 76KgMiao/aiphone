package com.beishu.aiphone.enumeration;

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
**  系统参数名称枚举类
*******************************************************************************/
public enum SysParamEnum {

    SAVE_CALL_FILE_DAYS                 ("保存通话文件天数");
    
    private String desc;
    
    SysParamEnum(String desc) {
        this.desc = desc;
    }
    
    /*
     * 返回枚举描述
     */
    public String V() {
        return this.desc;
    }
}
