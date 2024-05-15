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
        setContentView(R.layout.exercise_description);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        Intent intent = getIntent(); // Gets the intent that started the activity
        exercise = intent.getStringArrayListExtra("exerciseChosen"); // Gets the array from the intent and puts it into an arrayList called exercise

        TextView exerciseTitle = findViewById(R.id.exerciseTitle);
        exerciseTitle.setText(exercise.get(0));

        ImageView workoutGif = findViewById(R.id.exerciseGif);
        Integer gifInt = new workingOutBackend().getGif(exercise.get(0)); // Getting the location of the gif with the number assigned to it
        Glide.with(this).asGif().load(gifInt).into(workoutGif); // Using the glide library to play the gifs

    }
}