package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LengthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        Button length_btn_back = findViewById(R.id.length_back);
        final EditText length_et_input = findViewById(R.id.length_et_input);
        final TextView length_tv_output = findViewById(R.id.length_tv_output);

        length_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LengthActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        final Convert convert = new Convert();
        Spinner spinner_input = findViewById(R.id.length_spinner_input);
        Spinner spinner_target = findViewById(R.id.length_spinner_target);
        Button length_btn_cal = findViewById(R.id.length_cal);
        ArrayAdapter<String> input_adpeter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.length_spinner));
        spinner_input.setAdapter(input_adpeter);
        spinner_target.setAdapter(input_adpeter);
        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MyTest:Length_Input",String.valueOf(i));
                convert.setTypeInput(i);
                String strInput = length_et_input.getText().toString();
                String result = String.valueOf(convert.lengthConvert(strInput));
                length_tv_output.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_target.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MyTest:Length_Output",String.valueOf(i));
                convert.setTypeOutput(i);
                String strOutput = length_et_input.getText().toString();
                String result = String.valueOf(convert.lengthConvert(strOutput));
                length_tv_output.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        length_btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOutput = length_et_input.getText().toString();
                String result = String.valueOf(convert.lengthConvert(strOutput));
                length_tv_output.setText(result);
            }
        });

    }
}
