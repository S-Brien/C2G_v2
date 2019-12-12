package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfterMail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_mail);

        initializeButtons();
    }

    private void initializeButtons() {

        Button btnEx = (Button)findViewById(R.id.btnExit);
        btnEx.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        ActivityCompat.finishAffinity(AfterMail.this);
                    }
                });

        Button btnB = findViewById(R.id.btnP);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), ProfilePage.class));
            }
        });

    }
}
