package com.example.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Boolean signUpModeActive = true;
    TextView changeSignUpModeTextView;
    DatabaseManager myDB;
    public static String CUSTOMER_USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        myDB = new DatabaseManager(this);
        initializeButtons();



    }

    private void initializeButtons() {

        //--------------PARSE SIGN-UP STUFF

        changeSignUpModeTextView = findViewById(R.id.changeSignUpModeTextView);
        changeSignUpModeTextView.setOnClickListener(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //---------------------------------



        //change action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        //-----------BUTTONS

        Button changeLang = findViewById(R.id.changeMyLang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

        TextView textV = (TextView)findViewById(R.id.helpTxt);
        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HelpPage.class));
            }
        });

        Button byPassButton = findViewById(R.id.bypassLogin);
        byPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CUSTOMER_USERNAME = "Test1";
                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });
        //---------------------------

    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"français", "हिंदी", "اردو", "Deutsche", "Português" , "中文", "Español", "عربى", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language ...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    //French
                    setLocale("fr");
                    recreate();
                }
                else if(i == 1){
                    //Hindi
                    setLocale("hi");
                    recreate();
                }
                else if(i == 2){
                    //Urdu
                    setLocale("ur");
                    recreate();
                }
                else if(i == 3){
                    //german
                    setLocale("de");
                    recreate();
                }
                else if(i == 4){
                    //german
                    setLocale("pt");
                    recreate();
                }
                else if(i == 5){
                    //chinese
                    setLocale("zh");
                    recreate();
                }
                else if(i == 6){
                    setLocale("es");
                    recreate();
                }
                else if(i == 7){
                    setLocale("ar");
                    recreate();
                }
                else if(i == 8){
                    setLocale("en");
                    recreate();
                }

                //dismiss dialog box
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();

        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();
    }

    // load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My Lang", "");
        setLocale(language);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changeSignUpModeTextView){

            Button signUpButton = findViewById(R.id.signUpButton);



            if(signUpModeActive){

                signUpModeActive = false;
                signUpButton.setText("Login");
                changeSignUpModeTextView.setText("Or, SignUp");

            }

            else{

                signUpModeActive = true;
                signUpButton.setText("SignUp");
                changeSignUpModeTextView.setText("Or, Login");

            }
        }
    }

    public void signUpClicked(View view){

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);

        if (usernameEditText.getText().toString().matches("") ||  passwordEditText.getText().toString().matches(""))
        {

            Toast.makeText(this, "A username and password are required", Toast.LENGTH_SHORT).show();


        }
        else{

            if(signUpModeActive) {

                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            CUSTOMER_USERNAME = usernameEditText.getText().toString();
                            myDB.insertAccountData(usernameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                            Log.i("SignUp", "Successful");
                            Toast.makeText(MainActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
                        } else {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
            else{


                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user !=null){
                            CUSTOMER_USERNAME = usernameEditText.getText().toString();
                            Log.i("SignUp", "Login Successful");
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                            finish();
                        }



                        else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        }
    }


}


