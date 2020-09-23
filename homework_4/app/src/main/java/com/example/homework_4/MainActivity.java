package com.example.homework_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_Call);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"缺少打电话权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                String number = "13801380138";
                String encodedPhoneNumber = null;
                try {
                    encodedPhoneNumber = URLEncoder.encode(number, "UTF-8");
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + encodedPhoneNumber)));
            }
        });
    }
}
