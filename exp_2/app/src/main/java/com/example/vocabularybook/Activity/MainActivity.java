package com.example.vocabularybook.activity;

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
        int h = getWindowManager().getDefaultDisplay().getHeight();
        int w = getWindowManager().getDefaultDisplay().getWidth();
        if (h < w) {
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
}
