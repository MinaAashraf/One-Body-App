package com.questionsanawya.dell.sanawyaapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by dell on 3/13/2019.
 */

public class MessegingService extends FirebaseMessagingService {
    int id  = 0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "0");
        builder.setContentTitle(title).setContentText(body).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_foreground))
                .setSmallIcon(R.drawable.team).setDefaults(Notification.DEFAULT_SOUND).setContentIntent(pendingIntent).setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(id++,builder.build());


        super.onMessageReceived(remoteMessage);
    }
}
