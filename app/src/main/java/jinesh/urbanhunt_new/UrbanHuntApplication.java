package jinesh.urbanhunt_new;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;

/**
 * Created by Jinesh on 17/03/16.
 */
public class UrbanHuntApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialized Facebook SDK

        FacebookSdk.sdkInitialize(getApplicationContext());

        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }


    }
}
