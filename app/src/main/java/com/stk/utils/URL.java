package com.stk.utils;

/**
 * Created by wangl on 2016/11/29.
 */

public class URL {

    //推送消息
    public static final String PUSH = "http://192.168.0.239:8080/eLog/eLogAppController/pushMessage";
    //登陆
    public static final String LOGIN = "http://192.168.0.239:8080/eLog/eLogAppController/Login";
    //日志提交
    public static final String LOG_COMMIT = "http://192.168.0.239:8080/eLog/eLogAppController/LogCommit";
    //查询所有日志
    public static final String QUERY_LOG = "http://192.168.0.239:8080/eLog/eLogAppController/QueryAllLog";
    //更新密码
    public static final String UPDATE_PASSWORD = "http://192.168.0.239:8080/eLog/eLogAppController/updatePass";
    //更新电话
    public static final String UPDATE_PHONE = "http://192.168.0.239:8080/eLog/eLogAppController/updatePhone";
    //提交评论
    public static final String COMMIT_COMMENT = "http://192.168.0.239:8080/eLog/eLogAppController/commitComment";
    //查询评论
    public static final String QUERY_COMMENT = "http://192.168.0.239:8080/eLog/eLogAppController/QueryComment";
    //查询是否存在最新日志
    public static final String QUERY_ISEXIST = "http://192.168.0.239:8080/eLog/eLogAppController/updateLog";
    //查询最近日志
    public static final String RECENT_LOG = "http://192.168.0.239:8080/eLog/eLogAppController/recentLog";
    //更新日志状态
    public static final String UPDATE_LOG_STATE = "http://192.168.0.239:8080/eLog/eLogAppController/updateLogState";
    //查询最近评论
    public static final String RECENT_COMMENT = "http://192.168.0.239:8080/eLog/eLogAppController/commentRecent";
    //查询Log
    public static final String QUERY_LOG_INDEX = "http://192.168.0.239:8080/eLog/eLogAppController/queryLogByIndex";

}
