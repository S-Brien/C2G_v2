package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class HelpPage extends AppCompatActivity {

    /* I got the details for this method online. I used resources through stackoverflow.com to create this class
      I used the obover to help with the grabbing of the email, name and message and to send it on to the GMail.java class
      And also onto the SendMailTask.java and finally onto an actual gmail account. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page2);

        final EditText mail1 = findViewById(R.id.email1);
        final EditText mail2 = findViewById(R.id.email2);
        final EditText nameIn = findViewById(R.id.hName1);
        final Button btnSub = findViewById(R.id.helpBtn);
        final EditText msgIn = findViewById(R.id.hMsg);
        final String password = "Teampj2019";
        final String sendemail = "come2gopj@gmail.com";
        final String recemail = "c2grecieve@gmail.com";
        final boolean connt;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            // Connection affirmative:)
            connt = true;
        }
        else {
            connt = false;
            Toast.makeText(getApplicationContext(), "You're not connected to the internet",
                    Toast.LENGTH_LONG).show();
        }
        btnSub.setOnClickListener(
                arg0 -> {
                    //method to make sure all fields are checked
                    if(nameIn.getText().toString().equals("")||mail1.getText().toString().equals("")||mail2.getText().toString().equals("")||msgIn.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please check you've filled all fields.",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(connt!=true){
                        Toast.makeText(getApplicationContext(), "You're internet connection needs to be checked",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(mail1.getText().toString().equals(mail2.getText().toString())){
                            Log.i("SendMailActivity", "Send Button Clicked.");
                            //declaring the recieving, password and sending of the email
                            List<String> toEmailList = Arrays.asList(recemail.split("\\s*,\\s*")); //Recipient List
                            Log.i("SendMailActivity", "To List: " + toEmailList);
                            String emailSubject = ((EditText) findViewById(R.id.hName1)).getText().toString();
                            String emailEmail = ((EditText) findViewById(R.id.email1)).getText().toString();
                            String emailBody = "User's Name : " + ((EditText) findViewById(R.id.hName1)).getText().toString() + "\n" + "User's Email : " + ((EditText) findViewById(R.id.email1)).getText().toString() + "\n" +
                                    "User's message: "+((EditText) findViewById(R.id.hMsg)).getText().toString();
                            new SendMailTask(HelpPage.this).execute(sendemail, password,toEmailList,emailSubject,emailBody,emailEmail);//send the email with all the relevant data included
                            startActivity(new Intent(getApplicationContext(),AfterMail.class)); //this will start the next activity that i have included after the mail is sent
                        }
                        else{//method for checking the email inputs
                            Toast.makeText(getApplicationContext(), "Your emails don't match please try again.",
                                    Toast.LENGTH_LONG).show();
                        }


                    }

                });

    }


}
