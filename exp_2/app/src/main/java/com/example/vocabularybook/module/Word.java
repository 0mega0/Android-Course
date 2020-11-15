package com.example.vocabularybook.module;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Word extends LitePalSupport {
    @Column(nullable = false, unique = true)
    private String english;

    @Column(nullable = false)
    private String chineseInterpretation;

    @Column(nullable = false)
    private int id;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChineseInterpretation() {
        return chineseInterpretation;
    }

    public void setChineseInterpretation(String chineseInterpretation) {
        this.chineseInterpretation = chineseInterpretation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
