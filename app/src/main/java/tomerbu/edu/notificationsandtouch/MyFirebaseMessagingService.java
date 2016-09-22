package tomerbu.edu.notificationsandtouch;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by dev on 9/22/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private String TAG = "Ness";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "MessageReceived");


        Map<String, String> data = remoteMessage.getData();
        String message = data.get("messagses");
        String title = data.get("Title");
        String icon = data.get("icon");

        sendNotification(title, message, icon);

    }

    private void sendNotification(final String title, final String message, String icon) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Notification notification = builder.build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

        notificationManagerCompat.notify(10, notification);


    }
}
