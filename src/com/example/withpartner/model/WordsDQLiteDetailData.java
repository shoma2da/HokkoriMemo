package com.example.withpartner.model;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

import android.content.Context;

public class WordsDQLiteDetailData extends SQLiteDetailData {

    public WordsDQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.words);
    }

    @Override
    public int getImageResourceId() {
        return android.R.drawable.ic_btn_speak_now;
    }

    @Override
    protected String getTypeName() {
        return Constatns.WORDS;
    }

}
