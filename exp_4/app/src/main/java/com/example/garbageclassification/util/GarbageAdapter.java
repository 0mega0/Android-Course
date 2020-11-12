package com.example.garbageclassification.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.garbageclassification.R;
import com.example.garbageclassification.model.Garbage;

import java.util.ArrayList;

public class GarbageAdapter extends BaseAdapter implements Filterable {
    private int resourceId;
    private Context context;
    private ArrayList<Garbage> garbages;
    private ArrayList<Garbage> copyOfGarbages;
    private GarbageService service;
    private final static String TAG = "GarbageAdapter";
    private Filter filter;

    public GarbageAdapter(@NonNull Context context, int resource, ArrayList<Garbage> garbages, GarbageService service) {
        this.resourceId = resource;
        this.context = context;
        this.garbages = garbages;
        this.service = service;
        this.copyOfGarbages = garbages;
    }

    public ArrayList<Garbage> getGarbages() {
        return garbages;
    }

    public void setGarbages(ArrayList<Garbage> garbages) {
        this.garbages = garbages;
        this.copyOfGarbages = garbages;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return garbages == null ? 0 : garbages.size();
    }

    @Override
    public Object getItem(int i) {
        return garbages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        TextView GarbageItem;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Garbage garbage = (Garbage) getItem(i);
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.GarbageItem = view.findViewById(R.id.garbage_item);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (garbage != null) {
            viewHolder.GarbageItem.setText(garbage.getName());
            switch (garbage.getType()) {
                case kitchen:
                    viewHolder.GarbageItem.setTextColor(ContextCompat.getColor(context, R.color.colorKitchen));
                    break;
                case recycle:
                    viewHolder.GarbageItem.setTextColor(ContextCompat.getColor(context, R.color.colorRecycle));
                    break;
                case harmful:
                    viewHolder.GarbageItem.setTextColor(ContextCompat.getColor(context, R.color.colorHarmful));
                    break;
                case others:
                    viewHolder.GarbageItem.setTextColor(ContextCompat.getColor(context, R.color.colorOthers));
                    break;
            }
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (null == filter) {
            filter = new MyFilter();
        }
        return filter;
    }

    class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            ArrayList<Garbage> newValues;
            String filterString = constraint.toString().trim().toLowerCase();
            Log.d(TAG, "performFiltering: FilterString: " + filterString);
            if (TextUtils.isEmpty(filterString)) {
                Log.d(TAG, "performFiltering: Empty Filter!");
                newValues = copyOfGarbages;
            } else {
                newValues = service.search(garbages, filterString);
            }
            Log.d(TAG, "performFiltering: result: " + newValues);
            results.values = newValues;
            results.count = newValues.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            garbages = (ArrayList<Garbage>) filterResults.values;

            if (filterResults.count > 0) {
                Log.d(TAG, "publishResults: Data Set Changed");
                notifyDataSetChanged();
            } else {
                Log.d(TAG, "publishResults: Data Set Invalidated");
                notifyDataSetInvalidated();
            }
        }


    }
}
