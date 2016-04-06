package jinesh.urbanhunt_new;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.focus.android.sdk.common.FAInfoWrapper;
import com.focus.android.sdk.common.internal.utils.StringUtils;
import com.focus.android.sdk.common.location.FAGPSData;
import com.focus.android.sdk.common.location.FALocation;
import com.focus.android.sdk.services.impl.LocationUtils;

import jinesh.urbanhunt_new.Activity.StoreDetailActivity;

/**
 * Created by Jinesh on 26/03/16.
 */
public class LocationReceiver extends BroadcastReceiver {

    public interface UHLocationListener{

        void DataReceive(float mLat,float mLng);
        void isAtLocation(boolean atLocation);
    }

    private static UHLocationListener listener;

    public void setListener(UHLocationListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        StoreDetailActivity mStoreDetailActivity = new StoreDetailActivity();


        //On Receiving the location broadcast, use following code to get the location object
        FAInfoWrapper infoWrapper = LocationUtils.getLocationObject(context, intent.getStringExtra(StringUtils.EXTRA_REQUEST_ID));
        if( infoWrapper != null ){
            Log.d("info_wrapper","true");

//            mStoreDetailActivity = new StoreDetailActivity();

            //Successful Location Prediction
            FALocation locationObject = infoWrapper.getLocationObject();
            if (locationObject.isLocationAvailable()) {
                //Now use the locationObject for location update
                Log.d("loc_obj_available","true");

                if(listener !=null){

                    listener.isAtLocation(true);
                }
//                mStoreDetailActivity.IsAtLocation(true);

            }

            else
            {
                Log.d("loc_obj_available","false");
                //Location Unavailable - FALNF
                // check if there's any GpsData
                if (locationObject.isGpsAvailable()) {
                    //Here are the gps co-ordinates.
                    FAGPSData gpsData = locationObject.getGpsData();
                    Log.d("gps_available","true");
                    Log.d("gps_data",gpsData.toString());


                    float mLat= (float)gpsData.getLatitude();
                    float mLng = (float)gpsData.getLongitude();

                    if(listener!=null)

                    {
                        listener.DataReceive(mLat,mLng);
                    }

//
//                    if(mLat != 0){
//
//                        Log.d("mLat",mLat+"");
////                        mStoreDetailActivity.OnDataReceive(mLat, mLng);
//                    }
                }
                else {
                    Log.d("gps_available","false");

                    //No GpsData - possibly you haven't opted for gps data sharing in case of FALNF.
                }
            }
        }
        else {
            Log.d("info_wrapper","false");
        //Unsuccessful Location Prediction
        // To get cause of failure
            String failureMessage = intent.getStringExtra(StringUtils. EXTRA_LOCATION_FAIL_RESPONSE);
            Log.d("fail_msg",failureMessage);

        }
    }



}
