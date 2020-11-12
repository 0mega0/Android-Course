package com.example.garbageclassification.Activitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.garbageclassification.R;
import com.example.garbageclassification.util.GarbageAdapter;
import com.example.garbageclassification.util.GarbageService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView kitchen = findViewById(R.id.Image_kitchen);
        ImageView recycle = findViewById(R.id.Image_recycle);
        ImageView others = findViewById(R.id.Image_others);
        ImageView harmful = findViewById(R.id.Image_harmful);
        final SearchView search = findViewById(R.id.Search_Main);


        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,KitchenActivity.class);
                startActivity(intent);
            }
        });
        recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecycleActivity.class);
                startActivity(intent);
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,OthersActivity.class);
                startActivity(intent);
            }
        });
        harmful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HarmfulActivity.class);
                startActivity(intent);
            }
        });
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){//只有在获取焦点时跳转，不写这个的话只要焦点改变就跳转
                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                    startActivity(intent);
                }
                search.clearFocus();//取消焦点
            }
        });


    }
}
