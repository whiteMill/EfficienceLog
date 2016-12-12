package com.stk.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.adapter.WorkAdapter;
import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.model.LogVo;
import com.stk.model.WorkLogVo;
import com.stk.ui.LogActivity;
import com.stk.ui.LogCommentActivity;
import com.stk.ui.LogUpdateActivity;
import com.stk.utils.LogPopwindow;
import com.stk.utils.URL;
import com.stk.view.AbPullToRefreshView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wangl on 2016/11/30.
 */

public class WorkFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TextView addLog;
    private LogPopwindow logPopwindow;
    private Intent intent;
    private AbPullToRefreshView pullToRefreshView;
    private ListView logListView;
    private List<List<WorkLogVo.WorkLog>> logList;
    private ArrayList<LogVo> logVoArrayList =new ArrayList<>();
    private ProgressDialog progressDialog;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 111:
                    queryIsExist("01");
                    break;
                case 222:
                    queryIsExist("02");
                    break;
                case 333:
                    queryIsExist("03");
                    break;
                case 444:
                    queryIsExist("04");
                    break;
                case 555:
                    logPopwindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.work_fragment, container, false);
        initView();
        pullHeadView();
        pullFootView();
        pullToRefreshView.startLayoutAnimation();
        queryAllLog();
        return view;
    }

    private void initView(){
        progressDialog = new ProgressDialog(getActivity(),R.style.MyDialog);
        addLog = (TextView) view.findViewById(R.id.addLog);
        logListView = (ListView) view.findViewById(R.id.logListView);
        logPopwindow = new LogPopwindow(getActivity(),mHandler);
        pullToRefreshView = (AbPullToRefreshView) view.findViewById(R.id.mRefreshView);
        addLog.setOnClickListener(this);
        logListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), LogCommentActivity.class);
                List<WorkLogVo.WorkLog> workLogList =  logList.get(position);
                intent.putExtra("list", (Serializable) workLogList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addLog:
                if (logPopwindow != null) {
                    if (!logPopwindow.isShowing()) {
                        logPopwindow.showAtLocation(view.findViewById(R.id.addLog),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    } else {
                        logPopwindow.dismiss();
                    }
                }
                break;
        }
    }

    public void pullHeadView(){
        pullToRefreshView.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(AbPullToRefreshView view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        OkGo.post(URL.QUERY_LOG)
                                .tag(this)
                                .params("user_id", MyApplication.getUserVo().getUSER_ID())
                                .cacheKey("catchKey")
                                .cacheMode(CacheMode.DEFAULT)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        pullToRefreshView.onFooterLoadFinish();
                                        pullToRefreshView.onHeaderRefreshFinish();
                                        WorkLogVo workLogVo = new Gson().fromJson(s, WorkLogVo.class);
                                        try{
                                            if(workLogVo.isSuccess()){
                                                Toast.makeText(getActivity(),"日志获取成功", Toast.LENGTH_SHORT).show();
                                                logList =  workLogVo.getData();
                                                logListView.setAdapter(new WorkAdapter(logList,getActivity()));
                                            }else{
                                                Toast.makeText(getActivity(),workLogVo.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (Exception e){
                                            queryAllLog();
                                        }

                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        Toast.makeText(getActivity(),"服务器请求失败", Toast.LENGTH_SHORT).show();
                                        pullToRefreshView.onFooterLoadFinish();
                                        pullToRefreshView.onHeaderRefreshFinish();
                                    }
                                });
                    }
                },1500);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //queryAllLog();
    }

    public void pullFootView(){
        pullToRefreshView.setOnFooterLoadListener(new AbPullToRefreshView.OnFooterLoadListener() {
            @Override
            public void onFooterLoad(AbPullToRefreshView view) {
                pullToRefreshView.onFooterLoadFinish();
                pullToRefreshView.onHeaderRefreshFinish();
            }
        });
    }

    private void queryAllLog(){
        progressDialog.show();
        OkGo.post(URL.QUERY_LOG)
                .tag(this)
                .params("user_id", MyApplication.getUserVo().getUSER_ID())
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        progressDialog.dismiss();
                        WorkLogVo workLogVo = new Gson().fromJson(s, WorkLogVo.class);
                        try{
                            if(workLogVo.isSuccess()){
                                //Toast.makeText(getActivity(),"日志获取成功", Toast.LENGTH_SHORT).show();
                                logList =  workLogVo.getData();
                                logListView.setAdapter(new WorkAdapter(logList,getActivity()));
                            }else{
                                Toast.makeText(getActivity(),workLogVo.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            queryAllLog();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(),"服务器请求失败", Toast.LENGTH_SHORT).show();
                        super.onError(call, response, e);
                    }
                });
    }


    private void queryIsExist(final String type){
        OkGo.post(URL.QUERY_ISEXIST)
                .tag(this)
                .params("USER_ID", MyApplication.getUserVo().getUSER_ID())
                .params("LOG_TYPE",type)
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                                if(jsonObject.getString("data").isEmpty()){
                                    jumpActivity(type,"");
                                }else{
                                   // Toast.makeText(getActivity(),"有日志数据", Toast.LENGTH_SHORT).show();
                                    jumpActivity(type,jsonObject.getString("data"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

      private void jumpActivity(String logTYpe,String logIndex){
                if(logIndex==""){
                    intent =new Intent(getActivity(), LogActivity.class);
                    intent.putExtra("list",(Serializable)logVoArrayList);
                }else{
                    intent =new Intent(getActivity(), LogUpdateActivity.class);
                }
                intent.putExtra("log_type",logTYpe);
                intent.putExtra("log_index",logIndex);
                startActivity(intent);
                logPopwindow.dismiss();
     }




}
