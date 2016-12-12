package com.stk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.stk.efficiencelog.R;
import com.stk.model.WorkLogVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangl on 2016/12/6.
 */

public class WorkAdapter extends BaseAdapter{

    private Context context;
    private List<List<WorkLogVo.WorkLog>> workVos;

    private List<WorkLogVo.WorkLog> workVoList;

    private List<WorkLogVo.WorkLog> workLogListOne = null;

    private List<WorkLogVo.WorkLog> workLogListTwo = null;


    public WorkAdapter(List<List<WorkLogVo.WorkLog>> workVos, Context context) {
        this.workVos = workVos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return workVos.size();
    }

    @Override
    public Object getItem(int position) {
        return workVos.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.work_adapter_layout,null);
            myViewHolder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            myViewHolder.log_time  = (TextView) convertView.findViewById(R.id.log_time);
            myViewHolder.log_type = (TextView) convertView.findViewById(R.id.log_type);
            myViewHolder.log_comment = (TextView) convertView.findViewById(R.id.log_comment);
            myViewHolder.logListView = (ListView) convertView.findViewById(R.id.workListView);
            convertView.setTag(myViewHolder);
        }
        myViewHolder = (MyViewHolder) convertView.getTag();
        workVoList =  workVos.get(position);
        myViewHolder.user_name.setText(workVoList.get(0).getNAME());
        myViewHolder.log_time.setText(workVoList.get(0).getLOG_TIME());
        myViewHolder.log_comment.setText("评论("+workVoList.get(0).getCOMMENT_AMOUNT()+")");
        logType(workVoList.get(0).getLOG_TYPE(),myViewHolder);
        collectionLog(workVoList);
        myViewHolder.logListView.setAdapter(new WorkItemAdapter(context,workVoList));

        return convertView;
    }


     private  class MyViewHolder{
        private TextView user_name;
        private TextView log_time;
        private ListView logListView;
        private TextView log_comment;
        private TextView log_type;
    }

    private void logType(String log_type,MyViewHolder myViewHolder){
        switch (log_type){
            case "01":
                myViewHolder.log_type.setText("日计划");
                break;
            case "02":
                myViewHolder.log_type.setText("周计划");
                break;
            case "03":
                myViewHolder.log_type.setText("月计划");
                break;
            case "04":
                myViewHolder.log_type.setText("年计划");
                break;
        }
    }

    private void collectionLog(List<WorkLogVo.WorkLog> logs){
        workLogListOne = new ArrayList<>();
        workLogListTwo = new ArrayList<>();
        Collections.sort(logs, new Comparator<WorkLogVo.WorkLog>() {
            @Override
            public int compare(WorkLogVo.WorkLog logOne, WorkLogVo.WorkLog logTwo) {
                if(Integer.valueOf(logOne.getLOG_FLAG())>Integer.valueOf(logTwo.getLOG_FLAG())){
                    return 1;
                }else if(Integer.valueOf(logOne.getLOG_FLAG())==Integer.valueOf(logTwo.getLOG_FLAG())){
                    return 0;
                }else{
                    return -1;
                }

            }
        });

         for(WorkLogVo.WorkLog workLog:logs){
            if(workLog.getLOG_FLAG().equals("0")){
                workLogListOne.add(workLog);
            }else{
                workLogListTwo.add(workLog);
            }
        }

        collectionLevel(workLogListOne);
        collectionLevel(workLogListTwo);
        logs.clear();
        for(WorkLogVo.WorkLog workLog:workLogListOne){
            logs.add(workLog);
        }
        for(WorkLogVo.WorkLog workLog:workLogListTwo){
            logs.add(workLog);
        }

    }


    private void collectionLevel(List<WorkLogVo.WorkLog> logs){
        Collections.sort(logs, new Comparator<WorkLogVo.WorkLog>() {
            @Override
            public int compare(WorkLogVo.WorkLog logOne, WorkLogVo.WorkLog logTwo) {
               return logOne.getLOG_LEVEL().compareTo(logTwo.getLOG_LEVEL());

            }
        });
    }
}
