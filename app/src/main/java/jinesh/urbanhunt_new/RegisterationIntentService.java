package jinesh.urbanhunt_new;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import jinesh.urbanhunt_new.model.Device;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 22/03/16.
 */
public class RegisterationIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    // abbreviated tag name
    private static final String TAG = "RegIntentService";

//    public RegisterationIntentService(String name) {
//        super(name);
//    }


    public RegisterationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_SenderId);
        try {
            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);

            // pass along this data
            String i = instanceID.getId();
            sendRegistrationToServer(i,token);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void sendRegistrationToServer(String instance_id ,String reg_token) {


        final String token = "Token ";

        final String access_token = SaveSharedPreference.getFBUserAccessToken(this);

        String access_token_wo_quotes = access_token.replace("\"","");

        final String s = token.concat(access_token_wo_quotes);

        String content_type = "application/json";

//        HashMap<String,String> map = new HashMap<String, String>();
//        map.put("dev_id",instance_id);
//        map.put("reg_id",reg_token);
//        map.put("name",access_token_wo_quotes);

        Device device = new Device(instance_id,reg_token,access_token_wo_quotes);

        RestClient.get().registerDevice(s, content_type, device,
                new Callback<Dummy>() {
                    @Override
                    public void success(Dummy dummy, Response response) {

                        Log.d("Device Registered","true");

                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Log.d("Device Registered","false");


                    }
                });


    }
}
