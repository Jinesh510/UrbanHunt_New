package jinesh.urbanhunt_new;

import android.os.Bundle;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Jinesh on 21/03/16.
 */
public class MyGCMListenerService extends GcmListenerService {


    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
    }
}
