package com.example.workoutcompanion;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class buyPremiumActivity extends AppCompatActivity {

    private EditText editTextDate; // Declaring the editText beforehand so it can be used in another function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_premium);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        EditText editTextCard = findViewById(R.id.editTextCard);
        EditText editTextAddress = findViewById(R.id.editAddress);
        EditText editTextCVV = findViewById(R.id.editCVV);
        EditText editTextCity = findViewById(R.id.editCityName);

        Button complete = findViewById(R.id.completeSignUp);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNum = editTextCard.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String cvv = editTextCVV.getText().toString().trim();
                String city = editTextCity.getText().toString().trim();

                if (cardNum.isEmpty() || address.isEmpty() || cvv.isEmpty() || city.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else if (!cardNum.matches("\\d{16}")) { // "\\d" is a special expression that only accounts for numbers, while {16} ensures that the string is 16 characters long.
                    Toast.makeText(getApplicationContext(), "The card number must be exactly 16 digits and only contain numbers.", Toast.LENGTH_SHORT).show();
                } else if (!cvv.matches("\\d{3}")) {
                    Toast.makeText(getApplicationContext(), "The CVV must be the last 3 digits of your card and only contain numbers.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Premium Membership has been purchased.", Toast.LENGTH_SHORT).show();
                    SharedPreferences choices = getSharedPreferences("userChoices", MODE_PRIVATE);
                    SharedPreferences.Editor editor = choices.edit();
                    editor.putString("premiumstatus", "premium");

                    editor.apply();  // Apply changes asynchronously
                    Intent profileIntent = new Intent(buyPremiumActivity.this, profileActivity.class);
                    profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(profileIntent);
                }
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(buyPremiumActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(buyPremiumActivity.this, reportActivity.class);
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(buyPremiumActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(buyPremiumActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });
    }
}