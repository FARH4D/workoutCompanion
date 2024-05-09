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
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class workingOutActivity extends AppCompatActivity {
    private Handler workoutHandler = new Handler();
    private ArrayList<String> exercises;
    private Runnable run;
    private int exerciseIndex = 0; // Initialise all these variables at the start so they can be used in every function
    private ImageView workoutGif;
    private TextView timerText;
    private int timeRemaining;
    private boolean preparing = true;
    private boolean rest = true;
    private TextView exerciseTitle;
    private TextView timerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_out); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)
                                                                                                                                // FLAG LIGHT was added because the design will have a white background, so without this the text on the status bar would be white and unreadable
        Intent intent = getIntent(); // Gets the intent that started the activity
        exercises = intent.getStringArrayListExtra("exerciseList"); // Gets the array from the intent and puts it into an arrayList called exercises

        timerTitle = findViewById(R.id.timerTitle);
        exerciseTitle = findViewById(R.id.exerciseTitle);
        timerText = findViewById(R.id.timer);
        workoutGif = findViewById(R.id.exerciseGif);

        prepare();

    }


    private void prepare(){
        timerTitle.setText("Are you ready?");
        exerciseTitle.setText("Next exercise is " + exercises.get(exerciseIndex)); // Gets the first exercise in the list to inform the user
        timeRemaining = 11;
        String currentExercise = exercises.get(exerciseIndex);
        Integer gifInt = new workingOutBackend().getGif(currentExercise); // Getting the location of the gif with the number assigned to it
        Glide.with(this).asGif().load(gifInt).into(workoutGif); // Using the glide library to play the gifs
        updateTimer();
    }

    private void exerciseLoop(){
        if (exerciseIndex < exercises.size()){ // Starting a loop to go through the exercise array
            exerciseTitle.setText(exercises.get(exerciseIndex));
            exerciseIndex++;
            timeRemaining = 31;
            workoutHandler.postDelayed(this::updateTimer, 0); // Starts the updateTimer function with no delay.
        } else {
            exerciseTitle.setText("Good job you completed the workout!");
            timerText.setText("");
        }
    }

    private void updateTimer(){
        if (timeRemaining > 0){
            timeRemaining--; // Takes away 1 second
            timerText.setText(String.valueOf(timeRemaining)); // Updates the timer label with the current time
            workoutHandler.postDelayed(this::updateTimer, 1000); // Updates every second because the function is run after 1 second
        } else {
            if (preparing) {
                preparing = false;
                timerTitle.setText("");
                exerciseTitle.setText(exercises.get(exerciseIndex));

                ConstraintLayout.LayoutParams exerciseParameters = (ConstraintLayout.LayoutParams) exerciseTitle.getLayoutParams(); // Gets the parameters of my exercise label (using constraint because I used a constraint layout)
                exerciseParameters.topMargin = 100;
                exerciseTitle.setTextSize(36);
                exerciseTitle.setTextColor(Color.parseColor("#FF0000"));
                exerciseTitle.setLayoutParams(exerciseParameters); // Sets the new parameters I created

                ConstraintLayout.LayoutParams timerParameters = (ConstraintLayout.LayoutParams) timerText.getLayoutParams();
                timerParameters.bottomMargin = 600;
                timerText.setTextSize(90);
                timerText.setLayoutParams(timerParameters);
                rest = true;
                exerciseLoop();

            } else if (rest && exerciseIndex < exercises.size()) { // The rest loop is simple because it simply alternates between training/rest/training/rest. After each exercise phase is a 30 second rest phase and it keeps looping until the workout ends.
                rest = false;

                ConstraintLayout.LayoutParams exerciseParameters = (ConstraintLayout.LayoutParams) exerciseTitle.getLayoutParams(); // Gets the parameters of my exercise label (using constraint because I used a constraint layout)
                exerciseParameters.topMargin = 300;
                exerciseTitle.setTextSize(24);
                exerciseTitle.setTextColor(Color.parseColor("#FFFFFF"));
                exerciseTitle.setLayoutParams(exerciseParameters); // Sets the new parameters I created

                ConstraintLayout.LayoutParams timerParameters = (ConstraintLayout.LayoutParams) timerText.getLayoutParams();
                timerParameters.bottomMargin = 600;
                timerText.setTextSize(90);
                timerText.setLayoutParams(timerParameters);

                timerTitle.setText("Resting");
                exerciseTitle.setText("Next exercise is " + exercises.get(exerciseIndex));

                String nextExercise = exercises.get(exerciseIndex);
                Integer gifInt = new workingOutBackend().getGif(nextExercise); // Getting the location of the gif with the number assigned to it
                Glide.with(this).asGif().load(gifInt).into(workoutGif); // Using the glide library to play the gifs

                timeRemaining = 31;
                preparing = true;
                updateTimer();

            } else {
                rest = true;
                exerciseLoop();
            }
        }
    }
}
