package com.tech_tec.withpartner.model;

import com.tech_tec.withpartner.Constatns;
import com.tech_tec.withpartner.R;

import android.content.Context;

public class MemorySQLiteDetailData extends SQLiteDetailData {

    public MemorySQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.memory).replaceAll("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_memory;
    }

    @Override
    protected String getTypeName() {
        return Constatns.MEMORY;
    }

}
