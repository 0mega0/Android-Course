package com.example.calculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_main = findViewById(R.id.tv_main);
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
        Button btn_negative = findViewById(R.id.btn_negative);
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

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_dot.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_equals.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_negative.setOnClickListener(this);
        btn_percent.setOnClickListener(this);
        btn_root.setOnClickListener(this);
        btn_pow.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_help.setOnClickListener(this);


        if(isLand()){
            btn_bracket_left.setOnClickListener(this);
            btn_bracket_right.setOnClickListener(this);
            btn_sin.setOnClickListener(this);
            btn_cos.setOnClickListener(this);
            btn_tan.setOnClickListener(this);
            btn_length.setOnClickListener(this);
            btn_volume.setOnClickListener(this);
            btn_base.setOnClickListener(this);
            btn_date.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v){

        String pastStr = tv_main.getText().toString();
        if(pastStr.equals("0"))
            tv_main.setText("");
        String currentStr = tv_main.getText().toString();
        switch(v.getId()){
            //---------------------------Number Buttons-------------------------------------------//
            case R.id.btn_0:{
                currentStr+="0";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_1:{
                currentStr+="1";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_2:{
                currentStr+="2";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_3:{
                currentStr+="3";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_4:{
                currentStr+="4";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_5:{
                currentStr+="5";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_6:{
                currentStr+="6";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_7:{
                currentStr+="7";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_8:{
                currentStr+="8";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_9:{
                currentStr+="9";
                tv_main.setText(currentStr);
            }break;
            //---------------------------Character Buttons----------------------------------------//
            case R.id.btn_dot:{
                currentStr+=".";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_divide:{
                currentStr+="/";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_multiply:{
                currentStr+="*";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_add:{
                currentStr+="+";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_minus:{
                currentStr+="-";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_equals:{
                String result = Calculate.cal(currentStr)+"\n";
                tv_main.setText(result);
            }break;
            case R.id.btn_del:{
                if (TextUtils.isEmpty(tv_main.getText())) {
                    return;
                }
                tv_main.setText(currentStr.substring(0, currentStr.length() - 1).length() > 0 ? currentStr.substring(0, currentStr.length() - 1) : "0");
            }break;
            case R.id.btn_negative:{
                currentStr = "-" + currentStr;
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_percent:{
                currentStr+="%";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_bracket_left:{
                currentStr+="(";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_bracket_right:{
                currentStr+=")";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_root:{
                currentStr+="sqrt(";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_pow:{
                currentStr+="^";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_clear:{
                currentStr="";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_sin:{
                currentStr+="sin(";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_cos:{
                currentStr+="cos(";
                tv_main.setText(currentStr);
            }break;
            case R.id.btn_tan:{
                currentStr+="tan(";
                tv_main.setText(currentStr);
            }break;

            //---------------------------Activity Change Buttons----------------------------------//
            case R.id.btn_help:{
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,HelpActivity.class);
                startActivity(intent);
            }break;
            case R.id.btn_length:{
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,LengthActivity.class);
                startActivity(intent);
            }break;
            case R.id.btn_volume:{
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,VolumeActivity.class);
                startActivity(intent);
            }break;
            case R.id.btn_base:{
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,BaseActivity.class);
                startActivity(intent);
            }break;
            case R.id.btn_date:{
                Intent intent =new Intent();
                intent.setClass(MainActivity.this,DateActivity.class);
                startActivity(intent);
            }break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }




    private boolean isLand() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            return true;
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            return false;
        }
        return false;
    }
}
