package com.stk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.model.CommentVo;
import com.stk.model.PushVo;
import com.stk.ui.RecentCommentActivity;
import com.stk.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wangl on 2016/11/30.
 */

public class MessageFragment extends Fragment implements View.OnClickListener{

    private View  view;
    private TextView messTextView;
    private TextView mess_amount;
    private Button button;
    private TextView push_time;
    private static  int amount = 1;
    private LinearLayout comLayout;
    private List<CommentVo> commentVoList;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_fragment, container, false);
        initView();
        recentComment();
        return view;
    }

    private void initView() {
        comLayout = (LinearLayout) view.findViewById(R.id.comment_layout);
        mess_amount = (TextView) view.findViewById(R.id.mess_amount);
        messTextView = (TextView) view.findViewById(R.id.call_mess);
        button = (Button) view.findViewById(R.id.button);
        push_time = (TextView) view.findViewById(R.id.push_time);
        comLayout.setOnClickListener(this);
        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    push();
            }
        });
    }

    private void push(){
        OkGo.post(URL.PUSH)
                .tag(this)
                .params("FROM_ID", MyApplication.getUserVo().getUSER_ID())
                .params("TO_ID", MyApplication.getUserVo().getUSER_ID())
                .params("FROM_NAME","王磊")
                .params("TO_NAME","王磊" )
                .params("MESS_CONTENT","123hhhhhhhhhh")
                .params("TIME", "2016-12-12 08:05")
                .params("LOG_INDEX","dasdasdasd")
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

     public  void pushMessage(String message){
         PushVo pushVo  =  new Gson().fromJson(message, PushVo.class);
         messTextView.setText(pushVo.getFROM_NAME()+"回复我:"+pushVo.getMESS_CONTENT());
         push_time.setText(pushVo.getTIME());
         mess_amount.setVisibility(View.VISIBLE);
         mess_amount.setText(amount+++"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_layout:
                Intent intent = new Intent(getActivity(), RecentCommentActivity.class);
                //intent.putExtra("comment_list",(Serializable)commentVoList);
                amount = 1;
                mess_amount.setVisibility(View.INVISIBLE);
                startActivity(intent);
                break;
        }
    }

    private void recentComment(){
        OkGo.post(URL.RECENT_COMMENT)
                .tag(this)
                .params("USER_ID", MyApplication.getUserVo().getUSER_ID())
                .cacheKey("catchKey")
                .cacheMode(CacheMode.DEFAULT)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(s);
                            if(jsonObject.getBoolean("success")){
                                commentVoList = new Gson().fromJson(jsonObject.getString("data"),new TypeToken<List<CommentVo>>(){}.getType());
                                if(commentVoList.size()!=0){
                                   messTextView.setVisibility(View.VISIBLE);
                                    push_time.setVisibility(View.VISIBLE);
                                    CommentVo commentVo =  commentVoList.get(0);
                                    messTextView.setText(commentVo.getFROM_NAME()+"回复我:"+commentVo.getCOMMENT_CONTENT());
                                    push_time.setText(commentVo.getCOMMENT_TIME());
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


}
