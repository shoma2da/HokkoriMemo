package com.example.withpartner.model;

import android.content.Context;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

public class TripSQLiteDetailData extends SQLiteDetailData {
    private final static String TABLE_NAME = Constatns.TRIP;
    
    public TripSQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.trip).replaceAll("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_trip;
    }

    @Override
    protected String getTypeName() {
        return TABLE_NAME;
    }

}
