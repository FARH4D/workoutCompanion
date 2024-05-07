package com.example.workoutcompanion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    // Database Info
    public static final String DATABASE_NAME = "workoutCompanionData";
    public static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_USERS = "users";

    // User Table Columns
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_PASSWORD = "password";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_BIRTH = "birthdate";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PASSWORD + " TEXT," +
                KEY_USER_EMAIL + " TEXT PRIMARY KEY," +
                KEY_USER_PASSWORD + "TEXT," +
                KEY_USER_BIRTH + " TEXT" + ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drops older table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // Creates tables again
        onCreate(db);
    }
    public boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase(); // Gets a readable version of the SQLite database
        Cursor cursor = db.query(TABLE_USERS, // We must makes an SQL query to check the table with cursor
                new String[]{KEY_USER_EMAIL}, KEY_USER_EMAIL + "=? AND " + KEY_USER_PASSWORD + "=?",
                new String[]{email, password}, null, null, null, null); // The rest of the parameters are not needed, but they have to be filled so I make them all null

        int count = cursor.getCount(); // Gets the number of rows returned by the getCount method.
        cursor.close(); // Closes cursor since it is no longer needed, to free up resources.
        db.close(); // Closes the database
        return count == 1; // Returns true if the count is 1, which means that 1 record matches the email and password combo
    }
}
