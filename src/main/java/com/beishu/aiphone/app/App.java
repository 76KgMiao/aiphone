package com.beishu.aiphone.app;

import org.slf4j.LoggerFactory;

import com.beishu.aiphone.core.ConfigCache;
import com.beishu.aiphone.security.Encryption;
import com.beishu.aiphone.util.MacUtil;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

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
**  App
*******************************************************************************/
public class App {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param args
    **Return: 
    **  void
    **Descriptions: 
    **  主程序入口
    **---------------------------------------------------------------------------- */
    public static void main(String[] args) {
        init();
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  初始化
    **---------------------------------------------------------------------------- */
    private static void init() {
        initLoggerContext();
        checkProductSerial();
    }
    
    private static void initLoggerContext() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure("cfg/logback-log.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }

    private static void checkProductSerial() {
        String macId = MacUtil.getId();
        ConfigCache configCache = ConfigCache.getInstance();
        
        if (configCache.getValue("mid") == null 
                || configCache.getValue("aid") == null
                || configCache.getValue("appid") == null 
                || !macId.equals(Encryption.decryption(configCache.getValue("mid")))) {
            // 激活框激活
        } else {
            // 校验版本
            // 登录
        }
    }
    
}
