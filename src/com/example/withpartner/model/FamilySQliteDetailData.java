package com.example.withpartner.model;

import com.example.withpartner.Constatns;
import com.example.withpartner.R;

import android.content.Context;

public class FamilySQliteDetailData extends SQLiteDetailData {

    public FamilySQliteDetailData(Context context) {
        super(context);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.family);
    }

    @Override
    public int getImageResourceId() {
        return android.R.drawable.ic_dialog_dialer;
    }

    @Override
    protected String getTableName() {
        return Constatns.FAMILY;
    }

}
