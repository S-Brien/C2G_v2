package com.example.myapplication2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Context context;

    public static final String USER_DATABASE = "User-Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        //change action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        Button changeLang = findViewById(R.id.changeMyLang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

        Button profileTestButton = findViewById(R.id.profileTestButton);
        profileTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), ProfilePage.class));
            }
        });



        TextView textView = (TextView)findViewById(R.id.signTxt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });
        TextView textV = (TextView)findViewById(R.id.helpTxt);
        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });


    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"français", "हिंदी", "اردو", "Deutsche", "Português" , "中文", "English"};
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



}


