package com.tech_tec.withpartner.model;

import com.tech_tec.withpartner.Constatns;
import com.tech_tec.withpartner.R;

import android.content.Context;

public class LaughSQLiteDetailData extends SQLiteDetailData {

    public LaughSQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.laugh).replace("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_laugh;
    }

    @Override
    protected String getTypeName() {
        return Constatns.LAUGH;
    }
    
}
