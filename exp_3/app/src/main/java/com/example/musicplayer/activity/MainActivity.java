package com.example.musicplayer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicplayer.R;
import com.example.musicplayer.model.Music;
import com.example.musicplayer.utility.MusicAdapter;
import com.example.musicplayer.utility.MusicService;
import com.example.musicplayer.utility.Util;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public static final String ACTIVITY_ACTION = "com.example.musicplayer.activity";

    private List<Music> musics;
    private Music music;
    private int state = MusicService.PlayState.play.value;
    private int index;
    private int flag = 0;
    private boolean permission = false;
    private ImageButton btn_playPause;
    private ImageButton btn_status;
    private TextView tv_time;
    private SeekBar timebar;
    private TextView currentPlaying;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list_music = this.findViewById(R.id.list_view);
        ImageButton btn_last = this.findViewById(R.id.img_prev);
        btn_playPause = this.findViewById(R.id.img_play_pause);
        ImageButton btn_next = this.findViewById(R.id.img_next);
        btn_status = this.findViewById(R.id.img_status);
        tv_time = this.findViewById(R.id.tv_time);
        timebar = this.findViewById(R.id.seek_bar);
        currentPlaying = this.findViewById(R.id.current_music);

        Context context = MainActivity.this;

        permission = Util.checkPublishPermission(this);
        if (permission) {
            musics = Util.getMusicData(context);
        }

        MusicAdapter musicAdapter = new MusicAdapter(musics, this);
        list_music.setAdapter(musicAdapter);

        list_music.setOnItemClickListener(this);
        btn_last.setOnClickListener(this);
        btn_playPause.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_status.setOnClickListener(this);
        timebar.setOnSeekBarChangeListener(this);

        MyReceive receive = new MyReceive();
        IntentFilter filter = new IntentFilter(ACTIVITY_ACTION);
        registerReceiver(receive, filter);

        Intent intent = new Intent(context, MusicService.class);
        startService(intent);

        SharedPreferences preferences = getSharedPreferences("Date", 0);
        editor = preferences.edit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            for (int result: grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            permission = true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        music = musics.get(index);
        Intent intent = new Intent(MusicService.SERVICE_ACTION);
        intent.putExtra("music", music);
        intent.putExtra("newMusic", 1);
        sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        System.out.println("click");
        Intent intent = new Intent(MusicService.SERVICE_ACTION);
        switch (view.getId()) {
            case R.id.img_prev:
                if (index == 0) {
                    index = musics.size() - 1;
                } else {
                    index -= 1;
                }
                music = musics.get(index);
                intent.putExtra("newMusic", 1);
                intent.putExtra("music", music);
                break;
            case R.id.img_play_pause:
                System.out.println("click play");
                if (music == null) {
                    music = musics.get(index);
                    intent.putExtra("music", music);
                }
                intent.putExtra("isPlay", 1);
                break;
            case R.id.img_next:
                if (index == musics.size() - 1) {
                    index = 0;
                } else {
                    index += 1;
                }
                music = musics.get(index);
                intent.putExtra("newMusic", 1);
                intent.putExtra("music", music);
            case R.id.img_status:
                flag += 1;
                if (flag > 2) {
                    flag = 0;
                }
                if (flag == 0) {
                    btn_status.setImageResource(R.mipmap.order);
                } else if (flag == 1) {
                    btn_status.setImageResource(R.mipmap.single);
                } else {
                    btn_status.setImageResource(R.mipmap.random);
                }
                break;
        }
        sendBroadcast(intent);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Intent intent = new Intent((MusicService.SERVICE_ACTION));
        intent.putExtra("progress", timebar.getProgress());
        sendBroadcast(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //返回
        if (keyCode == KeyEvent.KEYCODE_BACK){
            editor.putInt("stateSave", state);
            editor.putInt("index", index);
            editor.commit();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,0,1,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        super.unregisterReceiver(receiver);
        unregisterReceiver(receiver);
    }

    public class MyReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            state = intent.getIntExtra("state", state);
            if (state == MusicService.PlayState.pause.value) {
                btn_playPause.setImageResource(R.mipmap.pause);
            } else {
                btn_playPause.setImageResource(R.mipmap.play);
            }

            String currentMusicName = intent.getStringExtra("musicName");
            if (currentMusicName != null) {
                currentPlaying.setText(currentMusicName);
            }

            int currentPosition = intent.getIntExtra("curPosition", -1);
            int time = intent.getIntExtra("time", -1);

            if (currentPosition != -1) {
                timebar.setProgress((int) ((currentPosition * 1.0) / time * 100));
                tv_time.setText(initTime(currentPosition, time));
            }

            boolean isOver = intent.getBooleanExtra("over", false);
            if (isOver) {
                Intent intentService = new Intent(MusicService.SERVICE_ACTION);
                if (flag == 0) {
                    if (index == musics.size() - 1) {
                        index = 0;
                    } else {
                        index += 1;
                    }
                } else if (flag == 2) {
                    index = (int) (Math.random() * musics.size());
                }
                playMusic(intentService);

                editor.putInt("index", index);
                editor.commit();
            }
        }
    }

    private void playMusic(Intent intent) {
        music = musics.get(index);
        intent.putExtra("newMusic", 1);
        intent.putExtra("music", music);
        sendBroadcast(intent);
    }

    private String initTime(int currentPosition, int time) {
        int curMinute = currentPosition / 1000 / 60;//分
        int curSecond = currentPosition / 1000 % 60;//秒
        int durMinute = time / 1000 / 60;//分
        int durSecond = time / 1000 % 60;//秒
        return getTime(curMinute) + ":" + getTime(curSecond) + "/" + getTime(durMinute) + ":" + getTime(durSecond);
    }

    private String getTime(int time) {
        return time < 10 ? "0" + time : time + "";
    }
}