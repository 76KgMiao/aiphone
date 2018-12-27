package com.beishu.aiphone.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
**  用户缓存类
*******************************************************************************/
public class UserCache {
    /**
     * property cache collection.
     */
    private Map<String, String> propertyCache = new ConcurrentHashMap<String, String>();
    
    private static volatile UserCache instance = null;
    
    private String filepath = null;

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  UserCache
    **Descriptions: 
    **  获取用户缓存对象
    **---------------------------------------------------------------------------- */
    public static synchronized UserCache getInstance() {
        synchronized (UserCache.class) {
            if (instance == null) {
                instance = new UserCache();
            }
        }
        return instance;
    }

    private UserCache() {
        File f = new File(AiConfig.USER_CACHE_FILE);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Log.phone_log.error("Create user cache fail:", e);
            }
        }
        this.filepath = AiConfig.USER_CACHE_FILE;
        this.init();
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  初始化用户属性值
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
            Log.phone_log.error("Read user cache fail:", e);
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
    **  设置用户属性
    **---------------------------------------------------------------------------- */
    public void setValue(String key, String value) {
        HashMap<String, String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put(key, value);
        this.setValue(propertiesMap);
    }

    public void setValue(Map<String, String> propertiesMap) {
        if (propertiesMap != null && !propertiesMap.isEmpty()) {
            this.writePropertyFile(propertiesMap);
            this.refresh();
        }
    }

    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  刷新
    **---------------------------------------------------------------------------- */
    private void refresh() {
        this.clear();
        this.init();
    }

    private void clear() {
        this.propertyCache.clear();
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param propertiesMap
    **Return: 
    **  void
    **Descriptions: 
    **  写入指定属性到缓存文件
    **---------------------------------------------------------------------------- */
    private void writePropertyFile(Map<String, String> propertiesMap) {
        Properties prop = new Properties();
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(filepath);
            prop.load(fis);
            fos = new FileOutputStream(filepath);
            String e = "";
            Iterator<String> it = propertiesMap.keySet().iterator();

            while (it.hasNext()) {
                e = (String) it.next();
                prop.setProperty(e, (String) propertiesMap.get(e));
            }

            prop.store(fos, "已经成功登陆过的用户ID");
        } catch (IOException e) {
            Log.phone_log.error("Write user cache fail:", e);
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

    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **Return: 
    **  String
    **Descriptions: 
    **  获取指定的属性值
    **---------------------------------------------------------------------------- */
    public String getValue(String key) {
        return propertyCache.get(key);
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **  @param value
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验是否有改用户
    **---------------------------------------------------------------------------- */
    public boolean hasValue(String key, String value) {
        if (this.propertyCache.containsKey(key) && value != null && value.trim().length() > 0) {
            String tempValue = (String) this.propertyCache.get(key);
            if (tempValue != null && tempValue.trim().length() > 0) {
                if (tempValue.contains(",")) {
                    tempValue = tempValue.substring(0, tempValue.length() - 1);
                    String[] tempValueArray = tempValue.split(",");
                    String[] arg7 = tempValueArray;
                    int arg6 = tempValueArray.length;

                    for (int arg5 = 0; arg5 < arg6; ++arg5) {
                        String v = arg7[arg5];
                        if (value.contains(v)) {
                            return true;
                        }
                    }
                } else if (value.contains(tempValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **  @param value
    **Return: 
    **  boolean
    **Descriptions: 
    **  校验是否相等
    **---------------------------------------------------------------------------- */
    public boolean equalsValue(String key, String value) {
        if (this.propertyCache.containsKey(key) && value != null && value.trim().length() > 0) {
            String tempValue = (String) this.propertyCache.get(key);
            if (tempValue != null && tempValue.trim().length() > 0) {
                if (tempValue.contains(",")) {
                    tempValue = tempValue.substring(0, tempValue.length() - 1);
                    String[] tempValueArray = tempValue.split(",");
                    String[] arg7 = tempValueArray;
                    int arg6 = tempValueArray.length;

                    for (int arg5 = 0; arg5 < arg6; ++arg5) {
                        String v = arg7[arg5];
                        if (value.equals(v)) {
                            return true;
                        }
                    }
                } else if (value.equals(tempValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param key
    **  @param value
    **Return: 
    **  void
    **Descriptions: 
    **  删除指定属性
    **---------------------------------------------------------------------------- */
    public void removeValue(String key, String value) {
        if (this.propertyCache.containsKey(key) && value != null && value.trim().length() > 0) {
            String tempValue = (String) this.propertyCache.get(key);
            if (tempValue != null && tempValue.trim().length() > 0 && this.equalsValue(key, value)) {
                if (tempValue.contains(",")) {
                    tempValue = tempValue.replace(value + ",", "");
                } else {
                    tempValue = "";
                }

                this.setValue(key, tempValue);
                this.refresh();
            }
        }
    }
}
