package com.example.tiendadezapatos.ui.productos.helper;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tiendadezapatos.ui.productos.model.ProductoModel;

import java.util.LinkedList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    //Constantes

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CharmanderShoesShop.db";
    private final String TABLE_PRODUCTS = "productos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public boolean agregarProducto(String nombre, String descripcion, long precio, int cantidad, String idFirebase) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); //Obtenermos tbala en modo escritura
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("precio", precio);
            values.put("cantidad", cantidad);
            values.put("id_firebase", idFirebase);

            db.insert(TABLE_PRODUCTS, null, values);
//            db.close();
            return true;
        } catch (Exception e) {

            Log.e(TAG, "ERROR AL GUARDAR EL REGISTRO EN SQLITE: " + e.getMessage());
            return false;
        }
    }


    public boolean actualizarProducto(int id, String nombre, String descripcion, long precio, int cantidad, String idFirebase) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); //Obtenermos BASE DE DATOS en modo escritura
            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("precio", precio);
            values.put("cantidad", cantidad);
            values.put("id_firebase", idFirebase);

            db.update(TABLE_PRODUCTS, values, "where id = ?", new String[]{Integer.toString(id)});
//            db.close();
            return true;
        } catch (Exception e) {

            Log.e(TAG, "ERROR AL ACTUALIZAR EL REGISTRO EN SQLITE: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase(); //Obtenermos BASE DE DATOS en modo escritura
            ContentValues values = new ContentValues();
            values.put("id", id);
            db.update(TABLE_PRODUCTS, values, "where id = ?", new String[]{Integer.toString(id)});
//            db.close();

            return true;
        } catch (Exception e) {

            Log.e(TAG, "ERROR AL ELIMINAR EL REGISTRO EN SQLITE: " + e.getMessage());
            return false;
        }
    }


    @SuppressLint("Range")
    public List<ProductoModel> obtenerProductos() {
        try {
            List<ProductoModel> productosCarrito = new LinkedList<>();
            SQLiteDatabase db = this.getWritableDatabase(); //Obtenermos BASE DE DATOS en modo escritura
            Cursor res = db.rawQuery("select * from " + TABLE_PRODUCTS, null);
            res.moveToFirst();
            db.close();

            while (res.isAfterLast()) {
                int idDb = res.getInt(res.getColumnIndex("id"));
                String nombre = res.getString(res.getColumnIndex("nombre"));
                String descripcion = res.getString(res.getColumnIndex("descripcion"));
                long precio = res.getLong(res.getColumnIndex("precio"));
                int cantidad = res.getInt(res.getColumnIndex("cantidad"));

                String idFirebase = res.getString(res.getColumnIndex("id_firebase"));

                ProductoModel producto = new ProductoModel(idFirebase, nombre, descripcion, precio, cantidad, idDb);
                productosCarrito.add(producto);
                res.moveToNext();
            }
            return productosCarrito;
        } catch (Exception e) {

            Log.e(TAG, "ERROR AL OBTENER TODOS LOS PRODUCTOS: " + e.getMessage());
            return null;
        }
    }
}
