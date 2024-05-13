package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class exerciseActivity extends AppCompatActivity {
    private boolean isFront = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        final FrameLayout frontContainer = findViewById(R.id.frontContainer);
        final FrameLayout backContainer = findViewById(R.id.backContainer);
        Button turnButton = findViewById(R.id.turnButton);
        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setDuration(300);

                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                fadeIn.setDuration(500);

                // Decides which image is going to be faded in or out depending on the isFront boolean.
                final View toFadeOut = isFront ? frontContainer : backContainer;
                final View toFadeIn = isFront ? backContainer : frontContainer;

                // Animation listener so the fade in and out animations can happen one after the other rather than at the same time, allowing for smoother animation.
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }  // Necessary class to be implemented with AnimationListener.

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Hides the fading out image and starts fading in the new image.
                        toFadeOut.setVisibility(View.GONE);
                        toFadeIn.startAnimation(fadeIn);
                        toFadeIn.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }  // Necessary class to be implemented with AnimationListener
                });

                toFadeOut.startAnimation(fadeOut); // Starts the fade out animation

                isFront = !isFront; // Toggles the boolean variable for the next button press.
            }
        });

        TextView shouldersLabel = findViewById(R.id.shoulderLabel);
        shouldersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Shoulders");
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView chestLabel = findViewById(R.id.chestLabel);
        chestLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Chest");
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView absLabel = findViewById(R.id.absLabel);
        absLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView bicepsLabel = findViewById(R.id.bicepLabel);
        bicepsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView quadsLabel = findViewById(R.id.quadLabel);
        quadsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView trapsLabel = findViewById(R.id.trapLabel);
        trapsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView glutesLabel = findViewById(R.id.gluteLabel);
        glutesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView tricepsLabel = findViewById(R.id.tricepLabel);
        tricepsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView latsLabel = findViewById(R.id.latLabel);
        latsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView hamstringsLabel = findViewById(R.id.hamstringLabel);
        hamstringsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        TextView calvesLabel = findViewById(R.id.calfLabel);
        calvesLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> exercises = new ArrayList<>(); // Creates an array list called exercises so it can be sent to the working out activity
                exercises.add("Push ups");
                exercises.add("Squats");
                exercises.add("Pull ups");

                Intent exerciseList = new Intent(exerciseActivity.this, exercisesList.class);
                exerciseList.putStringArrayListExtra("exerciseList", exercises); // Puts the exercises array into the intent being launched
                exerciseList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseList);
            }
        });

        
        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(exerciseActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(exerciseActivity.this, reportActivity.class);
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(exerciseActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });



    }
}