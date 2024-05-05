package com.example.workoutcompanion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class welcomeActivity extends AppCompatActivity {

    private int isManSelected = 0; // Variable to determine if male is selected or not, 1 for male, 2 for female and 0 is the initial value for neither.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome); // Gets the layout of the screen from welcome.xml and sets it according to that.

        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        // Creates an animation for scaling down the images
        ScaleAnimation scaleDown = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleDown.setDuration(500); // Sets the duration of the scaleDown animation to 500ms

        // Creates an animation for scaling up the images
        ScaleAnimation scaleUp = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleUp.setDuration(500);

        // Creating the animation for the male to be moved to the middle and to the side
        TranslateAnimation moveToMiddleMale = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.3f,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        moveToMiddleMale.setDuration(500);

        TranslateAnimation moveToSideMale = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -0.3f,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        moveToSideMale.setDuration(500);

        // Creating the animations for the female to be moved to the middle and to the side
        TranslateAnimation moveToMiddleFemale = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -0.4f,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        moveToMiddleMale.setDuration(500);

        // Moving to side animation
        TranslateAnimation moveToSideFemale = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.3f,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        moveToSideMale.setDuration(500);


        ImageView yellowMan = findViewById(R.id.imageView); // Gets the id of the male and female images
        ImageView yellowWoman = findViewById(R.id.imageView2);

        // For actions that happen after the animation starts or ends
        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Actions after animations complete
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };

        yellowMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isManSelected == 0 | isManSelected == 2){
                    AnimationSet animationSetMan = new AnimationSet(true);
                    animationSetMan.addAnimation(scaleUp);
                    animationSetMan.addAnimation(moveToMiddleMale);
                    animationSetMan.setFillAfter(true); // Keeps the image at the final position of the animation
                    animationSetMan.setAnimationListener(animationListener);

                    AnimationSet animationSetWoman = new AnimationSet(true);
                    animationSetWoman.addAnimation(scaleDown);
                    animationSetWoman.addAnimation(moveToSideFemale);
                    animationSetWoman.setFillAfter(true); // Keeps the image at the final position of the animation
                    animationSetWoman.setAnimationListener(animationListener);

                    yellowMan.startAnimation(animationSetMan);
                    yellowWoman.startAnimation(animationSetWoman);
                    isManSelected = 1;
                }
            }
        });

        yellowWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isManSelected == 0 | isManSelected == 1){
                    AnimationSet animationSetWoman = new AnimationSet(true);
                    animationSetWoman.addAnimation(scaleUp);
                    animationSetWoman.addAnimation(moveToMiddleFemale);
                    animationSetWoman.setFillAfter(true);
                    animationSetWoman.setAnimationListener(animationListener);

                    AnimationSet animationSetMan = new AnimationSet(true);
                    animationSetMan.addAnimation(scaleDown);
                    animationSetMan.addAnimation(moveToSideMale);
                    animationSetMan.setFillAfter(true);
                    animationSetMan.setAnimationListener(animationListener);

                    yellowWoman.startAnimation(animationSetWoman);
                    yellowMan.startAnimation(animationSetMan);
                    isManSelected = 2;
                }
            }
        });

        Button continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent welcomeIntent = new Intent(welcomeActivity.this, welcomeActivity2.class); // Creates a new intent so the screen can be switched to the report screen.
                welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(welcomeIntent);
            }
        });


    }

}
