package com.example.workoutcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

public class reportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_page);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        TextView exercisesNum = findViewById(R.id.exercisesNum);
        TextView timeNum = findViewById(R.id.timeNum);

        SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE); // Gets the values of the shared preferences
        String name = choices.getString("name", "null"); // Gets the value of the name key, if it doesn't exist the value becomes null instead
        String email = choices.getString("email", "null");

        if (name != "null") { // Determines if the user has an account or not
            DatabaseManager db = new DatabaseManager(this);
            Cursor dailyStats = db.getWeekly(email);

            if (dailyStats.moveToFirst()) { // Move to first checks if there is any records returned, if there are it will return true
                @SuppressLint("Range") int totalWorkouts = dailyStats.getInt(dailyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                @SuppressLint("Range") int totalReps = dailyStats.getInt(dailyStats.getColumnIndex("total_reps")); // Gets the values of the weekly totals from the SQLite database using the weeklyStats function
                @SuppressLint("Range") int totalExercises = dailyStats.getInt(dailyStats.getColumnIndex("total_exercises"));
                @SuppressLint("Range") int totalDuration = dailyStats.getInt(dailyStats.getColumnIndex("total_duration"));


                exercisesNum.setText(String.valueOf(totalExercises));
                timeNum.setText(String.valueOf(totalDuration));
//                TextView workoutNumber = findViewById(R.id.workoutNumber);
//                workoutNumber.setText(String.valueOf(totalWorkouts));
//                TextView repetitionNumber = findViewById(R.id.repetitionNumber);
//                repetitionNumber.setText(String.valueOf(totalReps));
//                TextView timeNumber = findViewById(R.id.timeNumber);
//                timeNumber.setText(String.valueOf(totalDuration));
            } else { // Condition for it the user has an account

            }
        }


        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(reportActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(reportActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(reportActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });



    }



}


