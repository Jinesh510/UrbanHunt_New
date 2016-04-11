package jinesh.urbanhunt_new;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Jinesh on 21/03/16.
 */
public class MyGCMListenerService extends GcmListenerService {


    @Override
    public void onMessageReceived(String from, Bundle data) {

//        String message = data.getString("message");

        Log.d("message",data.toString());

        int Notification_id = data.getInt("id");

        switch (Notification_id){
            case 1:
                int bill_id = data.getInt("bill_id");


                break;
            case 2:
                break;
        }

    }
}
