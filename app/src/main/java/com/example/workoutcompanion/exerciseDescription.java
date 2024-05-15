package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class exerciseDescription extends AppCompatActivity {

    private ArrayList<String> exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_description); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        Intent intent = getIntent(); // Gets the intent that started the activity
        exercise = intent.getStringArrayListExtra("exerciseChosen"); // Gets the array from the intent and puts it into an arrayList called exercise

        TextView exerciseTitle = findViewById(R.id.exerciseTitle);
        exerciseTitle.setText(exercise.get(0));

        ImageView workoutGif = findViewById(R.id.exerciseGif);
        Integer gifInt = new workingOutBackend().getGif(exercise.get(0)); // Getting the location of the gif with the number assigned to it
        Glide.with(this).asGif().load(gifInt).into(workoutGif); // Using the glide library to play the gifs

        TextView goHome = findViewById(R.id.goBack);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(exerciseDescription.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                finish(); // Clear the task before redirecting to save memory because it was opened as a new task.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport); // Gets the id of the report button on the bottom nav bar.
        reportButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the report button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(exerciseDescription.this, reportActivity.class); // Creates a new intent so the screen can be switched to the report screen.
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(exerciseDescription.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(exerciseDescription.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                startActivity(profileIntent);
            }
        });

    }
}