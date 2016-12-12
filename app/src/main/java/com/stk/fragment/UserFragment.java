package com.stk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stk.efficiencelog.MyApplication;
import com.stk.efficiencelog.R;
import com.stk.ui.EditPassActivity;
import com.stk.ui.EditPhoneActivity;

/**
 * Created by wangl on 2016/11/30.
 */

public class UserFragment extends Fragment implements View.OnClickListener{
    private View view;
    private RelativeLayout editPass;
    private RelativeLayout editPhone;
    private TextView user_name;
    private TextView user_phone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment, container, false);
        initView();
        return view;
    }

    private void initView(){
        editPass = (RelativeLayout) view.findViewById(R.id.editPass);
        editPhone = (RelativeLayout) view.findViewById(R.id.editPhone);
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_phone = (TextView) view.findViewById(R.id.user_phone);
        editPass.setOnClickListener(this);
        editPhone.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        user_name.setText(MyApplication.getUserVo().getNAME());
        user_phone.setText(MyApplication.getUserVo().getPHONE());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editPass:
                Intent editPassIn = new Intent(getActivity(), EditPassActivity.class);
                startActivity(editPassIn);
                break;
            case R.id.editPhone:
                Intent editPhoneIn = new Intent(getActivity(), EditPhoneActivity.class);
                startActivity(editPhoneIn);
                break;
        }
    }
}
