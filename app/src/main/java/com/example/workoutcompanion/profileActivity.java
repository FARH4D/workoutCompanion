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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class profileActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        firstTimeChecker FirstTimeChecker = new firstTimeChecker(this);
        TextView textView = findViewById(R.id.textView);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileBirth = findViewById(R.id.profileBirth);
        TextView profileEmail = findViewById(R.id.profileEmail);
        Button signInButton = findViewById(R.id.signIn);
        Button premiumButton = findViewById(R.id.premiumButton);

        SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE);
        name = choices.getString("name", "null"); // Gets the value of the name key, if it doesn't exist the value becomes null instead

        // This if statement assigns the correct values to the textviews on the profile page, if the user is not signed in they are treated as a guest and the textviews showcase this.
        if (!"null".equals(name)){
            textView.setText(name);
            profileName.setText(name);
            profileBirth.setText(choices.getString("birth", "null"));
            profileEmail.setText(choices.getString("email", "null"));
            signInButton.setText("Sign Out");

            if ("premium".equals(choices.getString("premiumstatus", "null"))){ // Checks if the user has premium status
                premiumButton.setText("You have a premium membership.");
            } else {
                premiumButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent premiumIntent = new Intent(profileActivity.this, buyPremiumActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                        premiumIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                        startActivity(premiumIntent);
                    }
                });
            }
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("null".equals(name)){
                    Intent signInIntent = new Intent(profileActivity.this, signInActivity.class);
                    signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(signInIntent);
                } else {
                    Toast.makeText(profileActivity.this, "Signed out.", Toast.LENGTH_SHORT).show();

                    FirstTimeChecker.setFirstTime(true);
                    SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE);
                    SharedPreferences.Editor editor = choices.edit();

                    editor.putString("name", "null"); // Resetting the values of the items in the shared preferences
                    editor.putString("email", "null");
                    editor.putString("birth", "");
                    editor.putString("premiumstatus", "basic");

                    editor.apply();  // Apply changes asynchronously

                    Intent welcomeIntent = new Intent(profileActivity.this, MainActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                    welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                    startActivity(welcomeIntent);
                }
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(profileActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(profileActivity.this, reportActivity.class);
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(profileActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

    }
}