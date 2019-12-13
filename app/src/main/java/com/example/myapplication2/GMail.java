package com.example.myapplication2;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMail {

    // I got the details for this method online. I used resources through stackoverflow.com to create this class

    final String emailPort = "587"; // this is gMail's smtp port number
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";

    String fromEmail;
    String fromPassword;
    List<String> toEmailList;
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    // this will be the constructor of the email
    public GMail(String fromEmail, String fromPassword, List<String> toEmailList, String emailSubject, String emailBody)
    {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        //setting the server settings for Gmail
        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        Log.i("Gmail", "Mail server properties are now set.");

    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException{

            mailSession = Session.getDefaultInstance(emailProperties, null);
            emailMessage = new MimeMessage(mailSession);

            emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));//address setup
            for(String toEmail : toEmailList){
                Log.i("GMail", "toEmail" + toEmail);
                emailMessage.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(toEmail));
            }

            emailMessage.setSubject(emailSubject); //email Subject
            emailMessage.setContent(emailBody, "text/html");
            Log.i("Gmail", "Email message created.");
            return emailMessage;
    }

    public void sendEmail() throws AddressException, MessagingException
    {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        Log.i("Gmail", "allrecipients: " + emailMessage.getAllRecipients());
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        Log.i("Gmail", "Your Email has successfully been sent.");
    }



}
