package com.example.tiendadezapatos.ui.productos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    //Constantes

    private final String DATABASE_NAME = "CharmanderShoesShop.db";
    private final String TABLE_PRODUCTS = "productos";

    public dbHelper(@Nullable Context context, @Nullable String DATABASE_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { //metodo que se ejecuta para crear una tabla

        String sqlQueryCrear = "CREATE TABLE " + TABLE_PRODUCTS +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "precio NUMERIC NOT NULL," +
                "cantidad NUMERIC NOT NULL," +
                "id_firebase TEXT NOT NULL" +
                ");";

        sqLiteDatabase.execSQL(sqlQueryCrear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS); //Eliminar la base de datos cuando se lance una nueva version de la Base de datos
    }
}
