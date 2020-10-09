package com.example.servieceandbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG="service353";

    private LocalBinder myBinder =new LocalBinder();
    public class LocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
    public int add(int x,int y){
        return x+y;
    }
    public int sub(int x,int y){
        return x-y;
    }
    public int mul(int x,int y){
        return x*y;
    }
    public int div(int x,int y){
        return x/y;
    }
    public boolean isPrime(int num){
        int a = 0;
        for(int i=2;i<num;i++){
            if(num%i==0){
                a++;  // 素数个数加1
            }
        }
        if(a==0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind()");
        return myBinder;
    }
    @Override
    public boolean onUnbind(Intent intent){
        Log.v(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    public MyService(){
    }
    @Override
    public void onCreate(){
        Log.v(TAG,"onCreate");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.v(TAG,"onStartCommand()");
        int n=intent.getIntExtra("num",0);
        Log.v(TAG,"int is:"+n);
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        Log.v(TAG,"onDestroy()");
        super.onDestroy();
    }
}
