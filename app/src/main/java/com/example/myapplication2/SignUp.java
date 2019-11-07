package com.example.myapplication2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    static ArrayList userArray;
    DatabaseManager myDB;
    EditText editUsername, editEmail, editPassword;
    Button addDataButton;
    ProfilePage profile;
    User user;
    static int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDB = new DatabaseManager(this);

        userArray = new ArrayList<User>();
        profile = new ProfilePage();
        editUsername = (EditText)findViewById(R.id.usernameField);
        editEmail = (EditText)findViewById(R.id.emailField);
        editPassword = (EditText)findViewById(R.id.passwordField);

        addDataButton = (Button)findViewById(R.id.signupButton);
        addData();

}

    public void addData() {
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(editUsername.getText().toString(),
                                                     editEmail.getText().toString(),
                                                     editPassword.getText().toString() );

                //Res is ID of the new customer
                Cursor res = myDB.getInfo();

                userID = res.getInt(0);
                System.out.println("UserID = " + userID);
                user = new User(userID,
                                editUsername.getText().toString(),
                                editEmail.getText().toString(),
                                editPassword.getText().toString() );


                userArray.add(user);

                if (isInserted) {
                    Toast.makeText(SignUp.this, "Account created!", Toast.LENGTH_LONG).show();



                    startActivity(new Intent(getApplicationContext(), ProfilePage.class));
                }
                else {
                    Toast.makeText(SignUp.this, "Failed to create account.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);

    }

}
