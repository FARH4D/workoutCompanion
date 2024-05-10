package com.example.workoutcompanion;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class signUpActivity extends AppCompatActivity {

    private EditText editTextDate; // Declaring the editText beforehand so it can be used in another function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Window window = getWindow(); // Gets the window instance of the activity and gets the properties of it.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // Essentially tells the system that it wants to be in charge of drawing the background for the phone's status bar, so my app can handle it (this lets me make it transparent).
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // Clears the translucency of the status bar, the clear flag function lets me remove any specific setting.
        window.setStatusBarColor(Color.TRANSPARENT); // Sets the colour of the status bar to transparent
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // This makes it so the content on the screen can extend into the status bar (so the status bar doesn't just sit on top of everything)

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextPass = findViewById(R.id.editTextPass);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        editTextDate = findViewById(R.id.editTextDate);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        Calendar today = Calendar.getInstance();
        Calendar thirteenYears = (Calendar) today.clone();
        thirteenYears.add(Calendar.YEAR, - 13);
        Button complete = findViewById(R.id.completeSignUp);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String password = editTextPass.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String birthDate = editTextDate.getText().toString().trim();
                String premiumstatus = "basic";

                if (name.isEmpty() || password.isEmpty() || email.isEmpty() || birthDate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 5) {
                    Toast.makeText(getApplicationContext(), "Password is too short.", Toast.LENGTH_SHORT).show();
                }
                else if (!email.contains("@")) {
                    Toast.makeText(getApplicationContext(), "Invalid Email.", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(name, password, email, birthDate, premiumstatus);
                }
            }
        });

        TextView homeButton = findViewById(R.id.navHome); // Gets the id of the home button on the bottom nav bar.
        homeButton.setOnClickListener(new View.OnClickListener() { // Creates an onClick listener for the home button so the application can detect when it has been clicked.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(signUpActivity.this, homeActivity.class); // Creates a new intent so the screen can be switched to the home screen.
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
                startActivity(homeIntent);
            }
        });

        TextView reportButton = findViewById(R.id.navReport);
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(signUpActivity.this, reportActivity.class);
                reportIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(reportIntent);
            }
        });

        TextView exerciseButton = findViewById(R.id.navExercises);
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exerciseIntent = new Intent(signUpActivity.this, exerciseActivity.class);
                exerciseIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(exerciseIntent);
            }
        });

        TextView profileButton = findViewById(R.id.navProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(signUpActivity.this, profileActivity.class);
                profileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(profileIntent);
            }
        });

    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);
                selectedDate.set(Calendar.HOUR_OF_DAY, 0);
                selectedDate.set(Calendar.MINUTE, 0);
                selectedDate.set(Calendar.SECOND, 0);
                selectedDate.set(Calendar.MILLISECOND, 0);

                // Calculating the date 13 years ago
                Calendar thirteenYears = Calendar.getInstance();
                thirteenYears.add(Calendar.YEAR, -13);

                if (!selectedDate.before(thirteenYears)){
                    Toast.makeText(signUpActivity.this, "You must be older than 13 to use this app.", Toast.LENGTH_LONG).show();
                    editTextDate.setText("");
                } else if (selectedDate.after(calendar)) {
                    Toast.makeText(signUpActivity.this, "The date cannot be in the future.", Toast.LENGTH_LONG).show();
                    editTextDate.setText("");
                } else {
                    editTextDate.setText(day + "/" + (month + 1) + "/" + year);
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void registerUser(String name, String password, String email, String birthDate, String premiumstatus){
        DatabaseManager dbManager = new DatabaseManager(this); // Opens an instance of the DatabaseManager class
        SQLiteDatabase db = dbManager.getWritableDatabase();

        ContentValues values = new ContentValues(); // ContentValues is a class that lets me specify key names and put in values, it kinda works like an ArrayMap
        values.put(DatabaseManager.KEY_USER_NAME, name); // Inserts the values that the user entered into the database.
        values.put(DatabaseManager.KEY_USER_PASSWORD, password);
        values.put(DatabaseManager.KEY_USER_EMAIL, email);
        values.put(DatabaseManager.KEY_USER_BIRTH, birthDate);
        values.put(DatabaseManager.KEY_USER_PREMIUM, premiumstatus);

        long id = db.insert(DatabaseManager.TABLE_USERS, null, values); // Inserts my values into the specified table
        if (id!= -1){ // If the id is not -1 then the data was successfully inserted.
            Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show(); // Makes a pop up telling the registration was successful.
            Intent signInIntent = new Intent(signUpActivity.this, signInActivity.class); // Creates a new intent so the screen can be switched to the sign in screen.
            signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Does not open a new screen, but instead opens it on the same screen.
            startActivity(signInIntent);
        } else {
            Toast.makeText(this, "The email you entered is taken. Please try another.", Toast.LENGTH_SHORT).show(); // Tells the user the email was taken.
        }
        db.close();
    }
}