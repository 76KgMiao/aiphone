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
**  话术领域模型
*******************************************************************************/
public class SpeechDO {

    private int id;
    private String title;
    private int employeeId;
    private String note;
    private int count;
    private int state;
    
    /** 关联字段 */
    private String employeeName;
    
    public static String[] speech = new String[]{"", "", "", "", "", "", ""};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "SpeechDO [id=" + id + ", title=" + title + ", employeeId=" + employeeId + ", note=" + note + ", count="
                + count + ", state=" + state + ", employeeName=" + employeeName + "]";
    }
    
}
