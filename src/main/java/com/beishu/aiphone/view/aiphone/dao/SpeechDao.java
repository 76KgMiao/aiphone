package com.beishu.aiphone.view.aiphone.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.beishu.aiphone.common.Log;
import com.beishu.aiphone.util.JDBCUtil;
import com.beishu.aiphone.view.aiphone.model.ProcessDO;
import com.beishu.aiphone.view.aiphone.model.SpeechDO;

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
**  话术持久层类
*******************************************************************************/
public class SpeechDao {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param speech 条件：员工ID，话术标题，话术备注。
    **Return: 
    **  List<SpeechDO>
    **Descriptions: 
    **  查询指定条件的话术集合。
    **---------------------------------------------------------------------------- */
    public List<SpeechDO> listSpeech(SpeechDO speech) {
        List<SpeechDO> result = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "SELECT "
                            + "SS.ID AS id, "
                            + "SS.SPEECH_TITLE AS title, "
                            + "SS.EMPLOYEE_ID AS employeeId, "
                            + "SS.SPEECH_NOTE AS note, "
                            + "SS.SPEECH_COUNT AS count, "
                            + "SS.SPEECH_STATE AS state, "
                            + "SE.EMPLOYEE_NAME AS employeeName "
                       + "FROM "
                            + "SMART_SPEECH SS "
                       + "LEFT JOIN "
                            + "SMART_EMPLOYEE SE "
                       + "ON "
                            + "SS.EMPLOYEE_ID = SE.ID "
                       + "WHERE "
                            + "1=1 ";
            if (speech.getEmployeeId() > 0) {
                sql += "AND SS.EMPLOYEE_ID = " + speech.getEmployeeId() + " ";
            }
            if (speech.getTitle() != null) {
                sql += "AND SS.SPEECH_TITLE LIKE '%" + speech.getTitle() + "'% ";
            }
            if (speech.getNote() != null) {
                sql += "AND SS.SPEECH_NOTE LIKE '%" + speech.getNote() + "'% ";
            }
            result = query.query(conn, sql, new BeanListHandler<>(SpeechDO.class));
        } catch (Exception e) {
            Log.phone_log.error("查询指定条件的话术集合", e);
        }
        return result;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param speech 话术对象
    **  @param processes 话术流程集合
    **Return: 
    **  int 新增行数：1表示成功；0表示失败。
    **Descriptions: 
    **  新增话术：先新增话术，再新增话术流程。
    **---------------------------------------------------------------------------- */
    public int insertSpeech(SpeechDO speech, List<ProcessDO> processes) {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            
            QueryRunner query = new QueryRunner();
            // 新增话术
            String insertSpeechSql = "INSERT INTO SMART_SPEECH "
                                        + "(SPEECH_TITLE, EMPLOYEE_ID, SPEECH_NOTE, SPEECH_COUNT)"
                                   + "VALUES "
                                        + "(?, ?, ?, ?)";
            query.update(conn, insertSpeechSql, speech.getTitle(), speech.getEmployeeId(), speech.getNote(), speech.getCount());
            
            // 新增话术流程
            String insertProcessSql = "INSERT INTO SMART_PROCESS "
                                        + "(SPEECH_ID, PORCESS_URL, PROCESS_SERIAL, PROCESS_NOTE, PROCESS_DURATION, PROCESS_TIME_LENGTH)"
                                    + "VALUES "
                                        + "(?, ?, ?, ?, ?, ?)";
            for (ProcessDO process : processes) {
                query.update(conn, insertProcessSql, speech.getId(), process.getUrl(), process.getSerial(),
                        process.getNote(), process.getDuration(), process.getTimeLength());
            }
            
            conn.commit();
        } catch (Exception e) {
            Log.phone_log.error("新增话术", e);
            JDBCUtil.rollBack(conn);
            return 0;
        }
        return 1;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param speech 话术对象
    **  @param processes 话术流程集合
    **Return: 
    **  int 更新行数：1表示成功；0表示失败。
    **Descriptions: 
    **  更新话术：先修改话术，再删除该话术的流程，在新增流程。
    **---------------------------------------------------------------------------- */
    public int updateSpeech(SpeechDO speech, List<ProcessDO> processes) {
        Connection conn = JDBCUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            
            QueryRunner query = new QueryRunner();
            // 更新话术
            String updateSpeechSql = "UPDATE "
                                        + "SMART_SPEECH "
                                   + "SET "
                                        + "SPEECH_TITLE = ?, "
                                        + "SPEECH_NOTE = ?, "
                                        + "SPEECH_COUNT = ? "
                                   + "WHERE "
                                        + "ID = ? "
                                   + "LIMIT 1";
            query.update(conn, updateSpeechSql, speech.getTitle(), speech.getNote(), speech.getCount());
            
            // 删除话术流程
            String deleteProcessSql = "DELETE FROM SMART_PROCESS WHERE SPEECH_ID = ?";
            query.update(conn, deleteProcessSql, speech.getId());
            
            // 新增话术流程
            String insertProcessSql = "INSERT INTO SMART_PROCESS "
                                        + "(SPEECH_ID, PORCESS_URL, PROCESS_SERIAL, PROCESS_NOTE, PROCESS_DURATION, PROCESS_TIME_LENGTH)"
                                    + "VALUES "
                                        + "(?, ?, ?, ?, ?, ?)";
            for (ProcessDO process : processes) {
                query.update(conn, insertProcessSql, speech.getId(), process.getUrl(), process.getSerial(),
                        process.getNote(), process.getDuration(), process.getTimeLength());
            }
            
            conn.commit();
        } catch (Exception e) {
            Log.phone_log.error("更新指定话术", e);
            JDBCUtil.rollBack(conn);
            return 0;
        }
        return 1;
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param id 话术ID
    **Return: 
    **  int 删除行数
    **Descriptions: 
    **  删除指定话术：并不是真的删除，只是修改状态为禁用。
    **---------------------------------------------------------------------------- */
    public int deleteSpeech(int id) {
        int result = 0;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "UPDATE SMART_SPEECH SET SPEECH_STATE = 0 WHERE ID = ? LIMIT 1";
            result += query.update(conn, sql, id);
        } catch (Exception e) {
            Log.phone_log.error("删除指定话术", e);
        }
        return result;
    }
    
}
