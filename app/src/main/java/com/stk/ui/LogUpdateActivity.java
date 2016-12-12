package com.stk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.adapter.LogUpdateAdapter;
import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.model.LogVo;
import com.stk.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class LogUpdateActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent intent;
    private String LOG_TYPE;
    private String LOG_INDEX;
    private ListView updateListView;
    private TextView updateLog;
    private TextView chooseText;
    private LogUpdateAdapter logUpdateAdapter;
    private int flag=0;
    private List<LogVo> logVoList = new ArrayList<>();
    private List<LogVo> logVoList1 = null;
    private ArrayList<LogVo> comLogVoList = new ArrayList<>();
    private ArrayList<LogVo> unComLogVoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_update);
        initView();
        intent = getIntent();
        LOG_INDEX = intent.getStringExtra("log_index");
        LOG_TYPE  = intent.getStringExtra("log_type");
        recentLog();
    }

    private void initView(){
       updateListView = (ListView) findViewById(R.id.updateListView);
        updateLog = (TextView) findViewById(R.id.updateLog);
        chooseText = (TextView) findViewById(R.id.chooseText);
        chooseText.setOnClickListener(this);
        updateLog.setOnClickListener(this);
    }

    private void recentLog(){
        OkGo.post(URL.RECENT_LOG)
                .tag(this)
                .params("LOG_INDEX",LOG_INDEX)
                .params("USER_ID",MyApplication.getUserVo().getUSER_ID())
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                                Log.d("sdasjngnj",s);
                                logVoList1= new Gson().fromJson(jsonObject.getString("data"),new TypeToken<List<LogVo>>() {
                                }.getType());
                                for (int i = 0; i < logVoList1.size(); i++) {
                                    if(logVoList1.get(i).getLOG_FLAG().equals("1")){
                                        logVoList.add(logVoList1.get(i));
                                    }
                                }

                                logUpdateAdapter  = new LogUpdateAdapter(LogUpdateActivity.this,logVoList);
                                updateListView.setAdapter(logUpdateAdapter);
                            }
                        }catch (JSONException e){

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateLog:
                for (int i = 0; i < logVoList.size(); i++) {
                    if(logUpdateAdapter.getIsSelected().get(i)){
                        logVoList.get(i).setLOG_FLAG("0");
                        Log.d("已完成的计划position",i+"");
                        comLogVoList.add(logVoList.get(i));
                    }else{
                        logVoList.get(i).setLOG_FLAG("1");
                        unComLogVoList.add(logVoList.get(i));
                    }
                }
                Log.d("已完成的计划position",comLogVoList.toString());
                updateLog();
                break;

            case R.id.chooseText:
                  if(flag==0){
                      for (int i = 0; i <logVoList.size() ; i++) {
                          logUpdateAdapter.getIsSelected().put(i,true);
                      }
                      logUpdateAdapter.notifyDataSetChanged();
                      chooseText.setText("取消全选");
                      flag=1;
                  }else{
                      for (int i = 0; i <logVoList.size() ; i++) {
                          logUpdateAdapter.getIsSelected().put(i,false);
                      }
                      logUpdateAdapter.notifyDataSetChanged();
                      chooseText.setText("全选");
                      flag=0;
                  }
                break;
        }
    }

   /* private void CollectionLog(){
        for (LogVo logVo :logVoList) {
            switch (logVo.getLOG_LEVEL()){
                case "A":
                    logVo.setROLE_ID(a_position+++"");
                    break;
                case "B":
                    logVo.setROLE_ID(b_position+++"");
                    break;
                case "C":
                    logVo.setROLE_ID(c_position+++"");
                    break;

            }
        }
    }*/

    private void updateLog(){
        OkGo.post(URL.UPDATE_LOG_STATE)
                .tag(this)
                .params("RECENT_LOG","{"+"\"data\":"+comLogVoList.toString()+"}")
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                                Toast.makeText(LogUpdateActivity.this, "日志更新成功", Toast.LENGTH_SHORT).show();
                                Intent inte = new Intent(LogUpdateActivity.this,LogActivity.class);
                                inte.putExtra("log_type",LOG_TYPE);
                                inte.putExtra("list", (Serializable)unComLogVoList);
                                startActivity(inte);
                                finish();
                            }else{
                                Toast.makeText(LogUpdateActivity.this, "日志更新失败", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }
}
