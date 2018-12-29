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
**  话术流程领域模型
*******************************************************************************/
public class ProcessDO {
    
    private int id;
    private int speechId;
    private String url;
    private int serial;
    private String note;
    private int duration;
    private int timeLength;
    
    public static String[] process = new String[]{"", "", "", "", "", ""};

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    @Override
    public String toString() {
        return "ProcessDO [id=" + id + ", speechId=" + speechId + ", url=" + url + ", serial=" + serial + ", note="
                + note + ", duration=" + duration + ", timeLength=" + timeLength + "]";
    }

}
