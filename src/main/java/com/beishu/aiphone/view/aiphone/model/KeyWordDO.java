package com.beishu.aiphone.view.aiphone.model;

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
**  关键词领域模型
*******************************************************************************/
public class KeyWordDO {

    private int id;             // ID
    private int speechId;       // 话术ID
    private int employeeId;     // 员工ID
    private String content;     // 关键词内容
    private String url;         // 关键词地址
    private int duration;       // 最长间隔
    private String createTime;  // 创建时间
    private int type;           // 关键词类型0：普通；1：不说话；2：听不清；3：有效；4：无效；5：挂断
    private String typeStr;     // 类型字符串
    private String note;        // 关键词备注
    
    /** 关联字段 */
    private String employeeName;// 员工姓名
    private String speechTitle; // 话术标题
    
    /** 查询字段 */
    private String beginDate;   // 开始日期
    private String endDate;     // 结束日期

    public static String[] keyword = new String[]{"", "", "", "", "", "", "", "", ""};
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpeechId() {
        return speechId;
    }

    public void setSpeechId(int speechId) {
        this.speechId = speechId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        switch (type) {
        case 0:
            this.typeStr = "普通关键词";
            break;
        case 1:
            this.typeStr = "不说话";
            break;
        case 2:
            this.typeStr = "听不清";
            break;
        case 3:
            this.typeStr = "有效";
            break;
        case 4:
            this.typeStr = "无效";
            break;
        case 5:
            this.typeStr = "挂断";
            break;
        default:
            break;
        }
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSpeechTitle() {
        return speechTitle;
    }

    public void setSpeechTitle(String speechTitle) {
        this.speechTitle = speechTitle;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "KeyWordDO [id=" + id + ", speechId=" + speechId + ", employeeId=" + employeeId + ", content=" + content
                + ", url=" + url + ", duration=" + duration + ", createTime=" + createTime + ", type=" + type
                + ", typeStr=" + typeStr + ", note=" + note + "]";
    }

}
