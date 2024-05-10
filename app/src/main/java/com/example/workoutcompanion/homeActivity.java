package com.example.workoutcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class homeActivity extends AppCompatActivity {

 // REMEMBER TO COMMENT EVERYTHING




    // REMEMBER TO COMMENT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page); // Gets the layout of the screen from home_page.xml and sets it according to that.
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        TextView statWarning = findViewById(R.id.statWarning);
        SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE);
        String name = choices.getString("name", "null"); // Gets the value of the name key, if it doesn't exist the value becomes null instead

        if (name == "null"){
            welcomeMessage.setText("Good morning!");
            statWarning.setText("Statistics are not stored until you sign in");

        } else {
            welcomeMessage.setText("Good morning, " + name + "!");
        }

        FrameLayout basicButton = findViewById(R.id.button1);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent basicIntent = new Intent(homeActivity.this, basicWorkouts.class);
                basicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(basicIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport); // Gets the id of the report button on the bottom nav bar.
        reportButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the report button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(homeActivity.this, reportActivity.class); // Creates a new intent so the screen can be switched to the report screen.
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(homeActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(homeActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}