package com.example.workoutcompanion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class welcomeActivity3 extends AppCompatActivity {

    private String experience = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome3); // Gets the layout of the screen from welcome.xml and sets it according to that.

        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        Button beginner = findViewById(R.id.beginnerButton);
        Button intermediate = findViewById(R.id.intermediateButton);
        Button advanced = findViewById(R.id.advancedButton);
        Button elite = findViewById(R.id.eliteButton);
        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experience = "beginner";
                beginner.setBackgroundColor(Color.parseColor("#444343"));
                intermediate.setBackgroundColor(Color.parseColor("#00444343"));
                advanced.setBackgroundColor(Color.parseColor("#00444343"));
                elite.setBackgroundColor(Color.parseColor("#00444343"));
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experience = "intermediate";
                intermediate.setBackgroundColor(Color.parseColor("#444343"));
                beginner.setBackgroundColor(Color.parseColor("#00444343"));
                advanced.setBackgroundColor(Color.parseColor("#00444343"));
                elite.setBackgroundColor(Color.parseColor("#00444343"));
            }
        });

        advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experience = "advanced";
                advanced.setBackgroundColor(Color.parseColor("#444343"));
                beginner.setBackgroundColor(Color.parseColor("#00444343"));
                intermediate.setBackgroundColor(Color.parseColor("#00444343"));
                elite.setBackgroundColor(Color.parseColor("#00444343"));
            }
        });

        elite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experience = "elite";
                elite.setBackgroundColor(Color.parseColor("#444343"));
                beginner.setBackgroundColor(Color.parseColor("#00444343"));
                intermediate.setBackgroundColor(Color.parseColor("#00444343"));
                advanced.setBackgroundColor(Color.parseColor("#00444343"));
            }
        });

        Button continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(experience != ""){
                    SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE); // Open a sharedpreferences instance to save the userchoices so they can be used later
                    SharedPreferences.Editor editor = choices.edit(); // Opens the editor for the sharedpreferences
                    editor.putString("experience", experience);
                    editor.apply(); // Saves the edited data

                    Intent welcomeIntent = new Intent(welcomeActivity3.this, welcomeActivity3.class); // Creates a new intent so the screen can be switched to the welcome screen 2.
                    welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(welcomeIntent);
                }
            }
        });

    }
}
