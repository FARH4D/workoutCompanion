package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class exercisesList extends AppCompatActivity {

    private ArrayList<String> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_list); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        LinearLayout container = findViewById(R.id.buttonsContainer);
        Intent intent = getIntent(); // Gets the intent that started the activity
        exercises = intent.getStringArrayListExtra("exerciseList"); // Gets the array from the intent and puts it into an arrayList called exercises

        TextView exerciseTitle = findViewById(R.id.exerciseTitle);
        exerciseTitle.setText(exercises.get(0));

        for (int i = 1; i < exercises.size(); i++){ // A loop that creates buttons for each of the exercises in the array
            Button button = new Button(this); // This creates a button
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)); // Sets out the parameters of the button, how it is layed out and presented
            button.setText(exercises.get(i));
            int finalI = i; // Variable has to be final if i want to access it from the loop
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<String> exerciseChosen = new ArrayList<>(); // I have to pass an array to send to the next intent
                    exerciseChosen.add(exercises.get(finalI));

                    Intent exerciseIntent = new Intent(exercisesList.this, exerciseDescription.class);
                    exerciseIntent.putStringArrayListExtra("exerciseChosen", exerciseChosen); // Puts the exercises array into the intent being launched
                    startActivity(exerciseIntent); // I don't have flags for clearing the task because i want the user to have the ability to go back
                }
            });
            container.addView(button);
        }

        TextView goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(exercisesList.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(exercisesList.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport); // Gets the id of the report button on the bottom nav bar.
        reportButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the report button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(exercisesList.this, reportActivity.class); // Creates a new intent so the screen can be switched to the report screen.
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(exercisesList.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(exercisesList.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });
    }
}