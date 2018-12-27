package com.beishu.aiphone.common;

import java.io.File;
import java.util.Calendar;

import com.beishu.aiphone.enumeration.SysParamEnum;

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
**  项目路径配置类
*******************************************************************************/
public class AiConfig {
    /**
     * 路径分隔符
     */
    public static final String S = File.separator;
    /**
     * 根目录
     */
    public static final String HOME_DIR = System.getProperty("user.dir") + S;
    /**
     * 系统配置文件目录
     */
    public static final String CONFIG_DIR = HOME_DIR + "cfg" + S;
    /**
     * 程序运行时目录
     */
    public static final String RUN_DIR = HOME_DIR + "run" + S;
    /**
     * 系统图片目录
     */
    public static final String IMG_DIR = "/resources/img/";
    /**
     * 接收录音目录
     */
    public static final String RECV_VOICE_DIR = HOME_DIR + "voice" + S + "recv" + S;
    /**
     * 发送录音目录
     */
    public static final String SEND_VIOCE_DIR = HOME_DIR + "voice" + S + "send" + S;
    /**
     * ffmpeg目录
     */
    public static final String FFMPEG_DIR = HOME_DIR + "ffmpeg" + S;
    /**
     * 用户配置文件
     */
    public static final String USER_CONFIG_FILE = CONFIG_DIR + "config.cache";
    /**
     * 用户缓存文件
     */
    public static final String USER_CACHE_FILE = RUN_DIR + "aiuser.cache";
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  创建目录
    **---------------------------------------------------------------------------- */
    public void mkdir() {
        File dir = new File(RUN_DIR);
        if (!dir.exists() && !dir.mkdir()) {
            Log.phone_log.error("Create run dir fail.");
        }
        
        dir = new File(RECV_VOICE_DIR);
        if (!dir.exists() && !dir.mkdir()) {
            Log.phone_log.error("Create recv/voice dir fail.");
        }
        
        dir = new File(SEND_VIOCE_DIR);
        if (!dir.exists() && !dir.mkdir()) {
            Log.phone_log.error("Create send/voice dir fail.");
        }
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **Return: 
    **  void
    **Descriptions: 
    **  清除缓存
    **---------------------------------------------------------------------------- */
    public static void clear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -Integer.valueOf(SysParamEnum.SAVE_CALL_FILE_DAYS.V()));
        long curr = calendar.getTimeInMillis();
        
        File dir = new File(RECV_VOICE_DIR);
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.lastModified() < curr) {
                Log.phone_log.debug("Delete expired files:{}", f.getName());
                f.delete();
            }
        }
    }
}
