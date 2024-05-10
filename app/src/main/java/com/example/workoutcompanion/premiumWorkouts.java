package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

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
                exercises.add("Push ups");
                exercises.add("Squats"); // Adds each exercise to the list of exercises in the workout
                exercises.add("Pull ups");

                Intent basicIntent1 = new Intent(premiumWorkouts.this, workingOutActivity.class);
                basicIntent1.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                basicIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(basicIntent1);
            }
        });
    }
}
