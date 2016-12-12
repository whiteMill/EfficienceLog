package com.stk.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangl on 2016/12/6.
 */

public class WorkLogVo {


    /**
     * success : true
     * message : 日志查询成功
     * data : [[{"LOG_CONTENT":"2342342","LOG_TIME":"2016-12-06","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-06","BEGIN_TIME":"2016-12-06","ROLE_ID":"01","LOG_INDEX":"63225853b95d4eef8dd7a82db588a744","LOG_TYPE":"01","LOG_ID":"815c2c75a34a4f98b2047c5dbe33aeef","LOG_LEVEL":"A"},{"LOG_CONTENT":"234234","LOG_TIME":"2016-12-06","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-06","BEGIN_TIME":"2016-12-06","ROLE_ID":"01","LOG_INDEX":"63225853b95d4eef8dd7a82db588a744","LOG_TYPE":"01","LOG_ID":"1fdf982742674cecb9dfa9b414043887","LOG_LEVEL":"A"}],[{"LOG_CONTENT":"Cghdscgijasghcxjkdsabvchjgds","LOG_TIME":"2016-12-05","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-03","BEGIN_TIME":"2016-12-03","ROLE_ID":"01","LOG_INDEX":"a3176f22356c45f285a5cedbfeb2d367","LOG_TYPE":"01","LOG_ID":"96441eea74dc4eeb9bf58139474b2438","LOG_LEVEL":"A"},{"LOG_CONTENT":"NbcjxbchnxbchjbxzhjcvzjxhvcjhzxvcmxzvchjxvzC","LOG_TIME":"2016-12-05","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-16","BEGIN_TIME":"2016-12-01","ROLE_ID":"01","LOG_INDEX":"a3176f22356c45f285a5cedbfeb2d367","LOG_TYPE":"01","LOG_ID":"f27167b8643a4e7f8a4ea0e48f156a25","LOG_LEVEL":"A"},{"LOG_CONTENT":" MnxcvjhcvsacjkgvdscghdusaASDASDVCDD","LOG_TIME":"2016-12-05","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-30","BEGIN_TIME":"2016-12-14","ROLE_ID":"01","LOG_INDEX":"a3176f22356c45f285a5cedbfeb2d367","LOG_TYPE":"01","LOG_ID":"fff66b191ba4457cbaa6690b7efddde8","LOG_LEVEL":"B"},{"LOG_CONTENT":"MNCBIDHASL;CHBJDAVSCBILAISHDCGJUX BLDASC","LOG_TIME":"2016-12-05","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-02","BEGIN_TIME":"2016-12-01","ROLE_ID":"01","LOG_INDEX":"a3176f22356c45f285a5cedbfeb2d367","LOG_TYPE":"01","LOG_ID":"038a9fb029f540cda03e973aa02e6ed7","LOG_LEVEL":"C"},{"LOG_CONTENT":",MDNCH;AXldxhcbjkdscm/.ma mkdjvckxdvsda","LOG_TIME":"2016-12-05","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"王磊","END_TIME":"2016-12-03","BEGIN_TIME":"2016-12-03","ROLE_ID":"01","LOG_INDEX":"a3176f22356c45f285a5cedbfeb2d367","LOG_TYPE":"01","LOG_ID":"4c5eda1841fd47b68930162072ada89c","LOG_LEVEL":"C"}],[{"LOG_CONTENT":"Qqqqqqqqqq","LOG_TIME":"2016-12-05","USER_ID":"163f8cdca8f343b58b1289a5e5a6a679","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"郭军","END_TIME":"2016-12-03","BEGIN_TIME":"2016-12-03","ROLE_ID":"01","LOG_INDEX":"f92361d6250246cbb6840b188dbff22f","LOG_TYPE":"01","LOG_ID":"9314af840b77410db3b4ad7e3cddbb59","LOG_LEVEL":"A"},{"LOG_CONTENT":"Dddddddddd","LOG_TIME":"2016-12-05","USER_ID":"163f8cdca8f343b58b1289a5e5a6a679","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"郭军","END_TIME":"2016-12-03","BEGIN_TIME":"2016-12-01","ROLE_ID":"01","LOG_INDEX":"f92361d6250246cbb6840b188dbff22f","LOG_TYPE":"01","LOG_ID":"2252a95133ec44fbaac7da5ba55df022","LOG_LEVEL":"B"},{"LOG_CONTENT":"Fffffffff","LOG_TIME":"2016-12-05","USER_ID":"163f8cdca8f343b58b1289a5e5a6a679","ROLE_NAME":"软件组","LOG_FLAG":"1","NAME":"郭军","END_TIME":"2016-12-08","BEGIN_TIME":"2016-12-03","ROLE_ID":"01","LOG_INDEX":"f92361d6250246cbb6840b188dbff22f","LOG_TYPE":"01","LOG_ID":"4a57d2496c38481699b4fd7a36a179a7","LOG_LEVEL":"C"}],[{"LOG_CONTENT":"213123123","LOG_TIME":"2016-12-06","USER_ID":"a16e0e5662fb4fe79ba25c6b2a5fd108","ROLE_NAME":"软件组","LOG_FLAG":"0","NAME":"王磊","END_TIME":"2016-12-06","BEGIN_TIME":"2016-12-06","ROLE_ID":"01","LOG_INDEX":"41bcca38c8ea4849ad9b76a47a169578","LOG_TYPE":"01","LOG_ID":"6e39846bf02544d58d69c891270df54c","LOG_LEVEL":"A"}]]
     */

