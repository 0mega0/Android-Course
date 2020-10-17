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

public class VolumeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volume);

        Button volume_btn_back = findViewById(R.id.volume_back);
        final EditText volume_et_input = findViewById(R.id.volume_et_input);
        final TextView volume_tv_output = findViewById(R.id.volume_tv_output);
        volume_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(VolumeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        final Convert convert = new Convert();
        Spinner spinner_input = findViewById(R.id.volume_spinner_input);
        Spinner spinner_target = findViewById(R.id.volume_spinner_target);
        Button volume_btn_cal = findViewById(R.id.volume_cal);
        ArrayAdapter<String> input_adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.volume_spinner));
        spinner_input.setAdapter(input_adapter);
        spinner_target.setAdapter(input_adapter);
        spinner_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MyTest:volume_Input",String.valueOf(i));
                convert.setTypeInput(i);
                String strInput = volume_et_input.getText().toString();
                String result = String.valueOf(convert.volumeConvert(strInput));
                volume_tv_output.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_target.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MyTest:volume_Output",String.valueOf(i));
                convert.setTypeOutput(i);
                String strOutput = volume_et_input.getText().toString();
                String result = String.valueOf(convert.volumeConvert(strOutput));
                volume_tv_output.setText(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        volume_btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOutput = volume_et_input.getText().toString();
                String result = String.valueOf(convert.lengthConvert(strOutput));
                volume_tv_output.setText(result);
            }
        });
    }


}
