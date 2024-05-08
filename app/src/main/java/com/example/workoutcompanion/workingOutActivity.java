package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class workingOutActivity extends AppCompatActivity {
    private Handler workoutHandler = new Handler();
    private ArrayList<String> exercises;
    private Runnable run;
    private int exerciseIndex = 0;
    private ImageView workoutGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_out); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        Intent intent = getIntent(); // Gets the intent that started the activity
        exercises = intent.getStringArrayListExtra("exerciseList"); // Gets the array from the intent and puts it into an arrayList called exercises

        workoutGif = findViewById(R.id.exerciseGif);
        workingOutBackend exerciseBackend = new workingOutBackend(); // Initialises a new instance of the workingOutBackend class

        exerciseLoop();

    }

    private void exerciseLoop(){
        if (exerciseIndex < exercises.size()){ // Starting a loop to go through the exercise array
            String currentExercise = exercises.get(exerciseIndex);
            exerciseIndex++;
            Integer gifInt = new workingOutBackend().getGif(currentExercise);
            Glide.with(this).asGif().load(gifInt).into(workoutGif);
        }
    }

}
