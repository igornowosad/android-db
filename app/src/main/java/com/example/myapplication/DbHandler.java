package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "phoneDetails";
    private static final String TABLE_PHONES = "phones";

    private static final String KEY_PHONE_ID = "phoneId";
    private static final String KEY_PHONE_PRODUCER = "producer";
    private static final String KEY_PHONE_MODEL = "model";
    private static final String KEY_PHONE_ANDROID_VERSION = "androidVersion";
    private static final String KEY_PHONE_URL = "url";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PHONES_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_PHONES + "("
                + KEY_PHONE_ID + " INTEGER (10) PRIMARY KEY, "
                + KEY_PHONE_PRODUCER + " TEXT, "
                + KEY_PHONE_MODEL + " TEXT, "
                + KEY_PHONE_ANDROID_VERSION + " TEXT, "
                + KEY_PHONE_URL + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_PHONES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONES);
        onCreate(sqLiteDatabase);
    }

    public void addPhone(PhoneModel phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Content values use KEY-VALUE pair concept
        ContentValues values = new ContentValues();
        values.put(KEY_PHONE_ID, phone.getPhoneId());
        values.put(KEY_PHONE_MODEL, phone.getModel());
        values.put(KEY_PHONE_PRODUCER, phone.getProducer());
        values.put(KEY_PHONE_ANDROID_VERSION, phone.getAndroidVersion());
        values.put(KEY_PHONE_URL, phone.getUrl());

        db.insert(TABLE_PHONES, null, values);
        db.close();
    }

    public PhoneModel getPhone(int phoneId) {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_PHONES,
                new String[]{
                        KEY_PHONE_ID,
                        KEY_PHONE_PRODUCER,
                        KEY_PHONE_MODEL,
                        KEY_PHONE_ANDROID_VERSION,
                        KEY_PHONE_URL
                },
                KEY_PHONE_ID + "=?",
                new String[]{String.valueOf(phoneId)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        //Return Student
        return new PhoneModel(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }

    public List<PhoneModel> getAllPhones() {
        List<PhoneModel> phonesList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHONES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                PhoneModel student = new PhoneModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));

                phonesList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return student list
        return phonesList;
    }

    public int updatePhone(PhoneModel phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHONE_MODEL, phone.getModel());
        values.put(KEY_PHONE_PRODUCER, phone.getProducer());
        values.put(KEY_PHONE_ANDROID_VERSION, phone.getAndroidVersion());
        values.put(KEY_PHONE_URL, phone.getUrl());

        // updating phone row
        return db.update(TABLE_PHONES,
                values,
                KEY_PHONE_ID + " = ?",
                new String[]{String.valueOf(phone.getPhoneId())});

    }

    // Deleting single phone
    public void deletePhone(String phoneId) {
        Log.d("MainActivity", "DELETEEEEEEEEEEDELETEEEEEEEEEEDELETEEEEEEEEEE..");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHONES, KEY_PHONE_ID + " = ?",
                new String[]{phoneId});
        db.close();
    }
}
