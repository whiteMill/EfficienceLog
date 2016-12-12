package com.stk.efficiencelog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.stk.fragment.MessageFragment;
import com.stk.fragment.UserFragment;
import com.stk.fragment.WorkFragment;
import com.stk.model.UserVo;
import com.stk.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private MessageFragment messageFragment;
    private WorkFragment workFragment;
    private UserFragment userFragment;
    private static final int MESSAGE_CENTER = 0;
    private static final int WORK_CENTER = 1;
    private static final int USER_CENTER = 2;
    private RadioButton messageBtn;
    private RadioButton workBtn;
    private RadioButton userBtn;
    private int position = 1;
    private PushReceive pushReceive;
    public static final String PUSH_MESSAGE = "com.wl.action.PUSH_MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView();
        initUser();
        setTabSelection(MESSAGE_CENTER);
        initBroad();
    }

    private void initView(){
        messageBtn = (RadioButton) findViewById(R.id.MessageCenter);
        workBtn = (RadioButton) findViewById(R.id.WorkCenter);
        userBtn = (RadioButton) findViewById(R.id.UserCenter);

        messageBtn.setOnClickListener(this);
        workBtn.setOnClickListener(this);
        userBtn.setOnClickListener(this);
    }

    private void setTabSelection(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (position) {
            case MESSAGE_CENTER:
                change(MESSAGE_CENTER);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.changeFragment, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case WORK_CENTER:
                change(WORK_CENTER);
                // 是否添加过frgament
                if (workFragment == null) {
                    workFragment = new WorkFragment();
                    transaction.add(R.id.changeFragment, workFragment);
                } else {
                    transaction.show(workFragment);
                }
                break;
            case USER_CENTER:
                change(USER_CENTER);
                if (userFragment == null) {
                    userFragment = new UserFragment();
                    transaction.add(R.id.changeFragment, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }


    private void change(int index) {
        switch (index) {
            case 0:
                messageBtn.setChecked(true);
               // logBtn.setChecked(false);
                workBtn.setChecked(false);
                userBtn.setChecked(false);
                messageBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.message_pressed), null, null);
              //  logBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.log_normal), null, null);
                workBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.work_normal), null, null);
                userBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.user_normal), null, null);
                messageBtn.setTextColor(getResources().getColor(R.color.tabColor));
               // logBtn.setTextColor(getResources().getColor(R.color.tabTex));
                workBtn.setTextColor(getResources().getColor(R.color.tabTex));
                userBtn.setTextColor(getResources().getColor(R.color.tabTex));
                break;
          /*  case 1:
                messageBtn.setChecked(true);
               // logBtn.setChecked(false);
                workBtn.setChecked(false);
                userBtn.setChecked(false);
                messageBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.message_normal), null, null);
               // logBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.log_pressed), null, null);
                workBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.work_normal), null, null);
                userBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.user_normal), null, null);
                messageBtn.setTextColor(getResources().getColor(R.color.tabTex));
              //  logBtn.setTextColor(getResources().getColor(R.color.tabColor));
                workBtn.setTextColor(getResources().getColor(R.color.tabTex));
                userBtn.setTextColor(getResources().getColor(R.color.tabTex));
                break;*/
            case 1:
                messageBtn.setChecked(false);
               // logBtn.setChecked(false);
                workBtn.setChecked(true);
                userBtn.setChecked(false);
                messageBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.message_normal), null, null);
               // logBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.log_normal), null, null);
                workBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.work_pressed), null, null);
                userBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.user_normal), null, null);
                messageBtn.setTextColor(getResources().getColor(R.color.tabTex));
               // logBtn.setTextColor(getResources().getColor(R.color.tabTex));
                workBtn.setTextColor(getResources().getColor(R.color.tabColor));
                userBtn.setTextColor(getResources().getColor(R.color.tabTex));
                break;
            case 2:
                messageBtn.setChecked(false);
                //logBtn.setChecked(false);
                workBtn.setChecked(false);
                userBtn.setChecked(true);
                messageBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.message_normal), null, null);
               // logBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.log_normal), null, null);
                workBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.work_normal), null, null);
                userBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.user_pressed), null, null);
                messageBtn.setTextColor(getResources().getColor(R.color.tabTex));
               // logBtn.setTextColor(getResources().getColor(R.color.tabTex));
                workBtn.setTextColor(getResources().getColor(R.color.tabTex));
                userBtn.setTextColor(getResources().getColor(R.color.tabColor));
                break;
        }
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
       /* if (logFragment != null) {
            transaction.hide(logFragment);
        }*/
        if (workFragment != null) {
            transaction.hide(workFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MessageCenter:
                setTabSelection(MESSAGE_CENTER);
                break;
         /*   case R.id.LogCenter:
                setTabSelection(LOG_CENTER);
                break;*/
            case R.id.WorkCenter:
                setTabSelection(WORK_CENTER);
                break;
            case R.id.UserCenter:
                setTabSelection(USER_CENTER);
                break;
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // 记录当前的position
        outState.putInt("position", position);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void initBroad(){
        pushReceive  = new PushReceive();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(PUSH_MESSAGE);
        registerReceiver(pushReceive,intentFilter);
    }

    private class PushReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            messageFragment.pushMessage(intent.getStringExtra("message"));
        }
    }

    private void initUser(){
        UserVo userVo = new UserVo();
        userVo.setUSER_ID((String) SharedPreferencesUtils.getParam(getApplicationContext(),"USER_ID",""));
        userVo.setUSER_NAME((String) SharedPreferencesUtils.getParam(getApplicationContext(),"USER_NAME",""));
        userVo.setPASSWORD((String) SharedPreferencesUtils.getParam(getApplicationContext(),"PASSWORD",""));
        userVo.setNAME((String) SharedPreferencesUtils.getParam(getApplicationContext(),"NAME",""));
        userVo.setROLE_ID((String) SharedPreferencesUtils.getParam(getApplicationContext(),"ROLE_ID",""));
        userVo.setPHONE((String) SharedPreferencesUtils.getParam(getApplicationContext(),"PHONE",""));
        userVo.setSTATUS((String) SharedPreferencesUtils.getParam(getApplicationContext(),"STATUS",""));
        MyApplication.setUserVo(userVo);
    }


}
