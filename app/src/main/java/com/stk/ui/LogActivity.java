package com.stk.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.adapter.LogBaseAdapter;
import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.model.LogVo;
import com.stk.utils.LogListView;
import com.stk.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private LogListView logListView;
    private Button mAddLog;
    private ArrayList<LogVo> logVoArrayList ;
    private final int A_RESULT_CODE = 1001;
    private final int B_RESULT_CODE = 1002;
    private final int C_RESULT_CODE = 1003;
    private final int REQUEST_CODE = 102;
    private LogBaseAdapter logBaseAdapter;
    private AlertDialog.Builder builder;
    private TextView log_commit;
    private String log_type;
    private Intent intent;
    private SimpleDateFormat ssf;
    private static int  a_position=1;
    private static int  b_position=1;
    private static int  c_position=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initView();
        intent = getIntent();
        log_type = intent.getStringExtra("log_type");
        logVoArrayList = (ArrayList<LogVo>)intent.getSerializableExtra("list");
         initDate();
    }

    private void initDate(){
        for(LogVo logVo:logVoArrayList){
            if(logVo.getLOG_LEVEL().equals("A")){
                  logVo.setLOG_ID(a_position+++"");
                  logVo.setLOG_TIME(ssf.format(new Date()));
            }else if(logVo.getLOG_LEVEL().equals("B")){
                  logVo.setLOG_ID(b_position+++"");
                logVo.setLOG_TIME(ssf.format(new Date()));
            }else{
                  logVo.setLOG_ID(c_position+++"");
                logVo.setLOG_TIME(ssf.format(new Date()));
            }
        }
        logBaseAdapter = new LogBaseAdapter(this,logVoArrayList);
        logListView.setAdapter(logBaseAdapter);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        logListView = (LogListView) findViewById(R.id.logListView);
        mAddLog = (Button) findViewById(R.id.mAddLog);
        log_commit = (TextView) findViewById(R.id.log_commit);
        log_commit.setOnClickListener(this);
        mAddLog.setOnClickListener(this);
        back.setOnClickListener(this);
        ssf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                LogActivity.this.finish();
                break;
            case R.id.mAddLog:
                builder = new AlertDialog.Builder(this);
                builder.setItems(getResources().getStringArray(R.array.ItemEqual), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        switch (arg1) {
                            case 0:
                                Intent aIntent = new Intent(LogActivity.this, LogWriteActivity.class);
                                aIntent.putExtra("logLevel",A_RESULT_CODE );
                                aIntent.putExtra("log_type",log_type);
                                startActivityForResult(aIntent, REQUEST_CODE);
                                break;
                            case 1:
                                Intent bIntent = new Intent(LogActivity.this, LogWriteActivity.class);
                                bIntent.putExtra("logLevel", B_RESULT_CODE);
                                bIntent.putExtra("log_type",log_type);
                                startActivityForResult(bIntent, REQUEST_CODE);
                                break;
                            case 2:
                                Intent cIntent = new Intent(LogActivity.this, LogWriteActivity.class);
                                cIntent.putExtra("logLevel", C_RESULT_CODE);
                                cIntent.putExtra("log_type",log_type);
                                startActivityForResult(cIntent, REQUEST_CODE);
                                break;
                        }
                        arg0.dismiss();
                    }
                });
                builder.show();
               /*;
                Intent intent=new Intent(LogActivity.this,LogWriteActivity.class);
                startActivityForResult(intent,REQUEST_CODE);*/
                break;

            case R.id.log_commit:
                if(logVoArrayList.size()==0){
                    Toast.makeText(this, "请填写相关内容再提交！", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("log_content_sas","{"+"\"data\":"+logVoArrayList.toString()+"}");
                    conmmitLog();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case A_RESULT_CODE:
                    String alogContent = data.getStringExtra("Log_content");
                    String alog_begin = data.getStringExtra("begin_time");
                    String alog_end = data.getStringExtra("end_time");
                    LogVo alogVo = new LogVo();
                    alogVo.setBEGIN_TIME(alog_begin);
                    alogVo.setEND_TIME(alog_end);
                    alogVo.setLOG_CONTENT(alogContent);
                    alogVo.setLOG_FLAG("1");
                    alogVo.setLOG_ID(a_position+++"");
                    alogVo.setLOG_INDEX("");
                    alogVo.setLOG_LEVEL("A");
                    alogVo.setLOG_TIME(ssf.format(new Date()));
                    alogVo.setLOG_TYPE(log_type);
                    Log.d("dadad",MyApplication.getUserVo().getROLE_ID());
                    alogVo.setROLE_ID(MyApplication.getUserVo().getROLE_ID());
                    alogVo.setUSER_ID(MyApplication.getUserVo().getUSER_ID());
                    logVoArrayList.add(alogVo);
                    CollectionDate();
                    logBaseAdapter.notifyDataSetChanged();
                    break;
                case B_RESULT_CODE:
                    String blogContent = data.getStringExtra("Log_content");
                    String blog_begin = data.getStringExtra("begin_time");
                    String blog_end = data.getStringExtra("end_time");
                    LogVo blogVo = new LogVo();
                    blogVo.setBEGIN_TIME(blog_begin);
                    blogVo.setEND_TIME(blog_end);
                    blogVo.setLOG_CONTENT(blogContent);
                    blogVo.setLOG_FLAG("1");
                    blogVo.setLOG_ID(b_position+++"");
                    blogVo.setLOG_INDEX("");
                    blogVo.setLOG_LEVEL("B");
                    blogVo.setLOG_TIME(ssf.format(new Date()));
                    blogVo.setLOG_TYPE(log_type);
                    blogVo.setROLE_ID(MyApplication.getUserVo().getROLE_ID());
                    blogVo.setUSER_ID(MyApplication.getUserVo().getUSER_ID());
                    logVoArrayList.add(blogVo);
                    CollectionDate();
                    logBaseAdapter.notifyDataSetChanged();
                    break;
                case C_RESULT_CODE:
                    String clogContent = data.getStringExtra("Log_content");
                    String clog_begin = data.getStringExtra("begin_time");
                    String clog_end = data.getStringExtra("end_time");
                    LogVo clogVo = new LogVo();
                    clogVo.setBEGIN_TIME(clog_begin);
                    clogVo.setEND_TIME(clog_end);
                    clogVo.setLOG_CONTENT(clogContent);
                    clogVo.setLOG_FLAG("1");
                    clogVo.setLOG_ID(c_position+++"");
                    clogVo.setLOG_INDEX("");
                    clogVo.setLOG_LEVEL("C");
                    clogVo.setLOG_TIME(ssf.format(new Date()));
                    clogVo.setLOG_TYPE(log_type);
                    clogVo.setROLE_ID(MyApplication.getUserVo().getROLE_ID());
                    clogVo.setUSER_ID(MyApplication.getUserVo().getUSER_ID());
                    logVoArrayList.add(clogVo);
                    CollectionDate();
                    logBaseAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        a_position=1;
        b_position=1;
        c_position=1;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        a_position=1;
        b_position=1;
        c_position=1;
    }

    private void conmmitLog(){
        OkGo.post(URL.LOG_COMMIT)
                .tag(this)
                .params("dayLogVos","{"+"\"data\":"+logVoArrayList.toString()+"}")
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Boolean isSuccess = jsonObject.getBoolean("success");
                            if(isSuccess){
                                logVoArrayList.clear();
                                logBaseAdapter.notifyDataSetChanged();
                                Toast.makeText(LogActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(LogActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    private void CollectionDate(){
        Collections.sort(logVoArrayList, new Comparator<LogVo>() {
            @Override
            public int compare(LogVo logOne, LogVo logTwo) {
                return logOne.getLOG_LEVEL().compareTo(logTwo.getLOG_LEVEL());
            }
        });
    }
 }

