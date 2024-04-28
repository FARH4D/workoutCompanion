package com.example.workoutcompanion;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class exerciseActivity extends AppCompatActivity {
    private boolean isFront = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

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



    }
}