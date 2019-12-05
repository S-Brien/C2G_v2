package com.example.myapplication2;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewPage extends AppCompatActivity {
    DatabaseHelper peopleDB;

    Button btnAddData, btnViewData,btnUpdateData,btnDelete;
    EditText etName,etProp,etReview,etID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_page);

        peopleDB = new DatabaseHelper(this);

        etName = (EditText) findViewById(R.id.etNewName);
        etProp = (EditText) findViewById(R.id.etNewProp);
        etReview = (EditText) findViewById(R.id.etNewReview);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnUpdateData = (Button) findViewById(R.id.btnUpdateData);
        etID = (EditText) findViewById(R.id.etID);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String email = etProp.getText().toString();
                String tvShow = etReview.getText().toString();

                boolean insertData = peopleDB.addData(name, email, tvShow);

                if (insertData == true) {
                    Toast.makeText(ReviewPage.this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ReviewPage.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData(){
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = peopleDB.showData();

                if (data.getCount() == 0) {
                    display("Error", "No Data Found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("Review ID: " + data.getString(0) + "\n");
                    buffer.append("Name: " + data.getString(1) + "\n");
                    buffer.append("Landlord Name/property: " + data.getString(2) + "\n");
                    buffer.append("review details: " + data.getString(3) + "\n");

                    display("All Stored Data:", buffer.toString());
                }
            }
        });
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if (temp > 0) {
                    boolean update = peopleDB.updateData(etID.getText().toString(), etName.getText().toString(),
                            etProp.getText().toString(), etReview.getText().toString());
                    if (update == true) {
                        Toast.makeText(ReviewPage.this, "Successfully Updated Data!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ReviewPage.this, "Something Went Wrong :(.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ReviewPage.this, "You Must Enter A review ID to Update :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = etID.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = peopleDB.deleteData(etID.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(ReviewPage.this, "Successfully Deleted The review!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ReviewPage.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ReviewPage.this, "You Must Enter An ID to Delete :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}


