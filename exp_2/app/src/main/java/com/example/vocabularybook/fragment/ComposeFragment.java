package com.example.vocabularybook.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vocabularybook.R;
import com.example.vocabularybook.util.MyDBOpenHelper;

import java.util.Calendar;

public class ComposeFragment extends Fragment {

    private Button btn_back, btn_check;
    private EditText edit_text;
    private MyDBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private String id = "", method = "", content = "";
    private static final String TAG = "ComposeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compose, container, false);
        dbOpenHelper = new MyDBOpenHelper(view.getContext(), "my.db", null, 1);
        db = dbOpenHelper.getWritableDatabase();
        btn_back = view.findViewById(R.id.btn_back);
        btn_check = view.findViewById(R.id.btn_check);
        edit_text = view.findViewById(R.id.edit_compose);
        edit_text.setText(content);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                int w = getActivity().getWindowManager().getDefaultDisplay().getWidth();
                if (h < w) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.second_content, new ComposeFragment(), null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.first_content, new NoteListFragment(), null)
                            .commit();
                }
                Log.d(TAG, "onClick: back");
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (method.equals("insert") || method.equals("")) {
                    Calendar calendar = Calendar.getInstance();
                    String date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE);
                    db.execSQL("INSERT INTO note(note,time) values(?,?)",
                            new String[]{edit_text.getText().toString(), date});
                    Log.d(TAG, "onClick: insert");
                } else if (method.equals("update")) {
                    Calendar calendar = Calendar.getInstance();
                    String date = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE);
                    db.execSQL("UPDATE note SET note = ?,time = ? WHERE id = ?",
                            new String[]{edit_text.getText().toString(), date, id});
                    Log.d(TAG, "onClick: update");
                }
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.first_content, new NoteListFragment(), null)
                        .commit();
            }
        });
        return view;
    }

    public void SetMethod(String id, String method, String content) {
        this.id = id;
        this.method = method;
        this.content = content;
    }
}
