package com.example.garbageclassification.Activitiy;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.garbageclassification.R;
import com.example.garbageclassification.model.Garbage;
import com.example.garbageclassification.util.GarbageAdapter;
import com.example.garbageclassification.util.GarbageService;
import com.example.garbageclassification.util.Util;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemSelectedListener{
    GarbageService service;
    GarbageAdapter adapter;
    SearchView searchView;
    ListView garbageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.searchView);
        service = new GarbageService();
        adapter = new GarbageAdapter(this,R.layout.item_garbage,service.readCSV(getAssets()),service);
        garbageList = findViewById(R.id.garbage_list);
        garbageList.setAdapter(adapter);
        garbageList.setOnItemClickListener(this);
        searchView.setOnQueryTextListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Garbage garbage = (Garbage) adapter.getItem(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(SearchActivity.this);
        dialog.setTitle("垃圾分类手册");
        dialog.setMessage(garbage.toString());
        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] array = getResources().getStringArray(R.array.garbageTypes);
        Garbage.GarbageType type = Garbage.GarbageType.others;
        switch (array[i]) {
            case "全部垃圾":
                adapter.setGarbages(Util.readCSV(getAssets()));
                adapter.getFilter().filter(searchView.getQuery());
                return;
            case "厨余垃圾":type = Garbage.GarbageType.kitchen;break;
            case "可回收垃圾":type = Garbage.GarbageType.recycle;break;
            case "有害垃圾":type = Garbage.GarbageType.harmful;break;
            case "其他垃圾":type = Garbage.GarbageType.others;break;
        }
        adapter.setGarbages(Util.classifyGarbage(Util.readCSV(getAssets()), type));
        adapter.getFilter().filter(searchView.getQuery());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (searchView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
            }
            searchView.clearFocus();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (TextUtils.isEmpty(s)) {
            adapter.getFilter().filter("");
        } else {
            adapter.getFilter().filter(s);
        }
        return false;
    }
}