    private boolean success;
    private String message;
    /**
     * LOG_CONTENT : 2342342
     * LOG_TIME : 2016-12-06
     * USER_ID : a16e0e5662fb4fe79ba25c6b2a5fd108
     * ROLE_NAME : 软件组
     * LOG_FLAG : 1
     * NAME : 王磊
     * END_TIME : 2016-12-06
     * BEGIN_TIME : 2016-12-06
     * ROLE_ID : 01
     * LOG_INDEX : 63225853b95d4eef8dd7a82db588a744
     * LOG_TYPE : 01
     * LOG_ID : 815c2c75a34a4f98b2047c5dbe33aeef
     * LOG_LEVEL : A
     */

    private List<List<WorkLog>> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<WorkLog>> getData() {
        return data;
    }

    public void setData(List<List<WorkLog>> data) {
        this.data = data;
    }

    public static class WorkLog implements Serializable{
        private String LOG_CONTENT;
        private String LOG_TIME;
        private String USER_ID;
        private String ROLE_NAME;
        private String LOG_FLAG;
        private String NAME;
        private String END_TIME;
        private String BEGIN_TIME;
        private String ROLE_ID;
        private String LOG_INDEX;
        private String LOG_TYPE;
        private String LOG_ID;
        private String LOG_LEVEL;
        private String COMMENT_AMOUNT;

        public String getCOMMENT_AMOUNT() {
            return COMMENT_AMOUNT;
        }

        public void setCOMMENT_AMOUNT(String COMMENT_AMOUNT) {
            this.COMMENT_AMOUNT = COMMENT_AMOUNT;
        }

        public String getLOG_CONTENT() {
            return LOG_CONTENT;
        }

        public void setLOG_CONTENT(String LOG_CONTENT) {
            this.LOG_CONTENT = LOG_CONTENT;
        }

        public String getLOG_TIME() {
            return LOG_TIME;
        }

        public void setLOG_TIME(String LOG_TIME) {
            this.LOG_TIME = LOG_TIME;
        }

        public String getUSER_ID() {
            return USER_ID;
        }

        public void setUSER_ID(String USER_ID) {
            this.USER_ID = USER_ID;
        }

        public String getROLE_NAME() {
            return ROLE_NAME;
        }

        public void setROLE_NAME(String ROLE_NAME) {
            this.ROLE_NAME = ROLE_NAME;
        }

        public String getLOG_FLAG() {
            return LOG_FLAG;
        }

        public void setLOG_FLAG(String LOG_FLAG) {
            this.LOG_FLAG = LOG_FLAG;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String NAME) {
            this.NAME = NAME;
        }

        public String getEND_TIME() {
            return END_TIME;
        }

        public void setEND_TIME(String END_TIME) {
            this.END_TIME = END_TIME;
        }

        public String getBEGIN_TIME() {
            return BEGIN_TIME;
        }

        public void setBEGIN_TIME(String BEGIN_TIME) {
            this.BEGIN_TIME = BEGIN_TIME;
        }

        public String getROLE_ID() {
            return ROLE_ID;
        }

        public void setROLE_ID(String ROLE_ID) {
            this.ROLE_ID = ROLE_ID;
        }

        public String getLOG_INDEX() {
            return LOG_INDEX;
        }

        public void setLOG_INDEX(String LOG_INDEX) {
            this.LOG_INDEX = LOG_INDEX;
        }

        public String getLOG_TYPE() {
            return LOG_TYPE;
        }

        public void setLOG_TYPE(String LOG_TYPE) {
            this.LOG_TYPE = LOG_TYPE;
        }

        public String getLOG_ID() {
            return LOG_ID;
        }

        public void setLOG_ID(String LOG_ID) {
            this.LOG_ID = LOG_ID;
        }

        public String getLOG_LEVEL() {
            return LOG_LEVEL;
        }

        public void setLOG_LEVEL(String LOG_LEVEL) {
            this.LOG_LEVEL = LOG_LEVEL;
        }
    }
}
