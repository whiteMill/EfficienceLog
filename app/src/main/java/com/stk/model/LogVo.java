package com.stk.model;

import java.io.Serializable;

/**
 * Created by admin on 2016/12/1.
 */

public class LogVo implements Serializable{
    private String LOG_ID;
    private String LOG_INDEX;
    private String LOG_CONTENT;
    private String USER_ID;
    private String LOG_TIME;
    private String LOG_FLAG;
    private String BEGIN_TIME;
    private String END_TIME;
    private String LOG_LEVEL;
    private String LOG_TYPE;
    private String ROLE_ID;

    public String getLOG_ID() {
        return LOG_ID;
    }

    public void setLOG_ID(String LOG_ID) {
        this.LOG_ID = LOG_ID;
    }

    public String getLOG_INDEX() {
        return LOG_INDEX;
    }

    public void setLOG_INDEX(String LOG_INDEX) {
        this.LOG_INDEX = LOG_INDEX;
    }

    public String getLOG_CONTENT() {
        return LOG_CONTENT;
    }

    public void setLOG_CONTENT(String LOG_CONTENT) {
        this.LOG_CONTENT = LOG_CONTENT;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getLOG_TIME() {
        return LOG_TIME;
    }

    public void setLOG_TIME(String LOG_TIME) {
        this.LOG_TIME = LOG_TIME;
    }

    public String getLOG_FLAG() {
        return LOG_FLAG;
    }

    public void setLOG_FLAG(String LOG_FLAG) {
        this.LOG_FLAG = LOG_FLAG;
    }

    public String getBEGIN_TIME() {
        return BEGIN_TIME;
    }

    public void setBEGIN_TIME(String BEGIN_TIME) {
        this.BEGIN_TIME = BEGIN_TIME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getLOG_LEVEL() {
        return LOG_LEVEL;
    }

    public void setLOG_LEVEL(String LOG_LEVEL) {
        this.LOG_LEVEL = LOG_LEVEL;
    }

    public String getLOG_TYPE() {
        return LOG_TYPE;
    }

    public void setLOG_TYPE(String LOG_TYPE) {
        this.LOG_TYPE = LOG_TYPE;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    @Override
    public String toString() {
        return "{" +
                "LOG_ID='" + LOG_ID + '\'' +
                ", LOG_INDEX='" + LOG_INDEX + '\'' +
                ", LOG_CONTENT='" + LOG_CONTENT + '\'' +
                ", USER_ID='" + USER_ID + '\'' +
                ", LOG_TIME='" + LOG_TIME + '\'' +
                ", LOG_FLAG='" + LOG_FLAG + '\'' +
                ", BEGIN_TIME='" + BEGIN_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", LOG_LEVEL='" + LOG_LEVEL + '\'' +
                ", LOG_TYPE='" + LOG_TYPE + '\'' +
                ", ROLE_ID='" + ROLE_ID + '\'' +
                '}';
    }
}
