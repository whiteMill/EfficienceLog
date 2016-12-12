package com.stk.model;

/**
 * Created by wangl on 2016/12/9.
 */

public class PushVo {

    /**
     * TIME : 2016-12-12 08:05
     * TO_NAME : 王磊
     * MESS_CONTENT : 123
     * FROM_NAME : 王磊
     * FROM_ID : a16e0e5662fb4fe79ba25c6b2a5fd108
     * TO_ID : a16e0e5662fb4fe79ba25c6b2a5fd108
     * LOG_INDEX : dasdasdasd
     */

    private String TIME;
    private String TO_NAME;
    private String MESS_CONTENT;
    private String FROM_NAME;
    private String FROM_ID;
    private String TO_ID;
    private String LOG_INDEX;

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getTO_NAME() {
        return TO_NAME;
    }

    public void setTO_NAME(String TO_NAME) {
        this.TO_NAME = TO_NAME;
    }

    public String getMESS_CONTENT() {
        return MESS_CONTENT;
    }

    public void setMESS_CONTENT(String MESS_CONTENT) {
        this.MESS_CONTENT = MESS_CONTENT;
    }

    public String getFROM_NAME() {
        return FROM_NAME;
    }

    public void setFROM_NAME(String FROM_NAME) {
        this.FROM_NAME = FROM_NAME;
    }

    public String getFROM_ID() {
        return FROM_ID;
    }

    public void setFROM_ID(String FROM_ID) {
        this.FROM_ID = FROM_ID;
    }

    public String getTO_ID() {
        return TO_ID;
    }

    public void setTO_ID(String TO_ID) {
        this.TO_ID = TO_ID;
    }

    public String getLOG_INDEX() {
        return LOG_INDEX;
    }

    public void setLOG_INDEX(String LOG_INDEX) {
        this.LOG_INDEX = LOG_INDEX;
    }
}
