package com.example.musicplayer.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.R;

public class MainActivity extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public static final String ACTIVITY_ACTION = "com.example.musicplayer.activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView musicList = this.findViewById(R.id.list_view);
        ImageButton preButton = this.findViewById(R.id.img_prev);
        ImageButton stopButton = this.findViewById(R.id.img_play_pause);
        ImageButton nextButton = this.findViewById(R.id.img_next);
        ImageButton statusButton = this.findViewById(R.id.img_status);
        TextView timeTextView = this.findViewById(R.id.tv_time);
        SeekBar seekBar = this.findViewById(R.id.seek_bar);
        TextView NowPlaying = this.findViewById(R.id.current_music);
    }


}
