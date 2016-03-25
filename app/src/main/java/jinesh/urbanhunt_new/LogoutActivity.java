package jinesh.urbanhunt_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.LoginManager;

import jinesh.urbanhunt_new.API.uhapi;
import retrofit.Callback;
import retrofit.RequestInterceptor;
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
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.facebook_logout);

        facebookLogoutBtn = (Button)findViewById(R.id.logoutBtn);
        context = this;

//        final String a_t = SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this);
//
//        Log.d("acc_tok", a_t);
//
//        String a_t_1 = a_t.replace("\"", "");
//
//        Log.d("acc_tok_without_quotes", a_t_1);



        facebookLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this).equals("missing")){

//                    Log.d("facebook_token_1",AccessToken.getCurrentAccessToken().getToken());

                    Log.d("SharedPrefNotSaved","true");

                }
                else {

                    Log.d("Access_token_2", SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this));

                    final String token = "Token ";

                    final String access_token = SaveSharedPreference.getFBUserAccessToken(LogoutActivity.this);

                    String access_token_wo_quotes = access_token.replace("\"","");

                    final String s = token.concat(access_token_wo_quotes);

                    Log.d("merged str", s);



                    RestAdapter restAdapter = new RestAdapter.Builder().
                            setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade request) {
                            request.addHeader("Authorization",s);

//                            request.addHeader("Authorization","Token a871c374d0f008611dc1e25ee04f29d9c549ceae" );

                        }
                    }).build();

                    uhapi uh = restAdapter.create(uhapi.class);

                    uh.faceboookLogoutUser(CLIENT_ID, new Callback<Dummy>() {
                        @Override
                        public void success(Dummy dummy, Response response) {

                            Log.d("LoggedOut", "true");

                            //Remove Access_token from shared preferences
                            SaveSharedPreference.removeFBUserAccessToken(LogoutActivity.this);

                            //logout from facebook
                            LoginManager.getInstance().logOut();

                            //Move to Login Preference Activity
                            Intent i = new Intent(context,LoginPreferenceManagerActivity.class);
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
