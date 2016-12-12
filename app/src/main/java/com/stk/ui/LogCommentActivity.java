package com.stk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.adapter.CommentAdapter;
import com.stk.adapter.WorkItemAdapter;
import com.stk.efficiencelog.R;
import com.stk.model.CommentVo;
import com.stk.model.WorkLogVo;
import com.stk.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LogCommentActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView name;
    private TextView time;
    private TextView logType;
    private ListView logListView;
    private ListView commentListView;
    private Intent oldIntent;
    private List<WorkLogVo.WorkLog> workLogList;
    private WorkLogVo.WorkLog workLog;
    private ImageView back;
    private TextView commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_comment);
        oldIntent = getIntent();
        workLogList = (List<WorkLogVo.WorkLog>) oldIntent.getSerializableExtra("list");
        workLog = workLogList.get(0);
        initView();
        QueryComment();
    }

    private void initView(){
        commentText = (TextView) findViewById(R.id.commentText);
        commentText.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        name = (TextView) findViewById(R.id.user_name);
        time = (TextView) findViewById(R.id.log_time);
        logType = (TextView) findViewById(R.id.log_type);
        logListView = (ListView) findViewById(R.id.logListView);
        commentListView = (ListView) findViewById(R.id.commentListView);
        logListView.setAdapter(new WorkItemAdapter(this,workLogList));
        name.setText(workLog.getNAME());
        time.setText(workLog.getLOG_TIME());
        setLogType(workLog.getLOG_TYPE());
    }

    private void setLogType(String log_type){
        switch (log_type){
            case "01":
                logType.setText("日计划");
                break;
            case "02":
                logType.setText("周计划");
                break;
            case "03":
                logType.setText("月计划");
                break;
            case "04":
                logType.setText("年计划");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        QueryComment();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.back:
              finish();
              break;
          case R.id.commentText:
              Intent intent = new Intent(this,UserCommentActivity.class);
              intent.putExtra("log_index",workLog.getLOG_INDEX());
              intent.putExtra("to_id",workLog.getUSER_ID());
              intent.putExtra("to_name",workLog.getNAME());
              startActivity(intent);
              break;
      }
    }

    private void QueryComment(){
        OkGo.post(URL.QUERY_COMMENT)
                .tag(this)
                .params("LOG_INDEX",workLog.getLOG_INDEX())
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("返回的数据",s);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            Boolean isSuccess = jsonObject.getBoolean("success");
                            if(isSuccess){
                                 List<CommentVo> commentVoList = new Gson().fromJson(jsonObject.getString("data"),new TypeToken<List<CommentVo>>() {
                                   }.getType());
                                commentListView.setAdapter(new CommentAdapter(LogCommentActivity.this,commentVoList));

                            }else{
                                Toast.makeText(LogCommentActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }


}
