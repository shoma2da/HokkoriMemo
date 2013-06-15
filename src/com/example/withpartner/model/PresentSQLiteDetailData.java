package com.example.withpartner.model;

import android.content.Context;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

public class PresentSQLiteDetailData extends SQLiteDetailData {

    public PresentSQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.present).replaceAll("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_present;
    }

    @Override
    protected String getTypeName() {
        return Constatns.PRESENT;
    }

}
