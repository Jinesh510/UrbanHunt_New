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



//        FocusSdk.initialize(getApplicationContext());
//
//        FocusSdkState state = FocusSdk.getSetupState(getApplicationContext());
//
//        switch(state) {
//            case STARTING_UP:
//                Log.d("Starting up","true");
//
//            //This indicates that the SDK is starting up and has not yet contacted the server. It cannot execute any server related requests at this point of time
//
//            case PERMISSION_UNAVAILABLE:
//                //This indicates that the permissions required to initialize the SDK are not provided
//                Log.d("permission_unavailable","true");
//
//            case INVALID_CONFIGURATION:
//                //This indicates that SDK is configured improperly i.e., setting up client-identification key and environment in the manifest
//                Log.d("invalid_config","true");
//
//
//            case SETUP_VERIFIED:
//                //This indicates that the SDK has established the validatity of the client. This means that the client key has been verified by the server. It can now execute any api calls that are requested
//                Log.d("setup_verified","true");
//
//            case SCHEDULERS_RUNNING:
//                //This indicates that the SDK has initiated background tracking on the current device, if background tracking is enabled. If background tracking is not enabled, this is a transient state and does not imply that background tracking is in progress
//                Log.d("schedulers_running","true");
//
//            case ALL_OK:
//                //This indicates that the SDK has finished with its initialization cycle and is functioning in its full capacity
//                Log.d("all_ok","true");
//
//        }



    }
}
