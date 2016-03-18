package jinesh.urbanhunt_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import jinesh.urbanhunt_new.API.uhapi;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 16/03/16.
 */
public class LogoutActivity extends AppCompatActivity {

    Button facebookLogoutBtn;

    String API = "http://192.168.1.104:8000/";
    final String CLIENT_ID = "8KHU8SRpTyG8dB0EnR8Z1Yyp5Ebo0rh0NW07uoTl";
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.facebook_logout);

        facebookLogoutBtn = (Button)findViewById(R.id.logoutBtn);

        c = this;


        facebookLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this).equals("missing")){

//                    Log.d("facebook_token_1",AccessToken.getCurrentAccessToken().getToken());

                    Log.d("SharedPrefNotSaved","true");

                }
                else {

                    Log.d("Access_token_2", SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this));

                    RestAdapter restAdapter = new RestAdapter.Builder().
                            setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).build();

                    uhapi uh = restAdapter.create(uhapi.class);

                    uh.faceboookLogoutUser("Token" + " "+ SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this),
                            CLIENT_ID, new Callback<Dummy>() {
                        @Override
                        public void success(Dummy dummy, Response response) {

                            Log.d("LoggedOut","true");
                            SaveSharedPreference.removeFBUserAccessToken(LogoutActivity.this);

                            Intent i = new Intent(c,LoginPreferenceManagerActivity.class);
                            startActivity(i);

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Log.d("LoggedOut","false");

                        }
                    });

                }

            }
        });



    }
}
