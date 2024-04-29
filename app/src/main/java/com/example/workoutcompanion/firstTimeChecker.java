package com.example.workoutcompanion;

import android.content.Context;
import android.content.SharedPreferences;

public class firstTimeChecker {

    private static final String DATANAME = "firstTimeData"; // Name of the file that stores the data
    private static final String FIRSTTIMEBOOL = "isFirstTime"; // Key for accessing the first time data (essentially a column in the data file)

    private SharedPreferences firstTime; // An instance of the SharedPreferences class

    public firstTimeChecker(Context context){
        firstTime = context.getSharedPreferences(DATANAME, Context.MODE_PRIVATE); // Retrieves the data inside 'firstTimeData', if the file does not exist it is created.
                                                                                  // Context mode private sets it so that the file can only be accessed by the application.
    }

    public boolean isFirstTime() {
        return firstTime.getBoolean(FIRSTTIMEBOOL, true); // Gets the value of isFirstTime
    }

    public void setFirstTime(boolean isFirstTime) {
        firstTime.edit().putBoolean(FIRSTTIMEBOOL, isFirstTime).apply(); // Sets the isFirstTime value
    }
}