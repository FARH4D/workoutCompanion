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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {

 // REMEMBER TO COMMENT EVERYTHING




    // REMEMBER TO COMMENT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        TextView statWarning = findViewById(R.id.statWarning);
        SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE); // Gets the values of the shared preferences
        String name = choices.getString("name", "null"); // Gets the value of the name key, if it doesn't exist the value becomes null instead
        String email = choices.getString("email", "null");
        String premiumStatus = choices.getString("premiumstatus", "null");

        if ("null".equals(name)){ // Determines if the user has an account or not
            welcomeMessage.setText("Good morning!");
            statWarning.setText("Statistics are not stored until you sign in");
        } else { // Condition for it the user has an account
            welcomeMessage.setText("Good morning, " + name + "!");

            DatabaseManager db = new DatabaseManager(this);
            Cursor weeklyStats = db.getWeekly(email);

            if (weeklyStats.moveToFirst()){ // Move to first checks if there is any records returned, if there are it will return true
                @SuppressLint("Range") int totalWorkouts = weeklyStats.getInt(weeklyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                @SuppressLint("Range") int totalReps = weeklyStats.getInt(weeklyStats.getColumnIndex("total_reps")); // Gets the values of the weekly totals from the SQLite database using the weeklyStats function
                @SuppressLint("Range") int totalDuration = weeklyStats.getInt(weeklyStats.getColumnIndex("total_duration"));

                TextView workoutNumber = findViewById(R.id.workoutNumber);
                workoutNumber.setText(String.valueOf(totalWorkouts));
                TextView repetitionNumber = findViewById(R.id.repetitionNumber);
                repetitionNumber.setText(String.valueOf(totalReps));
                TextView timeNumber = findViewById(R.id.timeNumber);
                timeNumber.setText(String.valueOf(totalDuration));
            }
        }

        FrameLayout basicButton = findViewById(R.id.button1);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent basicIntent = new Intent(homeActivity.this, basicWorkouts.class);
                basicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(basicIntent);
            }
        });

        FrameLayout premiumButton = findViewById(R.id.button2);
        premiumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (premiumStatus == "null" | premiumStatus == "basic"){
                    Toast.makeText(homeActivity.this, "You are not a premium member", Toast.LENGTH_SHORT).show();
                } else {
                    Intent premiumIntent = new Intent(homeActivity.this, premiumWorkouts.class);
                    premiumIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(premiumIntent);
                }
            }
        });

        TextView reportButton = findViewById(R.id.navReport); // Gets the id of the report button on the bottom nav bar.
        reportButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the report button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(homeActivity.this, reportActivity.class); // Creates a new intent so the screen can be switched to the report screen.
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(homeActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(homeActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });

    }
}