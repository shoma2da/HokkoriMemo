package com.tech_tec.withpartner.model;

import java.util.ArrayList;

import com.tech_tec.withpartner.Constatns;
import com.tech_tec.withpartner.data.Hokkori;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class SQLiteDetailData extends SQLiteOpenHelper implements DetailData {
    
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
    
    private static final String DB_NAME = "hokkori.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_HOKKORI_NAME = "hokkori";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date_millis";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_TYPE = "type";
    
    private Context context;

    public SQLiteDetailData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            String.format(
                "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL ) ",
                TABLE_HOKKORI_NAME, COLUMN_ID, COLUMN_DATE, COLUMN_TEXT, COLUMN_TYPE
            )
        );
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException();
    }
    
    protected abstract String getTypeName();
    
    @Override
    public final ArrayList<Hokkori> getHokkoriList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?", COLUMN_DATE, COLUMN_TEXT, COLUMN_ID, TABLE_HOKKORI_NAME, COLUMN_TYPE), new String[]{ this.getTypeName() });
        try {
            ArrayList<Hokkori> hokkoris = new ArrayList<Hokkori>();
            cursor.moveToFirst();
            int size = cursor.getCount();
            for (int i = 0; i < size; i++) {
                Hokkori hokkori = new Hokkori(cursor.getInt(2), cursor.getString(1), cursor.getLong(0));
                hokkoris.add(hokkori);
                cursor.moveToNext();
            }
        
            return hokkoris;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Hokkori>();
        } finally {
            cursor.close();
            db.close();
        }
    }
    
    @Override
    public final Hokkori addHokkoriList(Hokkori hokkori) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TYPE, getTypeName());
            values.put(COLUMN_DATE, hokkori.getTime());
            values.put(COLUMN_TEXT, hokkori.getText());
            
            long result = db.insert(TABLE_HOKKORI_NAME, null, values);
            if (result != -1) {
                return new Hokkori((int)result, hokkori.getText(), hokkori.getTime());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
    }
    
    @Override
    public final boolean removeHokkoriList(Hokkori hokkori) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            int result = db.delete(TABLE_HOKKORI_NAME, COLUMN_ID + " = ?", new String[]{ String.valueOf(hokkori.getId()) });
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }
    
    protected Context getContext() {
        return this.context;
    }
}
