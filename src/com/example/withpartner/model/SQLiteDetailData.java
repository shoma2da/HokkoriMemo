package com.example.withpartner.model;

import java.util.ArrayList;

import com.example.withpartner.Constatns;

import android.content.Context;

public abstract class SQLiteDetailData implements DetailData {
    
    public static SQLiteDetailData create(Context context, String type) {
        if (Constatns.TRIP.equals(type)) {
            return new TripSQLiteDetailData(context);
        } else if (Constatns.FAMILY.equals(type)) {
            return new FamilySQliteDetailData(context);
        } else if (Constatns.LAUGH.equals(type)) {
            return new LaughSQLiteDetailData(context);
        } else if (Constatns.MEMORY.equals(type)) {
            return new MemorySQLiteDetailData(context);
        } else if (Constatns.PRESENT.equals(type)) {
            return new PresentSQLiteDetailData(context);
        } else if (Constatns.WORDS.equals(type)) {
            return new WordsDQLiteDetailData(context);
        }
        return null;
    }
    
    //TODO ダミーです！
    private ArrayList<String> hokkoriList = new ArrayList<String>();
    private Context context;

    public SQLiteDetailData(Context context) {
        this.context = context;
    }
    
    protected abstract String getTableName();
    
    @Override
    public final ArrayList<String> getHokkoriList() {
        return new ArrayList<String>(hokkoriList);
    }
    
    @Override
    public final boolean addHokkoriList(String hokkori) {
        return this.hokkoriList.add(hokkori);
    }
    
    @Override
    public final boolean removeHokkoriList(String hokkori) {
        return this.hokkoriList.remove(hokkori);
    }
    
    protected Context getContext() {
        return this.context;
    }
}
