package com.example.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import static com.example.myapplication2.FullListingInfo.applicationList;
import static com.example.myapplication2.MainActivity.CUSTOMER_USERNAME;
import static com.example.myapplication2.ListingsPage.LISTING_ID;

public class ProfilePage extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    Intent intent;
    TextView bio;
    TextView usernameTest, usernameLabel, emailLabel, passwordLabel, editBalance;
    EditText editBio, editUsernameField, editEmailField, editPasswordField;
    ListView applicationListView;





    int userID;
    User user;
    String userUsername;
    String userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        displayUserInfo();
        fillApplicationList();
        applicationListView.setVisibility(View.VISIBLE);



        userID = getUserID();

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





        TextView textV = (TextView)findViewById(R.id.helpTxt);
        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), HelpPage.class));
            }
        });

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

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            ImageView avatar = findViewById(R.id.avatar);
            Uri fullPhotoUri = data.getData();
            avatar.setImageURI(fullPhotoUri);
        }
    }

    public String getUserUsername(){

        String username;
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<User>  userArrayList = dbm.generateUserArray();
        for(User u : userArrayList){
            if(CUSTOMER_USERNAME.equals(u.getUsername())){
                user = u;
                break;
            }
        }

        username = user.getUsername();
        return username;

    }

    public String getUserEmail(){

        String email;
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<User>  userArrayList = dbm.generateUserArray();
        for(User u : userArrayList){
            if(CUSTOMER_USERNAME.equals(u.getUsername())){
               user = u;
               break;
            }
        }

        email = user.getEmail();
        return email;

    }

    public String getUserPassword(){

        String password;
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<User>  userArrayList = dbm.generateUserArray();
        for(User u : userArrayList){
            if(CUSTOMER_USERNAME.equals(u.getUsername())){
                user = u;
                break;
            }
        }

        password = user.getPassword();
        return password;

    }

    public Double getUserBalance(){

        double balance;
        DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<User>  userArrayList = dbm.generateUserArray();
        for(User u : userArrayList){
            if(CUSTOMER_USERNAME.equals(u.getUsername())){
                user = u;
                break;
            }
        }

        balance = user.getBalance();
        return balance;

    }

    public int getUserID(){

        int id;
        DatabaseManager dbm = new DatabaseManager(this);
        Cursor result = dbm.getID();

        return result.getInt(0) - 1;
    }

    public void displayUserInfo(){

        usernameLabel = findViewById(R.id.editUsernameLabel);
        usernameLabel.setText(getUserUsername());

        emailLabel = findViewById(R.id.emailGoesHere);
        emailLabel.setText(getUserEmail());

        editBalance = findViewById(R.id.editBalance);
        editBalance.setText(getUserBalance().toString());

    }

    public void fillApplicationList(){

        applicationListView = findViewById(R.id.applicationListView);
        applicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Listing listing = applicationList.get(position);
                LISTING_ID = listing.getId() - 1;
                startActivity(new Intent(getApplicationContext(), FullListingInfo.class));
            }
        });
        CustomAdapter myAdapter = new CustomAdapter(applicationList, this);
        applicationListView.setAdapter(myAdapter);
    }

}
