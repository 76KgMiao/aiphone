package com.beishu.aiphone.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import com.beishu.aiphone.common.AiConfig;
import com.beishu.aiphone.common.Log;

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
**  配置缓存类
*******************************************************************************/
public class ConfigCache {
    /**
     * property cache collection.
     */
    private Map<String, String> propertyCache = new ConcurrentHashMap<String, String>();
    
    private static volatile ConfigCache instance = null;
    
    private String filepath = null;

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @return
    **Return: 
    **  TNTUserCache
    **Descriptions: 
    **  获取配置缓存对象
    **---------------------------------------------------------------------------- */
    public static synchronized ConfigCache getInstance() {
        synchronized(ConfigCache.class) {
            if (instance == null) {
                instance = new ConfigCache();
            }
        }
        return instance;
    }

    private ConfigCache() {
        File f = new File(AiConfig.USER_CONFIG_FILE);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Log.phone_log.error("Create config file fail:", e);
            }
        }
        this.filepath = AiConfig.USER_CONFIG_FILE;
        this.init();
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  初始化配置键值
    **---------------------------------------------------------------------------- */
    private void init() {
        FileInputStream is = null;

        try {
            is = new FileInputStream(filepath);
            PropertyResourceBundle e = new PropertyResourceBundle(is);
            Enumeration<String> enumer = e.getKeys();

            while (enumer.hasMoreElements()) {
                String key = (String) enumer.nextElement();
                this.propertyCache.put(key, ((String) e.handleGetObject(key)).trim());
            }
        } catch (IOException e) {
            Log.phone_log.error("Read config file fail:", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }

        }
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **  @param value
    **Return: 
    **  void
    **Descriptions: 
    **  设置配置属性值
    **---------------------------------------------------------------------------- */
    public void setValue(String key, String value) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(filepath);
            prop.load(fis);
            fos = new FileOutputStream(filepath);
            prop.setProperty(key, value);
            prop.store(fos, "您已成功激活系统");
        } catch (IOException e) {
            Log.phone_log.error("Write config file fail:", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
        propertyCache.clear();
        init();
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **Return: 
    **  String
    **Descriptions: 
    **  获取属性值，如果没有返回null
    **---------------------------------------------------------------------------- */
    public String getValue(String key) {
        return propertyCache.get(key);
    }
}
