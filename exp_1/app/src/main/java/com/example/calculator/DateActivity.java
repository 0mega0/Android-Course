package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_date);

        final EditText date_et_start = findViewById(R.id.date_et_start);
        final EditText date_et_end = findViewById(R.id.date_et_end);
        final TextView date_tv_output = findViewById(R.id.date_tv_output);
        Button date_btn_cal = findViewById(R.id.date_cal);
        Button date_btn_back = findViewById(R.id.date_back);
        date_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(DateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final Convert convert = new Convert();
        date_btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = date_et_start.getText().toString();
                String end = date_et_end.getText().toString();
                String output = convert.dateCalculate(start,end) + "天";
                date_tv_output.setText(output);
            }
        });

    }
}
