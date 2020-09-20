package com.example.activity_jump;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String t = "LifeCircle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(t,"onCreate");
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, SecActivity.class);
                intent.putExtra("name","Zhangsan");
                intent.putExtra("age",20);
                startActivityForResult(intent,0);
            }
        });
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode==0){
            String str=data.getStringExtra("result");
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(t,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(t,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(t,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(t,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(t,"onResume");
    }
}
