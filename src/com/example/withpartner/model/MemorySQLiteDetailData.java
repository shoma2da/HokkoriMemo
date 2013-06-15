package com.example.withpartner.model;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

import android.content.Context;

public class MemorySQLiteDetailData extends SQLiteDetailData {

    public MemorySQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.memory);
    }

    @Override
    public int getImageResourceId() {
        return android.R.drawable.ic_menu_week;
    }

    @Override
    protected String getTypeName() {
        return Constatns.MEMORY;
    }

}
