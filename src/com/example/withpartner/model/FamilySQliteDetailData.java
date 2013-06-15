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
        return getContext().getString(R.string.family).replaceAll("\n", "");
    }

    @Override
    public int getImageResourceId() {
        return R.drawable.icon_family;
    }

    @Override
    protected String getTypeName() {
        return Constatns.FAMILY;
    }

}
