package jinesh.urbanhunt_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Jinesh on 17/03/16.
 */
public class LoginPreferenceManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

//        FacebookSdk.sdkInitialize(getApplicationContext());

//        SaveSharedPreference s = new SaveSharedPreference();



        if(SaveSharedPreference.getFBUserAccessToken(LoginPreferenceManagerActivity.this).equals("missing") ){

            Log.d("LoggedIn","false");

            Intent a = new Intent(LoginPreferenceManagerActivity.this,FacebookLogin.class);
            startActivity(a);


        }
        else{

            Log.d("Already Logged In", "true");

            Log.d("Access_token",SaveSharedPreference.getFBUserAccessToken(LoginPreferenceManagerActivity.this));

//            Intent i = new Intent(LoginPreferenceManagerActivity.this,LogoutActivity.class);
//            Intent i = new Intent(LoginPreferenceManagerActivity.this,StoreDetailActivity.class);
            Intent i = new Intent(LoginPreferenceManagerActivity.this,LocationTestActivity.class);
            startActivity(i);


        }


//        if(AccessToken.getCurrentAccessToken() != null){
//
//            Log.d("Access_token",AccessToken.getCurrentAccessToken().getToken());
//
//            Intent i = new Intent(LoginPreferenceManagerActivity.this,LogoutActivity.class);
//            startActivity(i);
//        }
//        else{
//
//            Intent a = new Intent(LoginPreferenceManagerActivity.this,FacebookLogin.class);
//            startActivity(a);
//        }
//
//        SharedPreferences settings=getSharedPreferences("prefs",0);
//        boolean firstRun=settings.getBoolean("firstRun",false);
//        if(firstRun==false)//if running for first time
//        //Splash will load for first time
//        {
//            SharedPreferences.Editor editor=settings.edit();
//            editor.putBoolean("firstRun",true);
//            editor.apply();
//            Intent i=new Intent(LoginPreferenceManagerActivity.this,IntroActivity.class);
//            startActivity(i);
//        }
//        else
//        {
//            Intent a=new Intent(PreferenceManagerActivity.this,MainActivity.class);
//            startActivity(a);
//            finish();
//        }
    }
}
