package com.beishu.aiphone.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beishu.aiphone.common.AiSession;
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
**  Jul 28, 2018
**Modified History: 
**  <Author>  <Version>  <Date>       <Description> 
**  Miaoqx    V1.0       Jul 28, 2018      setup 
**Descriptions: 
**  Java数据库连接工具类
*******************************************************************************/ 
public class JDBCUtil {
	/**
	 * The Logger object
	 */
	private static final Logger logger = LoggerFactory.getLogger(JDBCUtil.class);
	/**
	 * Default connection params
	 */
	private static String connparams = "?useUnicode=true&"
									 + "characterEncoding=utf8&"
									 + "jdbcCompliantTruncation=false&"
									 + "connectTimeout=3000&"
									 + "allowMultiQueries=true&"
									 + "initialTimeout=1&"
									 + "maxReconnects=3&"
									 + "autoReconnect=true&"
									 + "zeroDateTimeBehavior=convertToNull&"
									 + "jdbcCompliantTruncation=false";
	
	/**
	 * The static code block is get the config file, and initialize the pool.
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			logger.error("Load jdbc driver fail:" + e);
		}
	}

	/**
	**------------------------------------------------------------------------------
	**Parameters: 
	**  @return
	**Return: 
	**  Connection
	**Descriptions: 
	**  获取数据库连接
	**---------------------------------------------------------------------------- */
	public static Connection getConnection() {
	    Connection result = null;
        String url = "jdbc:mysql://" + AiSession.server_ip + ":3306/smart_phone" + connparams;
        String username = "root";
        String password = "dev1";
        try {
            result = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Log.phone_log.error("Get database conn fail:", e);
            throw new Error("No database conn.");
        }
        return result;
	}
	
	/**
	**------------------------------------------------------------------------------
	**Parameters: 
	**  @param conn
	**Return: 
	**  void
	**Descriptions: 
	**  关闭数据库连接
	**---------------------------------------------------------------------------- */
	public static void closeConn(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	**------------------------------------------------------------------------------
	**Parameters: 
	**  @param conn
	**Return: 
	**  void
	**Descriptions: 
	**  回滚
	**---------------------------------------------------------------------------- */
	public static void rollBack(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
