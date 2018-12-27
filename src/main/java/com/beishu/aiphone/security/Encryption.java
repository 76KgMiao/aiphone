package com.beishu.aiphone.security;


import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

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
**  加密/解密类
*******************************************************************************/ 
public class Encryption {
	
	/**
	**------------------------------------------------------------------------------
	**Parameters: 
	**  @param str 加密前的字符串
	**Return: 
	**  String 加密后的字符串
	**Descriptions: 
	**  加密
	**---------------------------------------------------------------------------- */
    public static String encryption(String str) {
        if (str == null) {
            return null;
        }

        String ret = null;
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec("5M5NB2C1C5VJ88M5".getBytes("utf-8"), "AES"));
            /*
             * encrypt the clear text
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream os = new CipherOutputStream(baos, cipher);
            os.write(str.getBytes());
            os.close();

            /*
             * get the encrypted bytes and encode to a string
             */
            byte[] ba = baos.toByteArray();
            ret = Base64.getEncoder().encodeToString(ba);
        } catch (Exception e) {
            Log.phone_log.error("Encryption fail:", e);
            return "";
        }
        return ret;
    } 
	
	/**
	**------------------------------------------------------------------------------
	**Parameters: 
	**  @param str 解密前字符串
	**Return: 
	**  String 解密后字符串
	**Descriptions: 
	**  解密
	**---------------------------------------------------------------------------- */
    public static String decryption(String str) {
        if (str == null)
            return "";

        String ret = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec("5M5NB2C1C5VJ88M5".getBytes("utf-8"), "AES"));

            /*
             * decode the cipher text from base64 back to a byte array
             */
            byte[] decba = Base64.getDecoder().decode(str);

            /*
             * decrpyt the bytes
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream os = new CipherOutputStream(baos, cipher);
            os.write(decba);
            os.close();

            ret = baos.toString();

        } catch (Exception e) {
            Log.phone_log.error("Decryption fail:", e);
            return "";
        }
        return ret;
    }
	
}
