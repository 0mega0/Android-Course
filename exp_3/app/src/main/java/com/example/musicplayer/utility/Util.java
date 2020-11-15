package com.example.musicplayer.utility;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;


import com.example.musicplayer.activity.MainActivity;
import com.example.musicplayer.model.Music;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Music> getMusicData(Context context) {
        List<Music> musics = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        while (cursor != null && cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            String author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            long time = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));

            if (author.equals("<unknown")) {
                author = "未知艺术家";
            }

            if (time > 20000) {
                Music music = new Music(name, author, path, time);
                musics.add(music);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return musics;
    }

    public static boolean checkPublishPermission(Activity activity) {
        List<String> permissions = new ArrayList<>();
        if (PackageManager.PERMISSION_GRANTED !=
                ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (permissions.size() != 0) {
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[0]),
                    MainActivity.PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }
}
