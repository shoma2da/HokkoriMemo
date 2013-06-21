package com.tech_tec.withpartner.model;

import com.tech_tec.withpartner.Constatns;
import com.tech_tec.withpartner.R;

import android.content.Context;

public class WordsDQLiteDetailData extends SQLiteDetailData {

    public WordsDQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.words).replaceAll("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_words;
    }

    @Override
    protected String getTypeName() {
        return Constatns.WORDS;
    }

}
