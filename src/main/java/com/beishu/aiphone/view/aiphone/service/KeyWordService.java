package com.beishu.aiphone.view.aiphone.service;

import java.util.List;

import com.beishu.aiphone.view.aiphone.dao.KeyWordDao;
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
**  关键词业务层类
*******************************************************************************/
public class KeyWordService {

    private KeyWordDao wordDao = new KeyWordDao();
    
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
        return wordDao.listKeyWord(keyWord);
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
        return wordDao.listKeyWord(keyWord, index, num);
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
        return wordDao.countKeyWord(keyWord);
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
        return wordDao.insertKeyWord(keyWord);
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
        return wordDao.insertKeyWord(words);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param keyWord 单个关键词
    **Return: 
    **  int 修改行数
    **Descriptions: 
    **  修改关键词
    **---------------------------------------------------------------------------- */
    public int updateKeyWord(KeyWordDO keyWord) {
        return wordDao.updateKeyWord(keyWord);
    }
    
    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param words 关键词集合
    **Return: 
    **  int 修改行数
    **Descriptions: 
    **  批量修改关键词
    **---------------------------------------------------------------------------- */
    public int updateKeyWord(List<KeyWordDO> words) {
        return wordDao.updateKeyWord(words);
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
        return wordDao.deleteKeyWord(id);
    }

    /**
    **------------------------------------------------------------------------------
    **Parameters: 
    **  @param ids
    **Return: 
    **  int
    **Descriptions: 
    **  批量删除关键词
    **---------------------------------------------------------------------------- */
    public int deleteKeyWord(List<Integer> ids) {
        return wordDao.deleteKeyWord(ids);
    }
}
