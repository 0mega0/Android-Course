package com.example.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_main = findViewById(R.id.tv_main);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_9 = findViewById(R.id.btn_9);
        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_dot = findViewById(R.id.btn_dot);
        Button btn_divide = findViewById(R.id.btn_divide);
        Button btn_multiply = findViewById(R.id.btn_multiply);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_minus = findViewById(R.id.btn_minus);
        Button btn_equals= findViewById(R.id.btn_equals);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_negitive = findViewById(R.id.btn_negative);
        Button btn_percent= findViewById(R.id.btn_percent);
        Button btn_bracket_left = findViewById(R.id.btn_bracket_left);
        Button btn_bracket_right = findViewById(R.id.btn_bracket_right);
        Button btn_root = findViewById(R.id.btn_root);
        Button btn_pow = findViewById(R.id.btn_pow);
        Button btn_clear = findViewById(R.id.btn_clear);
        Button btn_sin = findViewById(R.id.btn_sin);
        Button btn_cos = findViewById(R.id.btn_cos);
        Button btn_tan = findViewById(R.id.btn_tan);
        Button btn_help = findViewById(R.id.btn_help);
        Button btn_length = findViewById(R.id.btn_length);
        Button btn_volume = findViewById(R.id.btn_volume);
        Button btn_base = findViewById(R.id.btn_base);
        Button btn_date = findViewById(R.id.btn_date);

        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,HelpActivity.class);
                startActivity(intent);
            }
        });
        if(isLand()){
            btn_length.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent();
                    intent.setClass(MainActivity.this,LengthActivity.class);
                    startActivity(intent);
                }
            });
        }

    }




    private boolean isLand() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            return true;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            return false;
        }
        return false;
    }
}
