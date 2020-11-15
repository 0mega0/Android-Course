package com.example.musicplayer.utility;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.musicplayer.R;
import com.example.musicplayer.activity.MainActivity;
import com.example.musicplayer.model.MyMusic;

import java.io.IOException;


public class MusicService extends Service {
    public static final String SERVICE_ACTION = "com.example.musicplayer.Service";

    private int newMusic;
    private MyMusic myMusic;
    private MediaPlayer player = new MediaPlayer();
    private PlayState state = PlayState.play;
    private int curPosition;
    private int time;
    private Notification.Builder builder;

    public enum PlayState {
        play(0), pause(1), playAgain(2);

        public final int value;

        PlayState(int value) {
            this.value = value;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("create");
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(SERVICE_ACTION);
        registerReceiver(receiver, filter);

        builder = new Notification.Builder(this);
        builder.setTicker("音乐播放器");
        builder.setSmallIcon(R.mipmap.music);
        builder.setContentTitle("音乐播放器");
        builder.setContentInfo("音乐播放器");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();

        startForeground(0, notification);
        manager.notify(0, notification);


        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(MainActivity.ACTIVITY_ACTION);
                intent.putExtra("over", true);
                sendBroadcast(intent);
                curPosition = 0;
                time = 0;
            }
        });
    }
    public void playMusic(final MyMusic myMusic) {
        if (player == null) {
            return;
        }
        player.stop();
        player.reset();
        builder.setContentText(myMusic.getName() + "-正在播放");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = builder.build();
        startForeground(0, notification);
        if (manager != null) {
            manager.notify(0, notification);
        }

        Intent intent = new Intent(MainActivity.ACTIVITY_ACTION);
        intent.putExtra("musicName", myMusic.getName());
        sendBroadcast(intent);

        try {
            player.setDataSource(myMusic.getPath());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.start();
        time = player.getDuration();

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (curPosition < time) {
                    try {
                        sleep(1000);
                        curPosition = player.getCurrentPosition();
                        Intent intent =  new Intent(MainActivity.ACTIVITY_ACTION);
                        intent.putExtra("curPosition", curPosition);
                        intent.putExtra("time", time);
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }




    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("receive");
            newMusic = intent.getIntExtra("newMusic", -1);
            if (newMusic != -1) {
                myMusic = (MyMusic) intent.getSerializableExtra("music");
                if (myMusic != null) {
                    playMusic(myMusic);
                    state = PlayState.pause;
                }
            }

            int isPlay = intent.getIntExtra("isPlay", -1);
            if (isPlay != -1) {
                switch (state) {
                    case play:
                        myMusic = (MyMusic) intent.getSerializableExtra("music");
                        playMusic(myMusic);
                        state = PlayState.pause;
                        break;
                    case pause:
                        player.pause();
                        state = PlayState.playAgain;
                        break;
                    case playAgain:
                        player.start();
                        state = PlayState.pause;
                        break;
                }
            }

            int progress = intent.getIntExtra("progress", -1);
            if (progress != -1) {
                curPosition = (int) (((progress * 1.0) / 100) * time);
                player.seekTo(curPosition);
            }

            Intent intentActivity = new Intent(MainActivity.ACTIVITY_ACTION);
            intentActivity.putExtra("state", state);
            sendBroadcast(intentActivity);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        super.unregisterReceiver(receiver);
        unregisterReceiver(receiver);
    }
}
