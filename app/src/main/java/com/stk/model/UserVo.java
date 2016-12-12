package com.stk.model;

/**
 * Created by admin on 2016/12/2.
 */

public class UserVo {
    private String USER_ID;
    private String USER_NAME;
    private String PASSWORD;
    private String NAME;
    private String ROLE_ID;
    private String PHONE;
    private String STATUS;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "STATUS='" + STATUS + '\'' +
                ", PHONE='" + PHONE + '\'' +
                ", ROLE_ID='" + ROLE_ID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", USER_ID='" + USER_ID + '\'' +
                '}';
    }
}
