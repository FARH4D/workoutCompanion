package com.example.workoutcompanion;

import android.content.ContentValues;
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
    public static final String KEY_USER_PREMIUM = "premiumstatus";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_PASSWORD + " TEXT," +
                KEY_USER_EMAIL + " TEXT PRIMARY KEY," +
                KEY_USER_BIRTH + " TEXT," +
                KEY_USER_PREMIUM + " TEXT" + ")";

        String CREATE_WORKOUT_TABLE = "CREATE TABLE Workouts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_email TEXT," +
                "date TEXT," +
                "reps INTEGER," +
                "exercises INTEGER," +
                "duration INTEGER" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_WORKOUT_TABLE);
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

    public Cursor getDetails(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERS,
                new String[] { KEY_USER_NAME, KEY_USER_BIRTH, KEY_USER_EMAIL, KEY_USER_PREMIUM }, // Include all required columns
                KEY_USER_EMAIL + "=? AND " + KEY_USER_PASSWORD + "=?", // WHERE clause
                new String[] { email, password }, // WHERE parameters
                null, null, null, null
        );
        return cursor; // Return the cursor directly, null check will be handled by the caller
    }

    public void insertWorkout(String email, String date, int reps, int exercises, int duration){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues values2 = new ContentValues(); // Opens a content values instance so I can insert data with a key and a value (the key will be the column the data goes into).
        values2.put("user_email", email);
        values2.put("date", date);
        values2.put("reps", reps);
        values2.put("exercises", exercises);
        values2.put("duration", duration);

        db2.insert("Workouts", null, values2); // Inserts my data into the table
        db2.close();
    }

    public Cursor getDaily(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT date(date) AS day, " +
                "COUNT(*) AS total_workouts, SUM(reps) AS total_reps, SUM(duration) AS total_duration, SUM(exercises) AS total_exercises " +
                "FROM Workouts WHERE user_email = ? AND date(date) = date('now', 'localtime') " +
                "GROUP BY day", new String[]{email});
    }

    public Cursor getWeekly(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT strftime('%Y-%W', date) AS week, " +
                "COUNT(*) AS total_workouts, SUM(reps) AS total_reps, SUM(duration) AS total_duration, SUM(exercises) AS total_exercises " +
                "FROM Workouts WHERE user_email = ? AND strftime('%Y-%W', date) = strftime('%Y-%W', 'now', 'localtime') " +
                "GROUP BY week", new String[]{email});
    }

    public Cursor getMonthly(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT strftime('%Y-%m', date) AS month, " +
                "COUNT(*) AS total_workouts, SUM(reps) AS total_reps, SUM(duration) AS total_duration, SUM(exercises) AS total_exercises " +
                "FROM Workouts WHERE user_email = ? AND strftime('%Y-%m', date) = strftime('%Y-%m', 'now', 'localtime') " +
                "GROUP BY month", new String[]{email});
    }
}