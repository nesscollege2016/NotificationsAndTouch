package tomerbu.edu.notificationsandtouch;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by dev on 9/22/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private String TAG = "Ness";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG, "MessageReceived");
    }
}
