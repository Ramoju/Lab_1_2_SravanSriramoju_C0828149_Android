package com.example.lab_1_2_sravansriramoju_c0828149_android.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab_1_2_sravansriramoju_c0828149_android.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DB_NAME = "PRODUCTS_DB";

    private static final String TABLE_NAME = "PRODUCTS";
    private static final String COLUMN_ID = "PRODUCT_ID";
    private static final String COLUMN_NAME = "PRODUCT_NAME";
    private static final String COLUMN_DESC = "PRODUCT_DESCRIPTION";
    private static final String COLUMN_PRICE = "PRODUCT_PRICE";
    private static final String COLUMN_LATITUDE = "PROVIDER_LAT";
    private static final String COLUMN_LONGITUDE = "PROVIDER_LON";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT product_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESC + " TEXT NOT NULL, " +
                COLUMN_PRICE + " DOUBLE NOT NULL, " +
                COLUMN_LATITUDE + " DOUBLE NOT NULL, " +
                COLUMN_LONGITUDE + " DOUBLE NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

    public void insertProduct(ProductModel product){
        System.out.println("Insert method in DBhelper");
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getProductName());
        values.put(COLUMN_DESC, product.getDescription());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_LATITUDE, product.getLatitude());
        values.put(COLUMN_LONGITUDE,product.getLongitude());
        db.insert(TABLE_NAME,null, values);
    }

    public void deleteProduct(int id){
        db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"ID=?", new String[]{String.valueOf(id)});
    }

    public void updateProductName(int id, String name){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
    }

    public void updateProductPrice(int id, double price){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRICE, price);
        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
    }

    public void updateProductDescription(int id, String desc){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESC, desc);
        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
    }

    public void updateProviderLocation(int id, double latitude, double longitude){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        db.update(TABLE_NAME , values , "ID=?" , new String[]{String.valueOf(id)});
    }

    public List<ProductModel> getAllProducts() {
        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<ProductModel> products = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        ProductModel product = new ProductModel();
                        product.setProductId(cursor.getColumnIndex(COLUMN_ID));
                        product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                        product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESC)));
                        product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                        product.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUDE)));
                        product.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE)));
                        products.add(product);
                    }while (cursor.moveToNext());
                }
            }
        } finally {
            db.endTransaction();
            cursor.close();
        }
        return products;
    }
}
