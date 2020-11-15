package com.example.vocabularybook.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.vocabularybook.R;
import com.example.vocabularybook.module.Item;
import com.example.vocabularybook.util.MyAdapter;
import com.example.vocabularybook.util.MyDBOpenHelper;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private ListView show;
    private SQLiteDatabase db;
    private MyAdapter myAdapter;
    private ArrayList<Item> itemList;

    public ArrayList<Item> searchNote() {
        String sql = "select * from note where note like '%" + editText.getText().toString() + "%'";
        ArrayList<Item> noteList = new ArrayList<Item>();
        Item note;
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            note = new Item();
            note.setContent(cursor.getString(cursor
                    .getColumnIndex("note")));
            note.setTime(cursor.getString(cursor
                    .getColumnIndex("time")));
            note.setId(cursor.getInt(cursor
                    .getColumnIndex("id")));
            noteList.add(note);
        }
        return noteList;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        MyDBOpenHelper mDBHelper = new MyDBOpenHelper(this, "my.db", null, 1);
        db = mDBHelper.getWritableDatabase();
        editText = this.findViewById(R.id.edit_search);
        show = this.findViewById(R.id.search_show);
        Button button = this.findViewById(R.id.btn_search_find);
        myAdapter = new MyAdapter<Item>(searchNote(), R.layout.list_item) {
            @Override
            public void bindView(ViewHolder holder, Item obj) {
                holder.setText(R.id.item_content, obj.getContent());
                holder.setText(R.id.item_time, obj.getTime());
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList = searchNote();
                myAdapter = new MyAdapter<Item>(searchNote(), R.layout.list_item) {
                    @Override
                    public void bindView(ViewHolder holder, Item obj) {
                        holder.setText(R.id.item_content, obj.getContent());
                        holder.setText(R.id.item_time, obj.getTime());
                    }
                };
                show.setAdapter(myAdapter);
            }
        });
        show.setAdapter(myAdapter);
    }
}
