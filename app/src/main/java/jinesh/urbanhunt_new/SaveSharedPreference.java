package jinesh.urbanhunt_new;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jinesh on 15/03/16.
 */
public class SaveSharedPreference {

    static final String USER_ACCESS_TOKEN = "access_token";
    static final String USER_EMAIL = "user_email";
    static final String USER_REFERRAL_CODE = "referral_code";



    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setFBUserAccessToken(Context ctx, String access_token)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_ACCESS_TOKEN, access_token);
        editor.commit();
    }

    public static String getFBUserAccessToken(Context ctx)
    {
        return getSharedPreferences(ctx).getString(USER_ACCESS_TOKEN, "missing");
    }

    public static void removeFBUserAccessToken(Context ctx){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_ACCESS_TOKEN, "missing");;
        editor.commit();
    }


    public static void removeSharedPreference(Context ctx){

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();

    }

    public static void setUserEmail(Context ctx, String user_email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_EMAIL, user_email);
        editor.commit();
    }

    public static String getUserEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(USER_EMAIL, "missing");
    }

    public static void setUserReferralCode(Context ctx, String user_referral_code)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_REFERRAL_CODE, user_referral_code);
        editor.commit();
    }

    public static String getUserReferralCode(Context ctx)
    {
        return getSharedPreferences(ctx).getString(USER_REFERRAL_CODE, "missing");
    }
}
