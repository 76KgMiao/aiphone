package com.beishu.aiphone.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

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
**  Dec 19, 2018
**Modified History: 
**  <Author>  <Version>  <Date>       <Description> 
**  Miaoqx    V1.0       Dec 19, 2018      setup 
**Descriptions: 
**  获取本机电脑的MAC地址，如果没有获取成功，获取硬盘的ID。
*******************************************************************************/ 
public class MacUtil {

    public static String getId() {
        String result = getMac();
        if (result.length() == 0) {
            result = getSerial("C");
            if (result.length() == 0) {
                result = getSerial("D");
                if (result.length() == 0) {
                    result = getSerial("E");
                    if (result.length() == 0) {
                        result = getSerial("F");
                    }
                }
            }
        }
        if (result.length() == 0) {
            System.exit(0);
        }
        return result;
    }
    
    private static String getMac() {
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                sb.append(mac[i]);
            }
        } catch (Exception e) {
            Log.phone_log.error("获取MAC地址失败", e);
            return "";
        }
        return sb.toString().trim();
    }

    private static String getSerial(String drive) {
        String result = "";
        try {
            File f = File.createTempFile("damn", ".vbs");
            f.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(f);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + f.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            Log.phone_log.error("获取硬盘ID失败", e);
            return "";
        }
        return result.trim();
    }
}