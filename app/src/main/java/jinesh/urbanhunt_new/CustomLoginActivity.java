package jinesh.urbanhunt_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import java.util.HashMap;

import jinesh.urbanhunt_new.API.uhapi;
import jinesh.urbanhunt_new.model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 15/03/16.
 */
public class CustomLoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    Button loginBtn;
    String API = "http://192.168.1.104:8000/";
    String strUsername;
    String strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        username = (EditText)findViewById(R.id.etUsername);
        password = (EditText)findViewById(R.id.etPassword);

        strUsername = username.getText().toString();
        strPassword = password.getText().toString();

        loginBtn = (Button)findViewById(R.id.loginBtn);
        final Context c = this;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("email",strUsername);
                Log.d("password",strPassword);

                RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).build();

                final uhapi uh = restAdapter.create(uhapi.class);

                strUsername = "jineshshah30@yahoo.co.in";
                strPassword = "abc123";

                HashMap<String,String> map = new HashMap<String, String>();
                map.put("email",strUsername);
                map.put("user_type","C");
                map.put("password",strPassword);


                User user = new User(strUsername,"C",strPassword);

//                HashMap<String,String> loginMap = new HashMap<String, String>();
//                loginMap.put("email",strUsername);
//                loginMap.put("password",strPassword);


//                uh.loginUser(loginMap, new Callback<JsonObject>() {
//                    @Override
//                    public void success(JsonObject jsonObject, Response response) {
//                        Log.d("logged-in","true");
//                        Log.d("Auth_token", String.valueOf(jsonObject.get("auth_token")));
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                    }
//                });

                uh.createUserNew(map, new Callback<Dummy>() {
                    @Override
                    public void success(Dummy dummy, Response response) {

                        Log.d("Registration_success", "successfully registered");

                        HashMap<String,String> loginMap = new HashMap<String, String>();
                        loginMap.put("email",strUsername);
                        loginMap.put("password",strPassword);

                        uh.loginUser(loginMap, new Callback<JsonObject>() {
                            @Override
                            public void success(JsonObject jsonObject, Response response) {
                                Log.d("Logged-in","true");
                                Log.d("Auth_token", String.valueOf(jsonObject.get("auth_token")));

                                String auth_token = String.valueOf(jsonObject.get("auth_token"));
                                SaveSharedPreference.setFBUserAccessToken(c,auth_token);

                                Intent i = new Intent(c,LoginActivity.class);
                                startActivity(i);
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Registration_error", "failed to register");

                    }
                });

//                uh.createNewUser(user, new Callback<Dummy>() {
//                    @Override
//                    public void success(Dummy dummy, Response response) {
//                        Log.d("Registration_success", "successfully registered");
//
//
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("Registration_error", "failed to register");
//                    }
//                });

//                uh.createUser(strUsername, "C", strPassword, new Callback<Dummy>() {
//                    @Override
//                    public void success(Dummy dummy, Response response) {
//
//                        uh.loginUser(strUsername, strPassword, new Callback<String>() {
//                            @Override
//                            public void success(String s, Response response) {
//                                Log.d("Auth_token", s);
//                                SaveSharedPreference.setUserName(getApplicationContext(), s);
//
//                                Intent i = new Intent(c, MainActivity.class);
//                                startActivity(i);
//                            }
//
//                            @Override
//                            public void failure(RetrofitError error) {
//                                Log.d("Login_error", "failed to login");
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Log.d("Registration_error", "failed to register");
//
//                    }
//                });



            }
        });



    }
}
