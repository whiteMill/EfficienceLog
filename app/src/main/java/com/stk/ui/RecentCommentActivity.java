package com.stk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.adapter.RecentCommentAdapter;
import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.model.CommentVo;
import com.stk.model.WorkLogVo;
import com.stk.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class RecentCommentActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView back;
    private ListView commentList;
    private List<CommentVo> commentVoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_comment);
        initView();
        QueryLog();
        //intent = getIntent();
     /*   commentVoList = (List<CommentVo>)intent.getSerializableExtra("comment_list");
        commentList.setAdapter(new RecentCommentAdapter(commentVoList,this));*/
        commentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("zxczxczxczxczxczx",commentVoList.get(position)+"");
                recentComment(commentVoList.get(position).getLOG_INDEX());
            }
        });
    }

    private void initView() {
        commentList= (ListView) findViewById(R.id.commentList);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    private void recentComment(String logIndex){
        OkGo.post(URL.QUERY_LOG_INDEX)
                .tag(this)
                .params("USER_ID", MyApplication.getUserVo().getUSER_ID())
                .params("LOG_INDEX", logIndex)
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("FJSH",s);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                              List<WorkLogVo.WorkLog>  logVoList = new Gson().fromJson(jsonObject.getString("data"),new TypeToken<List<WorkLogVo.WorkLog>>(){}.getType());
                                Intent intent = new Intent(RecentCommentActivity.this,LogCommentActivity.class);
                                intent.putExtra("list", (Serializable) logVoList);
                                startActivity(intent);

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

    private void QueryLog(){
        OkGo.post(URL.RECENT_COMMENT)
                .tag(this)
                .params("USER_ID", MyApplication.getUserVo().getUSER_ID())
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("FJSH",s);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                                commentVoList = new Gson().fromJson(jsonObject.getString("data"),new TypeToken<List<CommentVo>>(){}.getType());
                                Log.d("FJSH",commentVoList.size()+"--");
                                commentList.setAdapter(new RecentCommentAdapter(commentVoList,RecentCommentActivity.this));
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



    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.back:
                 finish();
                 break;
         }
    }
}
