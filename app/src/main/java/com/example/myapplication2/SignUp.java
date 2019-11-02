package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    DatabaseManager myDB;
    EditText editUsername, editEmail, editPassword;
    Button addDataButton;
    ProfilePage profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDB = new DatabaseManager(this);

    editUsername = (EditText)findViewById(R.id.usernameField);
    editEmail = (EditText)findViewById(R.id.emailField);
    editPassword = (EditText)findViewById(R.id.passwordField);

    addDataButton = (Button)findViewById(R.id.signupButton);
    profile = new ProfilePage();
    addData();

}

    public void addData() {
        addDataButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editUsername.getText().toString(),
                                                     editEmail.getText().toString(),
                                                     editPassword.getText().toString() );

                if (isInserted) {
                    Toast.makeText(SignUp.this, "Account created!", Toast.LENGTH_LONG).show();
                    //profile.addInfoToProfileFields();
                    startActivity(new Intent(getApplicationContext(), ProfilePage.class));

                }
                else {
                    Toast.makeText(SignUp.this, "Failed to create account.", Toast.LENGTH_LONG).show();
                }
            }
        }));

    }

}
