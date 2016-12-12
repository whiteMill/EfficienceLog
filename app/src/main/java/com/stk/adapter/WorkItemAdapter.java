package com.stk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stk.efficiencelog.R;
import com.stk.model.WorkLogVo;

import java.util.List;

/**
 * Created by wangl on 2016/12/6.
 */

public class WorkItemAdapter extends BaseAdapter {
    private Context context;
    private List<WorkLogVo.WorkLog> workLogList;

    public WorkItemAdapter(Context context, List<WorkLogVo.WorkLog> workLogList) {
        this.context = context;
        this.workLogList = workLogList;
    }

    @Override
    public int getCount() {
        return workLogList.size();
    }

    @Override
    public Object getItem(int position) {
        return workLogList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if(convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.log_item_adapter,null);
            myViewHolder.complete = (TextView) convertView.findViewById(R.id.tv_firstCharacter);
            myViewHolder.logLevel = (TextView) convertView.findViewById(R.id.log_order);
            myViewHolder.logContent = (TextView) convertView.findViewById(R.id.log_content);
            myViewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            myViewHolder.logLevel.setVisibility(View.VISIBLE);
            myViewHolder.icon.setVisibility(View.GONE);
            convertView.setTag(myViewHolder);
        }
        myViewHolder = (MyViewHolder) convertView.getTag();
        if(position==0){
            myViewHolder.complete.setVisibility(View.VISIBLE);
            myViewHolder.complete.setText(workLogList.get(position).getLOG_FLAG().equals("1")?"未完成任务":"已完成任务");
            myViewHolder.logContent.setText(workLogList.get(position).getLOG_CONTENT());
            myViewHolder.logLevel.setText(workLogList.get(position).getLOG_LEVEL());
        }else{
            if(!workLogList.get(position).getLOG_FLAG().equals(workLogList.get(position-1).getLOG_FLAG())){
                myViewHolder.complete.setVisibility(View.VISIBLE);
                myViewHolder.complete.setText(workLogList.get(position).getLOG_FLAG().equals("1")?"未完成任务":"已完成任务");
                myViewHolder.logContent.setText(workLogList.get(position).getLOG_CONTENT());
                myViewHolder.logLevel.setText(workLogList.get(position).getLOG_LEVEL());
            }else{
                myViewHolder.complete.setVisibility(View.GONE);
                myViewHolder.logContent.setText(workLogList.get(position).getLOG_CONTENT());
                myViewHolder.logLevel.setText(workLogList.get(position).getLOG_LEVEL());
            }
        }

        return convertView;
    }

    private class MyViewHolder{
        private TextView complete;
        private TextView logLevel;
        private TextView logContent;
        private ImageView icon;

    }
}
