package com.maggard.notificationdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat; //make sure you are using version 7 not 4..
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;  //build the object that will be the notification itself
    private static final int uniqueID = 45123; //the notification has to be assigned a uniqueID

    //each notification you make you have to have a uniqueID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //build the new notification
        notification = new NotificationCompat.Builder(this);
        //everytime you make a notification you must set your autocancel
        notification.setAutoCancel(true); //after you click the notification it will be removed from the screen
    }

    //customize the notification
    public void onClick(View view) {
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("UPDATE!");
        notification.setContentText("New Message from Bob");

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); //create alarm sound from ringtone manager
        notification.setSound(alarmSound); //set that alarmSound to the notification

        Intent i = new Intent(this, MainActivity.class);//where do you go when you press the notification?
        //first parameter is current context, second is request code, third is the intent, last is the flag
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, i,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent); //set the pending intent to the notification

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//send out the notification
        nm.notify(uniqueID, notification.build()); //calls your notification
    }
}
