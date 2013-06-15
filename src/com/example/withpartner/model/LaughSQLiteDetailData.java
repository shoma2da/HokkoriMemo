package com.example.withpartner.model;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

import android.content.Context;

public class LaughSQLiteDetailData extends SQLiteDetailData {

    public LaughSQLiteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.laugh);
    }

    @Override
    public int getImageResourceId() {
        return android.R.drawable.ic_dialog_email;
    }

    @Override
    protected String getTypeName() {
        return Constatns.LAUGH;
    }
    
}
