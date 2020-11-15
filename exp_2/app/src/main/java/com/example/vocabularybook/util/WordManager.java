package com.example.vocabularybook.util;


import android.database.sqlite.SQLiteDatabase;


import com.example.vocabularybook.module.Word;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class WordManager {
    public static void addWord(Word newWord) {
        newWord.save();
    }

    public static void updateWord(Word updateWord, int id) {
        updateWord.update(id);
    }

    public static void deleteAll() {
        LitePal.deleteAll(Word.class);
    }

    public static void deleteWord(int id) {
        LitePal.delete(Word.class, id);
    }

    public static List<Word> getAllWords() {
        return LitePal.findAll(Word.class);
    }

    public static Word getWordById(int id) {
        return LitePal.find(Word.class, id);
    }

    public static List<Word> getWordByKey(String key) {
        List<Word> temp = getAllWords();
        List<Word> result = new ArrayList<>();
        for (Word word : temp) {
            if (word.getEnglish().contains(key) ||
                    word.getChineseInterpretation().contains(key)) {
                result.add(word);
            }
        }
        return result;
    }

    public static SQLiteDatabase getWordTable() {
        return LitePal.getDatabase();
    }


}
