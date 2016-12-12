package com.stk.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stk.efficiencelog.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogWriteActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sureLog;
    private EditText log_item;
    private String itemContent;
    private ImageView back;
    private int logClass;
    private Intent intent;
    private final int A_RESULT_CODE = 1001;
    private final int B_RESULT_CODE = 1002;
    private final int C_RESULT_CODE = 1003;
    private LinearLayout timeLayout;
    private TextView time_begin;
    private TextView time_end;
    private int year, month, day;
    private SimpleDateFormat sdf;
    private String log_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_write);
        intent =  getIntent();
        logClass =  intent.getIntExtra("logLevel",1001);
        log_type = intent.getStringExtra("log_type");
        initView();
        getCurrentTime();
    }

    private void initView(){
        sureLog = (Button) findViewById(R.id.sure_log);
        log_item = (EditText) findViewById(R.id.log_item);
        back = (ImageView) findViewById(R.id.back);
        timeLayout = (LinearLayout) findViewById(R.id.time_layout);
        if(log_type.equals("01")){
            timeLayout.setVisibility(View.GONE);
        }
        time_begin = (TextView) findViewById(R.id.time_begin);
        time_end = (TextView) findViewById(R.id.time_end);
        time_begin.setOnClickListener(this);
        time_end.setOnClickListener(this);
        back.setOnClickListener(this);
        sureLog.setOnClickListener(this);
    }

    private void getCurrentTime() {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH) + 1;
        day = now.get(Calendar.DAY_OF_MONTH);
        now.get(Calendar.MINUTE);
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        time_begin.setText(sdf.format(new Date()));
        time_end.setText(sdf.format(new Date()));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_log:
                switch (logClass){
                    case A_RESULT_CODE:
                        itemContent = log_item.getText().toString().trim();
                        Intent aIntent=new Intent();
                        aIntent.putExtra("Log_content", itemContent);
                        aIntent.putExtra("begin_time",time_begin.getText().toString());
                        aIntent.putExtra("end_time",time_end.getText().toString());
                        setResult(A_RESULT_CODE, aIntent);
                        finish();
                        break;
                    case B_RESULT_CODE:
                        itemContent = log_item.getText().toString().trim();
                        Intent bIntent=new Intent();
                        bIntent.putExtra("Log_content", itemContent);
                        bIntent.putExtra("begin_time",time_begin.getText().toString());
                        bIntent.putExtra("end_time",time_end.getText().toString());
                        setResult(B_RESULT_CODE, bIntent);
                        finish();
                        break;
                    case C_RESULT_CODE:
                        itemContent = log_item.getText().toString().trim();
                        Intent cIntent=new Intent();
                        cIntent.putExtra("Log_content", itemContent);
                        cIntent.putExtra("begin_time",time_begin.getText().toString());
                        cIntent.putExtra("end_time",time_end.getText().toString());
                        setResult(C_RESULT_CODE, cIntent);
                        finish();
                        break;
                }


            case R.id.back:
                finish();
                break;

            case R.id.time_begin:
                 new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time_begin.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month - 1, day).show();
                break;
            case R.id.time_end:
                 new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time_end.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month - 1, day).show();
                break;
        }

    }
}
