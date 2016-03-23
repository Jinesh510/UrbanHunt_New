package jinesh.urbanhunt_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.JsonObject;

import java.util.HashMap;

import jinesh.urbanhunt_new.API.uhapi;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 15/03/16.
 */
public class FacebookLogin extends AppCompatActivity {

    LoginButton facebookLoginBtn;
    CallbackManager callbackManager;
    String API = "http://192.168.1.104:8000/";
//    final String CLIENT_ID = "ejIbSPISZLcilHNiRaArwKKUgTTOAeHPXXZEJqVc";
    final String CLIENT_ID = "8KHU8SRpTyG8dB0EnR8Z1Yyp5Ebo0rh0NW07uoTl";

//    final String CLIENT_SECRET = "k4135i0OLqZrquXtO4D0QC3JwMWfuRdyf0jDm1uUppM5Nnq" +
//            "3wmWelgjmBeOqzqbnsX2soXWmmJCEUArHOz0k55e8l1Kiy5QVaUgUqBrbP8Ti1Vt10FMKDy5Voq01hdZY";

    final String CLIENT_SECRET = "EFohDzJkt66HCBw2Nm4dt215B805fKDyoopGSloKaEyWRcrlvqA1cFEAHZIpbh" +
            "5C881qXTdpewE7pBoBEMmmuoaOyT7b5j2LVHtVfFbfQsyam1Hma8qct0dyvRJjQXOE";

    Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.facebook_login);

        facebookLoginBtn = (LoginButton)findViewById(R.id.login_button);
        facebookLoginBtn.setReadPermissions("email");

        c = this;
        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                // App code
                Log.d("OkBtn", "true");
//                facebookLoginBtn.setVisibility(View.INVISIBLE);


//                if(AccessToken.getCurrentAccessToken() !=null){
//
//                    Log.d("Access_token",AccessToken.getCurrentAccessToken().toString());
//
//                }


                RestAdapter restAdapter = new RestAdapter.Builder().
                        setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).build();

                uhapi uh = restAdapter.create(uhapi.class);

                HashMap<String, String> fbLoginMap = new HashMap<String, String>();

                fbLoginMap.put("grant_type", "convert_token");
                fbLoginMap.put("client_id", CLIENT_ID);
                fbLoginMap.put("client_secret", CLIENT_SECRET);
                fbLoginMap.put("backend", "facebook");
                fbLoginMap.put("token", loginResult.getAccessToken().getToken());


                uh.facebookLoginUser(fbLoginMap, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        Log.d("LoggedIn", "true");
                        Log.d("Keys", jsonObject.toString());

                        SaveSharedPreference.setFBUserAccessToken(FacebookLogin.this,jsonObject.get("token").toString());



                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            /* Create an Intent that will start the Menu-Activity. */
                                Intent mainIntent = new Intent(FacebookLogin.this, StoreDetailActivity.class);
                                FacebookLogin.this.startActivity(mainIntent);
                                FacebookLogin.this.finish();
                            }
                        }, 500);
//
//                        Intent i = new Intent(c, LogoutActivity.class);
//                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("LoggedIn", "false");

                    }
                });


            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("FacebookError", "true");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
