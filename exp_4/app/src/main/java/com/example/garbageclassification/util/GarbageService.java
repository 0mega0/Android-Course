package com.example.garbageclassification.util;

import android.content.res.AssetManager;
import android.util.Log;


import com.example.garbageclassification.model.Garbage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GarbageService {
    private ArrayList<Garbage> garbges;
    private static final String TAG = "GarbageService";
    private static final String CSVPath = "garbage.csv";

    public ArrayList<Garbage> readCSV(AssetManager manager) {
        Log.d(TAG, "readCSV: Read CSV begin");
        garbges = new ArrayList<>();
        Scanner scanner;
        InputStream inputStream;
        try {
            inputStream = manager.open(CSVPath);
            scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNext()) {
                String sourceString = scanner.nextLine();
                String[] sub = sourceString.split(",");
                String name = sub[0];
                int i;
                Garbage.GarbageType type;
                try {
                    i = Integer.parseInt(sub[1]);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "readCSV: Unknown trash: " + sourceString);
                    i = 0;
                }
                if (i == Garbage.GarbageType.recycle.typeValue) {
                    type = Garbage.GarbageType.recycle;
                } else if (i == Garbage.GarbageType.harmful.typeValue) {
                    type = Garbage.GarbageType.harmful;
                } else if (i == Garbage.GarbageType.kitchen.typeValue) {
                    type = Garbage.GarbageType.kitchen;
                } else{
                    type = Garbage.GarbageType.others;
                }
                Garbage newItem = new Garbage(name, type);
                garbges.add(newItem);
            }
        } catch (IOException e) {
            Log.d(TAG, "readCSV: Error on reading CSV");
        }
        Log.d(TAG, "readCSV: Read CSV finish");
        return garbges;
    }

    public ArrayList<Garbage> classifyGarbage(ArrayList<Garbage> lotsGarbages, Garbage.GarbageType type) {
        ArrayList<Garbage> result = new ArrayList<>();
        for (Garbage garbage : lotsGarbages) {
            if (garbage.getType() == type) {
                result.add(garbage);
            }
        }
        return result;
    }

    public ArrayList<Garbage> search(ArrayList<Garbage> dataSet, String keywords) {
        ArrayList<Garbage> result = new ArrayList<>();
        char[] chars = keywords.toLowerCase().toCharArray();
        StringBuilder temp = new StringBuilder("^\\w*");
        for (char ch : chars) {
            temp.append(ch);
            temp.append("\\w*");
        }
        temp.append("$");
        String pattern = temp.toString();
        for (Garbage trash : dataSet) {
            if (Pattern.matches(pattern, trash.getName().toLowerCase()) ||
                    Pattern.matches(pattern, Util.ChineseToPinYin(trash.getName()).toLowerCase())) {
                result.add(trash);
            }
        }
        return result;
    }
}
