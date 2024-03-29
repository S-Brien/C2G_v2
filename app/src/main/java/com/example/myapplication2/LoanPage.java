package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import static com.example.myapplication2.MainActivity.CUSTOMER_USERNAME;


public class LoanPage extends AppCompatActivity {

    DatabaseManager myDB;
    EditText balanceRequestEditText;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_page);
        myDB = new DatabaseManager(this);
        balanceRequestEditText = findViewById(R.id.editLoan);

        initializeButtons();
    }

    private void initializeButtons() {
        Button requestLoanButton = findViewById(R.id.requestLoanButton);
        requestLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLoan();
            }
        });

        Button listingsPageButton = findViewById(R.id.listingsButton);
        listingsPageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListingsPage.class));
            }
        });

        ImageView profilePicture = findViewById(R.id.profilePicture);
        profilePicture.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfilePage.class));
            }
        });

        Button homePageButton = findViewById(R.id.homePageButton);
        homePageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });

        TextView helpPageButton = findViewById(R.id.helpTxt);
        helpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HelpPage.class));
            }
        });
    }

    public void makeLoan(){

        ArrayList<User> userArray = myDB.generateUserArray();

        Double loanAmount;
        try {
            loanAmount = Double.parseDouble(balanceRequestEditText.getText().toString());
        }catch(NumberFormatException e){
            Toast.makeText(this, "Please submit a number!", Toast.LENGTH_SHORT).show();
            return;
        }

        for(User u : userArray){
            if(CUSTOMER_USERNAME.equals(u.getUsername())){
                user = u;
                break;
            }
        }

        Toast.makeText(this, "Loan Approved!", Toast.LENGTH_SHORT).show();
        myDB.addBalance(loanAmount, user.getId());

    }
}
