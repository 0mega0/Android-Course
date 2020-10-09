package com.example.servieceandbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyService myService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ServiceConnection serviceConnection = new ServiceConnection() {
            private static final String TAG = "service353";
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v(TAG, "onServiceConnected");
                myService = ((MyService.LocalBinder) iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.v(TAG, "onServiceDisconnected");
            }
        };
        Button btnStart =  findViewById(R.id.Start);
        Button btnStop = findViewById(R.id.Stop);
        Button btnUse1 = findViewById(R.id.Use1);
        Button btnUse2 = findViewById(R.id.Use2);
        Button btnUse3 = findViewById(R.id.Use3);
        Button btnUse4 = findViewById(R.id.Use4);
        final EditText et_input = findViewById(R.id.et_input);
        Button btn_isPrime = findViewById(R.id.btn_isPrime);
        final TextView tv_isPrime = findViewById(R.id.tv_isPrime);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(serviceConnection);
            }
        });
        btnUse1.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "service353";
            @Override
            public void onClick(View view) {
                if (myService != null)
                    Log.v(TAG, "Using Service:22+11=" + myService.add(22, 11));
            }
        });
        btnUse2.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "service353";
            @Override
            public void onClick(View view) {
                if (myService != null)
                    Log.v(TAG, "Using Service:9764-234=" + myService.sub(9764, 234));
            }
        });
        btnUse3.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "service353";
            @Override
            public void onClick(View view) {
                if (myService != null)
                    Log.v(TAG, "Using Service:8*2=" + myService.mul(8, 2));
            }
        });
        btnUse4.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "service353";
            @Override
            public void onClick(View view) {
                if (myService != null)
                    Log.v(TAG, "Using Service:752/2=" + myService.div(752, 2));
            }
        });

        btn_isPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myService != null){
                    boolean temp = myService.isPrime(Integer.parseInt(et_input.getText().toString()));
                    String result = String.valueOf(temp);
                    tv_isPrime.setText(result);
                }
            }
        });
    }


}