package com.example.Come2Go;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class SendMailTask extends AsyncTask {

    // I got the details for this method online. I used resources through stackoverflow.com to create this class

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;

    public SendMailTask(Activity activity) {
        sendMailActivity = activity;
    }

    // make the method for showing dialog progress
    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Setting up..");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }
    //method for creation and sending of email
    @Override
    protected Object doInBackground(Object... args) {
        try{
            Log.i("SendMailTask", "Setting up your email ..");
            publishProgress("Processing all your information ..");
            GMail androidEmail = new GMail(args[0].toString(),
                    args[1].toString(), (List) args[2], args[3].toString(),
                    args[4].toString());
            publishProgress("Preparing your information ..");
            androidEmail.createEmailMessage();
            publishProgress("Sending your information ..");
            androidEmail.sendEmail();
            publishProgress("Information sent.");
            Log.i("SendMailTask", "Info Email sent.");
        }catch(Exception e){
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        }
        return null;
    }
    // creating method for dialog messages.
    @Override
    public void onProgressUpdate(Object... values){
        statusDialog.setMessage(values[0].toString());
    }
    //method to get rid of the dialog message.
    @Override
    public void onPostExecute(Object result){
        statusDialog.dismiss();
    }
}
