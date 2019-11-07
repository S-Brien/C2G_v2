package com.example.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    Intent intent;
    TextView bio;
    TextView usernameTest, usernameLabel, emailLabel, passwordLabel;
    EditText editBio, editUsernameField, editEmailField, editPasswordField;

    ArrayList<User> userArray = SignUp.userArray;
    int userID = SignUp.userID;
    User user;
    String userUsername;
    String userEmail;



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

        userUsername = getUserUsername();

        usernameLabel = findViewById(R.id.editUsernameLabel);
        usernameLabel.setText(userUsername);

        userEmail = getUserEmail();

        emailLabel = findViewById(R.id.emailGoesHere);
        emailLabel.setText(userEmail);




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

    public String getUserUsername(){

        String username;
        user = userArray.get(userID - 1);
        username = user.getUsername();
        return username;

    }

    public String getUserEmail(){

        String email;
        user = userArray.get(userID - 1);
        email = user.getEmail();
        return email;

    }

    public String getUserPassword(){

        String password;
        user = userArray.get(userID - 1);
        password = user.getPassword();
        return password;

    }


}
