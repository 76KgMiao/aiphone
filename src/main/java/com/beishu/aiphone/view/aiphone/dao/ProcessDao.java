package com.beishu.aiphone.view.aiphone.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.beishu.aiphone.common.Log;
import com.beishu.aiphone.util.JDBCUtil;
import com.beishu.aiphone.view.aiphone.model.ProcessDO;

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
**  话术流程持久层类
*******************************************************************************/
public class ProcessDao {

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param speechId 话术ID
    **Return: 
    **  List<ProcessDO>
    **Descriptions: 
    **  查询指定话术ID的话术流程集合。
    **---------------------------------------------------------------------------- */
    public List<ProcessDO> listProcess(int speechId) {
        List<ProcessDO> result = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            QueryRunner query = new QueryRunner();
            String sql = "SELECT "
                            + "SP.ID AS id,"
                            + "SP.SPEECH_ID AS speechId,"
                            + "SP.PORCESS_URL AS url,"
                            + "SP.PROCESS_SERIAL AS serial,"
                            + "SP.PROCESS_NOTE AS note,"
                            + "SP.PROCESS_DURATION AS duration,"
                            + "SP.PROCESS_TIME_LENGTH AS timeLength "
                       + "FROM "
                            + "SMART_PROCESS SP "
                       + "WHERE "
                            + "SP.SPEECH_ID = ?";
            result = query.query(conn, sql, new BeanListHandler<>(ProcessDO.class), speechId);
        } catch (Exception e) {
            Log.phone_log.error("查询指定话术的流程集合", e);
        }
        return result;
    }
    
}
