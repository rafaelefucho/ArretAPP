package com.example.rafael.diviaapp.utilities.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rafael on 25/01/2018.
 */

public class ArretFavoriteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "arretFavorite.db";

    private static final int DATABASE_VERSION = 2;

    public ArretFavoriteDBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_ARRETFAVORITE_TABLE = "CREATE TABLE " +
                ArretFavoriteContract.ArretFavorite.TABLE_NAME + " (" +
                ArretFavoriteContract.ArretFavorite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ArretFavoriteContract.ArretFavorite.COLUMN_LIGNE + " TEXT NOT NULL, " +
                ArretFavoriteContract.ArretFavorite.COLUMN_ARRET + " TEXT NOT NULL, " +
                ArretFavoriteContract.ArretFavorite.COLUMN_SENS +  " TEXT NOT NULL, " +
                ArretFavoriteContract.ArretFavorite.COLUMN_REFS +  " TEXT NOT NULL, " +
                ArretFavoriteContract.ArretFavorite.COLUMN_COLOR + " TEXT NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_ARRETFAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ArretFavoriteContract.ArretFavorite.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
