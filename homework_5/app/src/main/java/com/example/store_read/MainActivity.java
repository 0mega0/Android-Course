package com.example.store_read;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    final String MyFileName = "Store_Test";
    private ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = this.getContentResolver();
        SP = getSharedPreferences("cconfig",MODE_PRIVATE);
        editor = SP.edit();
        Button btn_write = findViewById(R.id.btn_write);
        Button btn_read = findViewById(R.id.btn_contract);
        Button btn_readContract = findViewById(R.id.btn_readContract);
        /*
        存入SharedPreferences的代码
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("Name","俞昊洋");
                editor.putString("Number","2018011353");
                editor.apply();
            }
        });
         */
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(MyFileName,MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content="姓名：俞昊洋\n 学号：2018011353";
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn_readContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
                while(cursor.moveToNext()){

                    String msg;
                    //id
                    String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    msg="id:"+id;

                    //name
                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    msg=msg+" name:"+name;
//phone
                    Cursor phoneNumbers=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"="+id,null,null);
                    while(phoneNumbers.moveToNext()){
                        String phoneNumber=phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        msg=msg+" phone:"+phoneNumber;
                    }

                    //email
                    Cursor emails=resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID +"="+id,null,null);
                    while(emails.moveToNext()){
                        String email=emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        msg=msg+" email:"+email;
                    }

                    Log.v("output contract:",msg);
                }
            }
        });

                }
}
