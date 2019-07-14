package com.tutorkomputer.bukatoko.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tutorkomputer.bukatoko.data.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="bukaToko";
    private static final int DATABASE_VERSION=1;

    private static SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_NAME   ="cart";
    private static final String CART_ID      ="cart_id";
    private static final String PRODUCT_ID   ="product_id";
    private static final String PRODUCT      ="product";
    private static final String IMAGE_URL    ="image";
    private static final String PRICE        ="price";
    private static final String CURRENT_DATE ="curr_date";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(
//                "CREATE TABLE " + TABLE_NAME + "(" + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
//                +PRODUCT_ID +    "INTEGER, "
//                + PRODUCT   +    "TEXT,"
//                + IMAGE_URL +    "TEXT,"
//                + PRICE     +    "INTEGER, "
//                + CURRENT_DATE + "DATE DEFAULT CURRENT_DATE);"
//        );
        db.execSQL(
                "CREATE TABLE cart (cart_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, product_id INTEGER," +
                        "product TEXT, image TEXT, price INTEGER ,curr_date DATE DEFAULT CURRENT_DATE );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }


    public Long addToCart(int product_id,String product,String image, int price){
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID,product_id);
        values.put(PRODUCT,product);
        values.put(IMAGE_URL,image);
        values.put(PRICE,price);
        long cart_id =sqLiteDatabase.insert(TABLE_NAME,null,values);
        return  cart_id;


    }


    public Integer checkExists(int product_id){
        String sql =" SELECT " + PRODUCT_ID + " FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + "='" + product_id + "'";
        sqLiteDatabase =getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        int count = cursor.getCount();
        return  count;
    }


    public List<Cart> myCart(){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + CART_ID + " DESC ";

        ArrayList<Cart> carts = new ArrayList<>();
        Cursor cursor =sqLiteDatabase.rawQuery(sqlQuery,null);
        cursor.moveToFirst();

        for (int i=0; i<cursor.getCount(); i++){
             cursor.moveToPosition(i);

             Cart cart = new Cart();
             cart.setCart_id(cursor.getInt(cursor.getColumnIndex(CART_ID)));
             cart.setProduct_id(cursor.getInt(cursor.getColumnIndex(PRODUCT_ID)));
             cart.setProduct(cursor.getString(cursor.getColumnIndex(PRODUCT)));
             cart.setImage(cursor.getString(cursor.getColumnIndex(IMAGE_URL)));
             cart.setPrice(cursor.getInt(cursor.getColumnIndex(PRICE)));
             cart.setCurr_date(cursor.getString(cursor.getColumnIndex(CURRENT_DATE)));

             Log.e("_logProductName",cursor.getString(cursor.getColumnIndex(PRODUCT)));
             carts.add(cart);
        }

        return  carts;
    }

    public void removeItem(String cart_id){
         sqLiteDatabase.delete(TABLE_NAME,CART_ID + "='" + cart_id + "'",null);
    }


    public void clearTable(){
        sqLiteDatabase.delete(TABLE_NAME,null,null);
    }

}
