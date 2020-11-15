package com.example.vocabularybook.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vocabularybook.R;
import com.example.vocabularybook.fragment.ComposeFragment;
import com.example.vocabularybook.fragment.NoteListFragment;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isLand()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.first_content, new NoteListFragment())
                    .replace(R.id.second_content, new ComposeFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.first_content, new NoteListFragment())
                    .commit();
        }

    }
    public boolean isLand() {
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
