package com.stk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stk.efficiencelog.R;
import com.stk.model.LogVo;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/1.
 */

public class LogBaseAdapter extends BaseAdapter {
     private Context context;
     private ArrayList<LogVo> logVoArrayList;

     public LogBaseAdapter(Context context, ArrayList<LogVo> logVoArrayList) {
        this.context = context;
        this.logVoArrayList = logVoArrayList;
    }

    @Override
    public int getCount() {
        return logVoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return logVoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogViewHolder logViewHolder= null;
        if(convertView==null){
            logViewHolder = new LogViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.log_item_adapter,null);
            logViewHolder.content  = (TextView) convertView.findViewById(R.id.log_content);
            logViewHolder.tv_firstCharacter = (TextView) convertView.findViewById(R.id.tv_firstCharacter);
            logViewHolder.log_order = (TextView) convertView.findViewById(R.id.log_order);
            convertView.setTag(logViewHolder);
        }
        logViewHolder = (LogViewHolder) convertView.getTag();
        if(position==0){
            logViewHolder.tv_firstCharacter.setVisibility(View.VISIBLE);
            equalLevel(position,logViewHolder);
            logViewHolder.content.setText(logVoArrayList.get(position).getLOG_CONTENT());
        }else{
            if(logVoArrayList.get(position).getLOG_LEVEL().compareTo(logVoArrayList.get(position-1).getLOG_LEVEL())>0){
                logViewHolder.tv_firstCharacter.setVisibility(View.VISIBLE);
                equalLevel(position,logViewHolder);
                logViewHolder.content.setText(logVoArrayList.get(position).getLOG_CONTENT());
            }else{
                logViewHolder.tv_firstCharacter.setVisibility(View.GONE);
                logViewHolder.content.setText(logVoArrayList.get(position).getLOG_CONTENT());
            }

        }
        logViewHolder.log_order.setText(logVoArrayList.get(position).getLOG_ID());
        return convertView;
    }

    private class LogViewHolder{
        private TextView content;
        private TextView tv_firstCharacter;
        private TextView log_order;

    }

    private void equalLevel(int position,LogViewHolder logViewHolder){
        switch (logVoArrayList.get(position).getLOG_LEVEL()){
            case "A":
                logViewHolder.tv_firstCharacter.setText("A(最重要-自己做)");
                break;
            case "B":
                logViewHolder.tv_firstCharacter.setText("B(重要-压缩做)");
                break;
            case "C":
                logViewHolder.tv_firstCharacter.setText("C(次重要-授权别人做)");
                break;
        }

    }
}
