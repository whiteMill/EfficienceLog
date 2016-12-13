package com.stk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stk.efficiencelog.R;
import com.stk.model.CommentVo;

import java.util.List;

/**
 * Created by wangl on 2016/12/10.
 */

public class RecentCommentAdapter extends BaseAdapter {

    private Context context;
    private List<CommentVo> commentVoList;

    public RecentCommentAdapter(List<CommentVo> commentVoList, Context context) {
        this.commentVoList = commentVoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentVoList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentVoList.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.recent_comment_adapter,null);
            myViewHolder.from_name = (TextView) convertView.findViewById(R.id.from_name);
            myViewHolder.comment_content = (TextView) convertView.findViewById(R.id.comment_content);
            myViewHolder.comment_time = (TextView) convertView.findViewById(R.id.comment_time);
            convertView.setTag(myViewHolder);
        }
        myViewHolder = (MyViewHolder) convertView.getTag();
        myViewHolder.from_name.setText(commentVoList.get(position).getFROM_NAME());
        myViewHolder.comment_content.setText(commentVoList.get(position).getCOMMENT_CONTENT());
        myViewHolder.comment_time.setText(commentVoList.get(position).getCOMMENT_TIME());
        return convertView;
    }

    private class MyViewHolder{
        private TextView from_name;
        private TextView comment_content;
        private TextView comment_time;
    }
}
