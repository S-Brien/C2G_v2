package com.example.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfilePage extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    public static final int EDIT_TEXT = 2;
    Intent intent;
    TextView bio, usernameLabel;
    EditText editBio;
    public static final String DATABASE_NAME = "Account Database";
    public static final String TABLE_NAME = "Account_Info";

    public static SQLiteDatabase  myDB;

    public EditText editUsername, editEmail, editPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        Button avatarUpdater = findViewById(R.id.updateAvatar);
        avatarUpdater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAvatar();
            }
        });

        Button updateBioButton = findViewById(R.id.updateBioButton);
        updateBioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBio();
            }
        });

        //editUsername = findViewById(R.id.editUsername);
        //editEmail = findViewById(R.id.editEmail);
        //editPassword = findViewById(R.id.editPassword);
        //usernameLabel = findViewById(R.id.usernameLabel);

        //addInfoToProfileFields();

    }

    protected void updateAvatar() {

        //Update Avatar
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

    }

    protected void updateBio() {

        editBio = (EditText) findViewById(R.id.editBio);
        bio = (TextView) findViewById(R.id.accountBio);
        bio.setText(editBio.getText().toString());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE) {
            ImageView avatar = findViewById(R.id.avatar);
            Uri fullPhotoUri = data.getData();
            avatar.setImageURI(fullPhotoUri);
        }
    }

    /*public void addInfoToProfileFields(){

        myDB = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        Cursor resultUsername = myDB.rawQuery("SELECT USERNAME FROM [" + TABLE_NAME + "] WHERE ID = 0", null);
        Cursor resultEmail = myDB.rawQuery("SELECT EMAIL FROM [" + TABLE_NAME + "] WHERE ID = 0", null);
        Cursor resultPassword = myDB.rawQuery("SELECT PASSWORD FROM [" + TABLE_NAME + "] WHERE ID = 0", null);
        resultUsername.moveToFirst();
        resultPassword.moveToFirst();
        resultEmail.moveToFirst();

        String username = resultUsername.getString(0);
        usernameLabel.setText(username);


    }*/

}