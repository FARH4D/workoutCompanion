package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class premiumWorkouts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium_workouts); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        FrameLayout workout1 = findViewById(R.id.button1);
        workout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Barbell Rack Pulls");
                exercises.add("Barbell Bent Over Rows"); // Adds each exercise to the list of exercises in the workout
                exercises.add("Pull ups");
                exercises.add("Inverted Rows");
                exercises.add("Monkey Rows");

                Intent premiumIntent1 = new Intent(premiumWorkouts.this, workingOutActivity.class);
                premiumIntent1.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                premiumIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(premiumIntent1);
            }
        });

        FrameLayout workout2 = findViewById(R.id.button2);
        workout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Pull ups");
                exercises.add("Burpee Push ups"); // Adds each exercise to the list of exercises in the workout
                exercises.add("Russian Kettlebell Swings");
                exercises.add("Push ups");
                exercises.add("Pull ups");
                exercises.add("Burpee Push ups");
                exercises.add("Russian Kettlebell Swings");
                exercises.add("Push ups");

                Intent premiumIntent2 = new Intent(premiumWorkouts.this, workingOutActivity.class);
                premiumIntent2.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                premiumIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(premiumIntent2);
            }
        });

        FrameLayout workout3 = findViewById(R.id.button3);
        workout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Barbell Bent Over Rows");
                exercises.add("Pull ups"); // Adds each exercise to the list of exercises in the workout
                exercises.add("Zercher Squats");
                exercises.add("Russian Kettlebell Swings");

                Intent premiumIntent3 = new Intent(premiumWorkouts.this, workingOutActivity.class);
                premiumIntent3.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                premiumIntent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(premiumIntent3);
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(premiumWorkouts.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport); // Gets the id of the report button on the bottom nav bar.
        reportButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the report button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(premiumWorkouts.this, reportActivity.class); // Creates a new intent so the screen can be switched to the report screen.
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(premiumWorkouts.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(premiumWorkouts.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });

    }
}
