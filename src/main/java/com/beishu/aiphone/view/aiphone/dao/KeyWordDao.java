package com.beishu.aiphone.view.aiphone.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.beishu.aiphone.common.Log;
import com.beishu.aiphone.util.JDBCUtil;
import com.beishu.aiphone.view.aiphone.model.KeyWordDO;

/**
******************************************************************************* 
**Copyright (C) 
**  BeiShu Co., Ltd. All Rights Reserved. 
**Version: 
**  1.0 
**Author: 
**  Miaoqx
**Date: 
**  Dec 28, 2018
**Modified History: 
**  <Author>  <Version>  <Date>       <Description> 
**  Miaoqx    V1.0       Dec 28, 2018      setup 
**Descriptions: 
**  关键词持久层类
*******************************************************************************/
public class KeyWordDao {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 条件
    **Return: 
    **  List<KeyWordDO>
    **Descriptions: 
    **  查询指定条件的关键词集合
    **---------------------------------------------------------------------------- */
    public List<KeyWordDO> listKeyWord(KeyWordDO keyWord) {
        List<KeyWordDO> result = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "SELECT "
                            + "SK.ID AS id, "
                            + "SK.SPEECH_ID AS speechId, "
                            + "SK.EMPLOYEE_ID AS employeeId, "
                            + "SK.KEYWORD_CONTENT AS content, "
                            + "SK.KEYWORD_URL AS url, "
                            + "SK.KEYWORD_DURATION AS duration, "
                            + "SK.KEYWORD_CREATE_TIME AS createTime, "
                            + "SK.KEYWORD_TYPE AS type, "
                            + "SK.KEYWORD_NOTE AS note, "
                            + "SE.EMPLOYEE_NAME AS employeeName, "
                            + "SS.SPEECH_TITLE AS speechTitle "
                       + "FROM "
                            + "SMART_KEYWORD SK "
                       + "LEFT JOIN "
                            + "SMART_EMPLOYEE SE "
                       + "ON "
                            + "SK.EMPLOYEE_ID = SE.ID "
                       + "LEFT JOIN "
                            + "SMART_SPEECH SS "
                       + "ON "
                            + "SK.SPEECH_ID = SS.ID "
                       + "WHERE "
                            + "1=1 ";
            // 开始时间
            if (keyWord.getBeginDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME >= '" + keyWord.getBeginDate() + "' ";
            }
            // 结束时间
            if (keyWord.getEndDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME < '" + keyWord.getBeginDate() + "' ";
            }
            // 内容：模糊查询
            if (keyWord.getContent() != null) {
                sql += "AND SK.KEYWORD_CONTENT LIKE '%" + keyWord.getContent() + "'% ";
            }
            // 关键词类型
            if (keyWord.getType() >= 0) {
                sql += "AND SK.KEYWORD_TYPE = " + keyWord.getType() + " ";
            }
            // 员工ID
            if (keyWord.getEmployeeId() > 0) {
                sql += "AND SE.EMPLOYEE_ID = " + keyWord.getEmployeeId() + " ";
            }
            // 话术ID
            if (keyWord.getSpeechId() > 0) {
                sql += "AND SE.SPEECH_ID = " + keyWord.getEmployeeId() + " ";
            }
            result = query.query(conn, sql, new BeanListHandler<>(KeyWordDO.class));
        } catch (Exception e) {
            Log.phone_log.error("查询关键词集合", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 条件
    **  @param index 起始索引
    **  @param num 查询个数
    **Return: 
    **  List<KeyWordDO>
    **Descriptions: 
    **  分页查询指定条件的关键词集合
    **---------------------------------------------------------------------------- */
    public List<KeyWordDO> listKeyWord(KeyWordDO keyWord, int index, int num) {
        List<KeyWordDO> result = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "SELECT "
                            + "SK.ID AS id, "
                            + "SK.SPEECH_ID AS speechId, "
                            + "SK.EMPLOYEE_ID AS employeeId, "
                            + "SK.KEYWORD_CONTENT AS content, "
                            + "SK.KEYWORD_URL AS url, "
                            + "SK.KEYWORD_DURATION AS duration, "
                            + "SK.KEYWORD_CREATE_TIME AS createTime, "
                            + "SK.KEYWORD_TYPE AS type, "
                            + "SK.KEYWORD_NOTE AS note, "
                            + "SE.EMPLOYEE_NAME AS employeeName, "
                            + "SS.SPEECH_TITLE AS speechTitle "
                       + "FROM "
                            + "SMART_KEYWORD SK "
                       + "LEFT JOIN "
                            + "SMART_EMPLOYEE SE "
                       + "ON "
                            + "SK.EMPLOYEE_ID = SE.ID "
                       + "LEFT JOIN "
                            + "SMART_SPEECH SS "
                       + "ON "
                            + "SK.SPEECH_ID = SS.ID "
                       + "WHERE "
                            + "1=1 ";
            // 开始时间
            if (keyWord.getBeginDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME >= '" + keyWord.getBeginDate() + "' ";
            }
            // 结束时间
            if (keyWord.getEndDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME < '" + keyWord.getBeginDate() + "' ";
            }
            // 内容：模糊查询
            if (keyWord.getContent() != null) {
                sql += "AND SK.KEYWORD_CONTENT LIKE '%" + keyWord.getContent() + "'% ";
            }
            // 关键词类型
            if (keyWord.getType() >= 0) {
                sql += "AND SK.KEYWORD_TYPE = " + keyWord.getType() + " ";
            }
            // 员工ID
            if (keyWord.getEmployeeId() > 0) {
                sql += "AND SE.EMPLOYEE_ID = " + keyWord.getEmployeeId() + " ";
            }
            // 话术ID
            if (keyWord.getSpeechId() > 0) {
                sql += "AND SE.SPEECH_ID = " + keyWord.getEmployeeId() + " ";
            }
            sql += "LIMIT " + index + ", " + num;
            result = query.query(conn, sql, new BeanListHandler<>(KeyWordDO.class));
        } catch (Exception e) {
            Log.phone_log.error("分页查询指定关键词集合", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 条件
    **Return: 
    **  List<KeyWordDO>
    **Descriptions: 
    **  查询指定条件的关键词总数
    **---------------------------------------------------------------------------- */
    public int countKeyWord(KeyWordDO keyWord) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "SELECT "
                            + "COUNT(*) "
                       + "FROM "
                            + "SMART_KEYWORD SK "
                       + "LEFT JOIN "
                            + "SMART_EMPLOYEE SE "
                       + "ON "
                            + "SK.EMPLOYEE_ID = SE.ID "
                       + "LEFT JOIN "
                            + "SMART_SPEECH SS "
                       + "ON "
                            + "SK.SPEECH_ID = SS.ID "
                       + "WHERE "
                            + "1=1 ";
            // 开始时间
            if (keyWord.getBeginDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME >= '" + keyWord.getBeginDate() + "' ";
            }
            // 结束时间
            if (keyWord.getEndDate() != null) {
                sql += "AND SK.KEYWORD_CREATE_TIME < '" + keyWord.getBeginDate() + "' ";
            }
            // 内容：模糊查询
            if (keyWord.getContent() != null) {
                sql += "AND SK.KEYWORD_CONTENT LIKE '%" + keyWord.getContent() + "'% ";
            }
            // 关键词类型
            if (keyWord.getType() >= 0) {
                sql += "AND SK.KEYWORD_TYPE = " + keyWord.getType() + " ";
            }
            // 员工ID
            if (keyWord.getEmployeeId() > 0) {
                sql += "AND SE.EMPLOYEE_ID = " + keyWord.getEmployeeId() + " ";
            }
            // 话术ID
            if (keyWord.getSpeechId() > 0) {
                sql += "AND SE.SPEECH_ID = " + keyWord.getEmployeeId() + " ";
            }
            result = query.query(conn, sql, new ScalarHandler<Integer>(1));
        } catch (Exception e) {
            Log.phone_log.error("查询指定条件关键词个数", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 单个关键词
    **Return: 
    **  int 新增行数
    **Descriptions: 
    **  新增关键词
    **---------------------------------------------------------------------------- */
    public int insertKeyWord(KeyWordDO keyWord) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "INSERT INTO SMART_KEYWORD "
                            + "(SPEECH_ID, EMPLOYEE_ID, KEYWORD_CONTENT, KEYWORD_URL, KEYWORD_DURATION, KEYWORD_CREATE_TIME, KEYWORD_TYPE, KEYWORD_NOTE) "
                       + "VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?)";
            result = query.update(conn, sql, keyWord.getSpeechId(), keyWord.getEmployeeId(), keyWord.getContent(),
                    keyWord.getUrl(), keyWord.getDuration(), keyWord.getCreateTime(), keyWord.getType(),
                    keyWord.getNote());
        } catch (Exception e) {
            Log.phone_log.error("新增关键词", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param words 关键词集合
    **Return: 
    **  int 新增行数
    **Descriptions: 
    **  批量新增关键词
    **---------------------------------------------------------------------------- */
    public int insertKeyWord(List<KeyWordDO> words) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "INSERT INTO SMART_KEYWORD "
                            + "(SPEECH_ID, EMPLOYEE_ID, KEYWORD_CONTENT, KEYWORD_URL, KEYWORD_DURATION, KEYWORD_CREATE_TIME, KEYWORD_TYPE, KEYWORD_NOTE) "
                       + "VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?)";
            for (KeyWordDO word : words) {
                result += query.update(conn, sql, word.getSpeechId(), word.getEmployeeId(), word.getContent(),
                        word.getUrl(), word.getDuration(), word.getCreateTime(), word.getType(), word.getNote());
            }
        } catch (Exception e) {
            Log.phone_log.error("批量新增关键词", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 单个关键词
    **Return: 
    **  int 更新行数
    **Descriptions: 
    **  修改关键词
    **---------------------------------------------------------------------------- */
    public int updateKeyWord(KeyWordDO keyWord) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "UPDATE "
                            + "SMART_KEYWORD "
                       + "SET "
                            + "KEYWORD_CONTENT = ?, "
                            + "KEYWORD_URL = ?, "
                            + "KEYWORD_NOTE = ?, "
                            + "KEYWORD_DURATION = ? "
                       + "WHERE "
                            + "ID = ?";
            result = query.update(conn, sql, keyWord.getContent(), keyWord.getUrl(), keyWord.getNote(),
                    keyWord.getDuration());
        } catch (Exception e) {
            Log.phone_log.error("修改关键词", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param words 关键词集合
    **Return: 
    **  int 更新行数
    **Descriptions: 
    **  批量修改关键词
    **---------------------------------------------------------------------------- */
    public int updateKeyWord(List<KeyWordDO> words) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "UPDATE "
                            + "SMART_KEYWORD "
                       + "SET "
                            + "KEYWORD_CONTENT = ?, "
                            + "KEYWORD_URL = ?, "
                            + "KEYWORD_NOTE = ?, "
                            + "KEYWORD_DURATION = ? "
                       + "WHERE "
                            + "ID = ?";
            for (KeyWordDO keyWord : words) {
                result += query.update(conn, sql, keyWord.getContent(), keyWord.getUrl(), keyWord.getNote(),
                        keyWord.getDuration());
            }
        } catch (Exception e) {
            Log.phone_log.error("批量修改关键词", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param id 关键词ID
    **Return: 
    **  int 删除行数
    **Descriptions: 
    **  删除指定关键词
    **---------------------------------------------------------------------------- */
    public int deleteKeyWord(int id) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "DELETE FROM SMART_KEYWORD WHERE ID = ? LIMIT 1";
            result = query.update(conn, sql, id);
        } catch (Exception e) {
            Log.phone_log.error("删除关键词", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param ids 关键词ID集合
    **Return: 
    **  int 删除行数
    **Descriptions: 
    **  批量删除关键词
    **---------------------------------------------------------------------------- */
    public int deleteKeyWord(List<Integer> ids) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "DELETE FROM SMART_KEYWORD WHERE ID = ? LIMIT 1";
            for (int id : ids) {
                result += query.update(conn, sql, id);
            }
        } catch (Exception e) {
            Log.phone_log.error("批量删除关键词", e);
        }
        return result;
    }
    
}
