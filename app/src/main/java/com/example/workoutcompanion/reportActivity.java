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

import org.w3c.dom.Text;

import java.util.Locale;

public class reportActivity extends AppCompatActivity {

    private String name;

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
        TextView workoutNumber = findViewById(R.id.workoutsTitle2);
        TextView repetitionNumber = findViewById(R.id.repetitionsTitle2);
        TextView workoutStreak = findViewById(R.id.workoutStreakTitle);

        // Gets the ids of the labels so they can be used when the user switches between data viewing
        TextView reportTitle = findViewById(R.id.reportTitle);
        TextView navMonth = findViewById(R.id.navMonth);
        TextView navWeek = findViewById(R.id.navWeek);
        TextView navDay = findViewById(R.id.navDay);

        SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE);
        name = choices.getString("name", "null"); // Gets the value of the name key, if it doesn't exist the value becomes null instead
        String email = choices.getString("email", "null");
        String experience = choices.getString("experience", "null");


        workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(0));
        repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(0));

        if (!"null".equals(name)) { // Determines if the user has an account or not
            DatabaseManager db = new DatabaseManager(this);
            int currentStreak = db.getStreak(email);
            workoutStreak.setText("\uD83D\uDCC8 Workout Streak:\n  " + String.valueOf(currentStreak));
            Cursor dailyStats = db.getDaily(email); // Opens a cursor called dailyStats to retrieve the data

            if (dailyStats.moveToFirst()) { // Move to first checks if there are any records returned, if there are it will return true
                @SuppressLint("Range") int totalWorkouts = dailyStats.getInt(dailyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                @SuppressLint("Range") int totalReps = dailyStats.getInt(dailyStats.getColumnIndex("total_reps")); // Gets the values of the daily totals from the SQLite database using the dailyStats function
                @SuppressLint("Range") int totalExercises = dailyStats.getInt(dailyStats.getColumnIndex("total_exercises"));
                @SuppressLint("Range") int totalDuration = dailyStats.getInt(dailyStats.getColumnIndex("total_duration"));

                // Takes the ids of the labels and then assigns the correct values to the label for the user to see
                exercisesNum.setText(String.valueOf(totalExercises));
                float realTime = (float) totalDuration / 3600; // To convert the amount of seconds to an hour we have to divide it by 3600 since there are 3600 seconds in an hour
                String formattedTime = String.format(Locale.getDefault(), "%.1f", realTime); // Converts realTime into a more presentable format (such as 1 decimal point and rounding up)
                timeNum.setText(formattedTime);

                workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(totalWorkouts));
                repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(totalReps));

            }
            TextView middleText = findViewById(R.id.middleText);
            if ("beginner".equals(experience)){
                experience = " beginner.";
            } else if ("intermediate".equals(experience)){ // Formats the experience level so it can be added to a sentence in a more readable format
                experience = "t an intermediate level.";
            } else if ("advanced".equals(experience)) {
                experience = "t an advanced level.";
            } else if ("elite".equals(experience)) {
                experience = "t an elite level.";
            }

            middleText.setText("You are a" + experience);

            dailyStats.close();

            navMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor monthlyStats = db.getMonthly(email);
                    navMonth.setTextColor(Color.parseColor("#FF0000"));
                    navWeek.setTextColor(Color.parseColor("#FFFFFF"));
                    navDay.setTextColor(Color.parseColor("#FFFFFF")); // These extra changes before the if statement just reset everything to 0 in case there isnt any data for today.
                    reportTitle.setText("This Month");                         // then everything is changed if data is found.
                    exercisesNum.setText(String.valueOf(0));
                    timeNum.setText(String.valueOf(0));
                    workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(0));
                    repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(0));

                    if (monthlyStats != null && monthlyStats.moveToFirst()) { // This checks that there is actually data beforehand. One possible case is that if the user creates an account and checks the report screen before doing a workout there could be a possible crash.
                        @SuppressLint("Range") int totalWorkouts = monthlyStats.getInt(monthlyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                        @SuppressLint("Range") int totalReps = monthlyStats.getInt(monthlyStats.getColumnIndex("total_reps")); // Gets the values of the monthly totals from the SQLite database using the monthlyStats function
                        @SuppressLint("Range") int totalExercises = monthlyStats.getInt(monthlyStats.getColumnIndex("total_exercises"));
                        @SuppressLint("Range") int totalDuration = monthlyStats.getInt(monthlyStats.getColumnIndex("total_duration"));

                        exercisesNum.setText(String.valueOf(totalExercises));
                        float realTime = (float) totalDuration / 3600; // To convert the amount of seconds to an hour we have to divide it by 3600 since there are 3600 seconds in an hour
                        String formattedTime = String.format(Locale.getDefault(), "%.1f", realTime); // Converts realTime into a more presentable format (such as 1 decimal point and rounding up)
                        timeNum.setText(formattedTime);
                        workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(totalWorkouts));
                        repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(totalReps));
                        monthlyStats.close(); // Closing the cursor so performance isnt affected by a possible memory leak if multiple cursors are still open and unused
                    }
                }
            });

            navWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor weeklyStats = db.getWeekly(email);
                    navMonth.setTextColor(Color.parseColor("#FFFFFF"));
                    navWeek.setTextColor(Color.parseColor("#FF0000"));
                    navDay.setTextColor(Color.parseColor("#FFFFFF")); // These extra changes before the if statement just reset everything to 0 in case there isnt any data for today.
                    reportTitle.setText("This Week");                          // then everything is changed if data is found.
                    exercisesNum.setText(String.valueOf(0));
                    timeNum.setText(String.valueOf(0));
                    workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(0));
                    repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(0));

                    if (weeklyStats != null && weeklyStats.moveToFirst()) {
                        @SuppressLint("Range") int totalWorkouts = weeklyStats.getInt(weeklyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                        @SuppressLint("Range") int totalReps = weeklyStats.getInt(weeklyStats.getColumnIndex("total_reps")); // Gets the values of the weekly totals from the SQLite database using the weeklyStats function
                        @SuppressLint("Range") int totalExercises = weeklyStats.getInt(weeklyStats.getColumnIndex("total_exercises"));
                        @SuppressLint("Range") int totalDuration = weeklyStats.getInt(weeklyStats.getColumnIndex("total_duration"));

                        exercisesNum.setText(String.valueOf(totalExercises));
                        float realTime = (float) totalDuration / 3600; // To convert the amount of seconds to an hour we have to divide it by 3600 since there are 3600 seconds in an hour
                        String formattedTime = String.format(Locale.getDefault(), "%.1f", realTime); // Converts realTime into a more presentable format (such as 1 decimal point and rounding up)
                        timeNum.setText(formattedTime);
                        workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(totalWorkouts));
                        repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(totalReps));
                        weeklyStats.close(); // Closing the cursor so performance isnt affected by a possible memory leak if multiple cursors are still open and unused
                    }
                }
            });

            navDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor dailyStats = db.getDaily(email); // The cursor was closed earlier to save data so it had to be opened again
                    navMonth.setTextColor(Color.parseColor("#FFFFFF"));
                    navWeek.setTextColor(Color.parseColor("#FFFFFF"));
                    navDay.setTextColor(Color.parseColor("#FF0000")); // These extra changes before the if statement just reset everything to 0 in case there isnt any data for today.
                    reportTitle.setText("Today");                               // then everything is changed if data is found.
                    exercisesNum.setText(String.valueOf(0));
                    timeNum.setText(String.valueOf(0));
                    workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(0));
                    repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(0));

                    if (dailyStats != null && dailyStats.moveToFirst()) {
                        @SuppressLint("Range") int totalWorkouts = dailyStats.getInt(dailyStats.getColumnIndex("total_workouts")); // Suppresses the annoying -1 issue when loading data, that error would never occur because data always has to be in those columns
                        @SuppressLint("Range") int totalReps = dailyStats.getInt(dailyStats.getColumnIndex("total_reps")); // Gets the values of the daily totals from the SQLite database using the dailyStats function
                        @SuppressLint("Range") int totalExercises = dailyStats.getInt(dailyStats.getColumnIndex("total_exercises"));
                        @SuppressLint("Range") int totalDuration = dailyStats.getInt(dailyStats.getColumnIndex("total_duration"));

                        exercisesNum.setText(String.valueOf(totalExercises));
                        float realTime = (float) totalDuration / 3600; // To convert the amount of seconds to an hour we have to divide it by 3600 since there are 3600 seconds in an hour
                        String formattedTime = String.format(Locale.getDefault(), "%.1f", realTime); // Converts realTime into a more presentable format (such as 1 decimal point and rounding up)
                        timeNum.setText(formattedTime);
                        workoutNumber.setText("\uD83D\uDCAA Workouts:\n  " + String.valueOf(totalWorkouts));
                        repetitionNumber.setText("\uD83C\uDFCB\uFE0F Repetitions:\n  " + String.valueOf(totalReps));
                        dailyStats.close(); // Closing the cursor so performance isnt affected by a possible memory leak if multiple cursors are still open and unused
                    }
                }
            });
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


