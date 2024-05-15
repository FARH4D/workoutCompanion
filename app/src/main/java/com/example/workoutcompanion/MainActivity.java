package com.example.workoutcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstTimeChecker FirstTimeChecker = new firstTimeChecker(this); // Creates an instance of the firsttimechecker class

        if (FirstTimeChecker.isFirstTime()) {
            Intent welcomeIntent = new Intent(MainActivity.this, welcomeActivity.class); // Creates a new intent so the screen can be switched to the next screen if the user is a first-time user
            welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(welcomeIntent);
        }
        else {
            Intent homeIntent = new Intent(MainActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home if it is not the user's first time.
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
        }
        finish();
    }
}