package com.example.garbageclassification.util;

import android.content.res.AssetManager;
import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.*;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import com.example.garbageclassification.model.Garbage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Util {

    private static final String TAG = "GarbageUtil";

    public static ArrayList<Garbage> readCSV(AssetManager manager) {
        Log.d(TAG, "readCSV: Read begin");
        ArrayList<Garbage> garbages = new ArrayList<>();
        Scanner scanner;
        InputStream inputStream;
        try {
            inputStream = manager.open("garbage.csv");
            scanner = new Scanner(inputStream, "UTF-8");
            while (scanner.hasNext()) {
                String sourceString = scanner.nextLine();
                String[] sub = sourceString.split(",");
                String name = sub[0];
                int i = Integer.parseInt(sub[1]);
                Garbage.GarbageType type;
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
                garbages.add(newItem);
            }
        } catch (NumberFormatException | IOException e) {
            Log.d(TAG, "readCSV: Error on reading CSV");
        }
        Log.d(TAG, "readCSV: Read CSV finish");
        return garbages;
    }

    public static ArrayList<Garbage> classifyGarbage(ArrayList<Garbage> lotGarbages, Garbage.GarbageType type) {
        ArrayList<Garbage> result = new ArrayList<>();
        for (Garbage garbages : lotGarbages) {
            if (garbages.getType() == type) {
                result.add(garbages);
            }
        }
        return result;
    }

    public static String ChineseToPinYin(String str) {
        HanyuPinyinOutputFormat outputF = new HanyuPinyinOutputFormat();
        outputF.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputF.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        char[] chars = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char ch : chars) {
            String[] strs = new String[0];
            try {
                strs = PinyinHelper.toHanyuPinyinStringArray(ch, outputF);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            if (strs == null) {
                result.append(ch);
            } else {
                result.append(strs[0]);
            }
        }
        return result.toString();
    }

}
