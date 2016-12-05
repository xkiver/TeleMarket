package com.google.android.gms.samples.vision.barcodereader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patricio on 02-12-2016.
 */
public class DataBaseClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="ShopCar.db";
    public static final String TABLE_NAME="carshop_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="DESCRIPTION";
    public static final String COL_4="VALUE";
    public static final String COL_5="CODE";
    public static final String COL_6="SUPERMERCADO";
    public static final String COL_7="LUGAR";

    public DataBaseClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" TEXT,"+COL_5+" INTEGER,"+COL_6+" TEXT,"+COL_7+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String description,String value, int code, String supermercado, String lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4,value);
        contentValues.put(COL_5,code);
        contentValues.put(COL_6,supermercado);
        contentValues.put(COL_7,lugar);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String name,String description,String value, int code, String supermercado, String lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,description);
        contentValues.put(COL_4,value);
        contentValues.put(COL_5,code);
        contentValues.put(COL_6,supermercado);
        contentValues.put(COL_7,lugar);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String nombre, String supermercado, String lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "SUPERMERCADO=? and NAME=? and LUGAR=?",new String[] {supermercado,nombre,lugar});
    }

    public Cursor getThatData(String nombre,String supermercado, String lugar) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" WHERE NAME='"+nombre+"' and SUPERMERCADO='"+supermercado+"' and LUGAR='"+lugar+"'",null);
        return res;
    }
}
