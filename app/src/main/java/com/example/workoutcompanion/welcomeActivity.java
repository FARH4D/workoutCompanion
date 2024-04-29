package com.example.workoutcompanion;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome); // Gets the layout of the screen from welcome.xml and sets it according to that.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);  // Sets the application as full screen.

        ScaleAnimation scaleDown = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleDown.setDuration(500); // duration in ms

        // Scaling up animation
        ScaleAnimation scaleUp = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleUp.setDuration(500); // duration in ms

        // Moving to middle animation
        TranslateAnimation moveToMiddle = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        moveToMiddle.setDuration(500);

        // Moving to side animation
        TranslateAnimation moveToSide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0);
        moveToSide.setDuration(500);

        ImageView yellowMan = findViewById(R.id.imageView);
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
                AnimationSet animationSetMan = new AnimationSet(true);
                animationSetMan.addAnimation(scaleUp);
                animationSetMan.addAnimation(moveToMiddle);
                animationSetMan.setFillAfter(true); // Keeps the image at the final position of the animation
                animationSetMan.setAnimationListener(animationListener);

                AnimationSet animationSetWoman = new AnimationSet(true);
                animationSetWoman.addAnimation(scaleDown);
                animationSetWoman.addAnimation(moveToSide);
                animationSetWoman.setFillAfter(true); // Keeps the image at the final position of the animation
                animationSetWoman.setAnimationListener(animationListener);

                yellowMan.startAnimation(animationSetMan);
                yellowWoman.startAnimation(animationSetWoman);
            }
        });

        yellowWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet animationSetWoman = new AnimationSet(true);
                animationSetWoman.addAnimation(scaleUp);
                animationSetWoman.addAnimation(moveToMiddle);
                animationSetWoman.setFillAfter(true);
                animationSetWoman.setAnimationListener(animationListener);

                AnimationSet animationSetMan = new AnimationSet(true);
                animationSetMan.addAnimation(scaleDown);
                animationSetMan.addAnimation(moveToSide);
                animationSetMan.setFillAfter(true);
                animationSetMan.setAnimationListener(animationListener);

                yellowWoman.startAnimation(animationSetWoman);
                yellowMan.startAnimation(animationSetMan);
            }
        });

    }

}
