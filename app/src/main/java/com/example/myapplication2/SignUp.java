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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity {

    static ArrayList userArray;
    DatabaseManager myDB;
    EditText editUsername, editEmail, editPassword;
    Button addDataButton;
    ProfilePage profile;
    public static int USER_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDB = new DatabaseManager(this);


        editUsername = (EditText)findViewById(R.id.usernameField);
        editEmail = (EditText)findViewById(R.id.emailField);
        editPassword = (EditText)findViewById(R.id.passwordField);

        addDataButton = (Button)findViewById(R.id.signupButton);
        addData();

        addDataButton = (Button)findViewById(R.id.signupButton);

        TextView textV2 = (TextView)findViewById(R.id.helpTxt1);

        //This should have it's own method, like the addData() method
        textV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HelpPage.class));
            }
        });
        //
    }

    public void addData() {
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Boolean possibly causing non new user
                myDB.insertData(editUsername.getText().toString(),
                                                     editEmail.getText().toString(),
                                                     editPassword.getText().toString() );



                /*if (isInserted) {
                    Toast.makeText(SignUp.this, "Account created!", Toast.LENGTH_LONG).show();
                    //generateUserID();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }
                else {
                    Toast.makeText(SignUp.this, "Failed to create account.", Toast.LENGTH_LONG).show();
                }*/

            }
        });

    }

    private void showMessage(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);

    }




    /*public void generateUserID(){

        //Res is ID of the new customer

        Cursor res = myDB.getInfo();
        USER_ID = res.getInt(0) - 1;
        System.out.println("UserID = " + USER_ID);

        //Compare method to ListingsPage method
        //Try creating and returning the user ArrayList in the database manager class like the listings.
        //*/

    }
