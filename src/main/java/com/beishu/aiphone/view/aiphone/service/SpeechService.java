package com.beishu.aiphone.view.aiphone.service;

import java.util.List;

import com.beishu.aiphone.view.aiphone.dao.ProcessDao;
import com.beishu.aiphone.view.aiphone.dao.SpeechDao;
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
**  话术业务层类
*******************************************************************************/
public class SpeechService {

    private SpeechDao speechDao = new SpeechDao();
    
    private ProcessDao processDao = new ProcessDao();
    
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
        return speechDao.listSpeech(speech);
    }
    
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
        return processDao.listProcess(speechId);
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
        return speechDao.insertSpeech(speech, processes);
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
        return speechDao.updateSpeech(speech, processes);
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
        return speechDao.deleteSpeech(id);
    }
    
}
