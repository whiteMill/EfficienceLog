package com.stk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.stk.efficiencelog.R;
import com.stk.model.LogVo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2016/12/8.
 */

public class LogUpdateAdapter extends BaseAdapter {
    private Context context;
    private List<LogVo> logVoArrayList;

    private  HashMap<Integer,Boolean> isSelected;

    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    public LogUpdateAdapter(Context context, List<LogVo> logVoArrayList) {
        this.context = context;
        this.logVoArrayList = logVoArrayList;
        isSelected = new HashMap<Integer, Boolean>();
        initCheckBox();
    }


    private void initCheckBox(){
        for (int i = 0; i < logVoArrayList.size(); i++) {
            if(logVoArrayList.get(i).getLOG_FLAG().equals("0")){
                isSelected.put(i,true);
            }else{
                isSelected.put(i,false);
            }
        }
    }

    @Override
    public int getCount() {
        Log.d("list大小",logVoArrayList.size()+"");
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LogViewHolder logViewHolder = null;
        if(convertView==null){
            logViewHolder = new LogViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.log_item_adapter,null);
            logViewHolder.content  = (TextView) convertView.findViewById(R.id.log_content);
            logViewHolder.tv_firstCharacter = (TextView) convertView.findViewById(R.id.tv_firstCharacter);
            logViewHolder.log_order = (TextView) convertView.findViewById(R.id.log_order);
            logViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.isComplete);
            logViewHolder.checkBox.setVisibility(View.VISIBLE);
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
            Log.d("iueiruieurie",isSelected.get(position)+""+"<<<>>>"+position);
            logViewHolder.checkBox.setChecked(isSelected.get(position));
            logViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                     Log.d("dasdagggggggs",isChecked+"<><><>"+position);
                }
             });
            logViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View v) {
                 if(((CheckBox)v).isChecked()){
                    isSelected.put(position,true);
                }else{
                    isSelected.put(position,false);
                }
            }
        });
         return convertView;
    }

    private class LogViewHolder{
        private TextView content;
        private TextView tv_firstCharacter;
        private TextView log_order;
        private CheckBox checkBox;

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
