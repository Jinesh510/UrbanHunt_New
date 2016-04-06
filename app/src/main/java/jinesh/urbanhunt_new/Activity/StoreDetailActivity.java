package jinesh.urbanhunt_new.Activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.focus.android.sdk.FocusSdk;
import com.focus.android.sdk.services.exceptions.FocusSdkInitializationException;

import jinesh.urbanhunt_new.BillUploadActivity;
import jinesh.urbanhunt_new.LocationReceiver;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Brands;
import jinesh.urbanhunt_new.model.Stores;

/**
 * Created by Jinesh on 02/04/16.
 */
public class StoreDetailActivity extends AppCompatActivity {

    Stores mStore;
    Brands mBrand;

    ImageView mBrandImage;
    TextView mBrandName;
    Button mCheckInBtn;
    Button mBillUploadBtn;

    float mCurrentLatitude;
    float mCurrentLongitude;
    Location mCurrentLocation;

    float mStoreLatitude;
    float mStoreLongitude;
    Location mStoreLocation;

    RelativeLayout mRelativeLayout;
    boolean a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);



        mRelativeLayout = (RelativeLayout)findViewById(R.id.storeDetailLayout);

        Intent i = getIntent();
        mStore = (Stores)i.getSerializableExtra("store_object");

        mBrand = mStore.getBrand();

        mBrandImage = (ImageView)findViewById(R.id.brandImage);
        mBrandName = (TextView)findViewById(R.id.brandName);
        mCheckInBtn = (Button)findViewById(R.id.checkinBtn);
        mBillUploadBtn = (Button)findViewById(R.id.billUploadBtn);

        //make bill upload button invisible for a while

        mBillUploadBtn.setVisibility(View.INVISIBLE);

//        Picasso.with(this).load(mStor)

        mBrandName.setText(mBrand.getBrandName());

        mStoreLatitude = mStore.getLatitude();
        mStoreLongitude = mStore.getLongitude();

        Log.d("mStoreLat",mStoreLatitude + "");


//        Log.d("Store_Location", mStoreLocation.toString());

        try {
            LocationReceiver mLocationReceiver = new LocationReceiver();

            FocusSdk.getInstance().predictLocation();
            mLocationReceiver.setListener(new LocationReceiver.UHLocationListener() {
                @Override
                public void DataReceive(float mLat, float mLng) {

                    //sets current latitude and longitude
                    mCurrentLatitude = mLat;
                    mCurrentLongitude = mLng;

                    if(mCurrentLatitude != 0){
                        mCurrentLocation = new Location("");

                        Log.d("mCurrLat",mCurrentLatitude + "");
                        mCurrentLocation.setLatitude((double) mCurrentLatitude);
                        mCurrentLocation.setLongitude((double) mCurrentLongitude);

                        Log.d("Curr_Location", mCurrentLocation.toString());

                        mStoreLocation = new Location("");

                        Log.d("mStoreLat",mStoreLatitude + "");
                        mStoreLocation.setLatitude(mStoreLatitude);
                        mStoreLocation.setLongitude(mStoreLongitude);
                        Log.d("Store_Location", mStoreLocation.toString());

                    }

                    if(mCurrentLocation != null){
                        Log.d("Curr_Loc", "true");
                    }

                    if(mStoreLocation !=null){

                        Log.d("Store_Loc","true");
                        checkIfAtLocation(mCurrentLocation, mStoreLocation);
                    }



                }

                @Override
                public void isAtLocation(boolean atLocation) {

                    a = atLocation;

                    if(a){
                        Snackbar.make(mRelativeLayout, "You are at the outlet", Snackbar.LENGTH_LONG).show();

                        //Make Bill Upload Btn Visible i.e set overlay == invisible
                    }else{


                    }

                }
            });
        } catch (FocusSdkInitializationException e) {
            e.printStackTrace();
        }



        mBillUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(StoreDetailActivity.this, BillUploadActivity.class);
                startActivity(i);

            }
        });


    }


//    @Override
//    public void OnDataReceive(float mLat, float mLang) {
//
//        //sets current latitude and longitude
//
//        mCurrentLatitude = mLat;
//        mCurrentLongitude = mLang;
//
//
//        if(mCurrentLatitude != 0){
//            mCurrentLocation = new Location("");
//
//            Log.d("mCurrLat",mCurrentLatitude + "");
//            mCurrentLocation.setLatitude((double) mCurrentLatitude);
//            mCurrentLocation.setLongitude((double) mCurrentLongitude);
//
//            Log.d("Curr_Location", mCurrentLocation.toString());
//
//
//            mStoreLocation = new Location("");
//
//            Log.d("mStoreLat",mStoreLatitude + "");
//
//            mStoreLocation.setLatitude(mStoreLatitude);
//            mStoreLocation.setLongitude(mStoreLongitude);
//            Log.d("Store_Location", mStoreLocation.toString());
//
//
//        }
//
//        if(mCurrentLocation != null){
//            Log.d("Curr_Loc", "true");
//        }
//
//        if(mStoreLocation !=null){
//
//            Log.d("Store_Loc","true");
//            checkIfAtLocation(mCurrentLocation, mStoreLocation);
//
//        }
//
//    }

    private void checkIfAtLocation(Location from, Location to) {

//        float dist = (float) Math.sqrt(Math.pow((mCurrentLatitude - mStoreLatitude),2) +
//                Math.pow((mCurrentLongitude - mStoreLongitude),2));

        float distance = from.distanceTo(to);
        if(distance != 0){
            Log.d("Dist",distance + "");
        }
        if(distance < 200.00){

            Snackbar.make(mRelativeLayout, "You are at the outlet", Snackbar.LENGTH_LONG).show();
            mBillUploadBtn.setVisibility(View.VISIBLE);

        }
        else{

            Snackbar.make(mRelativeLayout, "Please visit the outlet to avail cashback", Snackbar.LENGTH_LONG).show();


        }

    }

//    @Override
//    public void IsAtLocation(boolean a) {
//
//        atLocation = a;
//
//        if(atLocation){
//            Snackbar.make(mRelativeLayout,"You are at the outlet",Snackbar.LENGTH_LONG).show();
//
//            //Make Bill Upload Btn Visible i.e set overlay == invisible
//        }else{
//
//
//        }


//    }

//    @Override
//    public void DataReceive(float mLat, float mLng) {
//
//        mCurrentLatitude = mLat;
//        mCurrentLongitude = mLng;
//
//        if(mCurrentLatitude != 0){
//            mCurrentLocation = new Location("");
//
//            Log.d("mCurrLat",mCurrentLatitude + "");
//            mCurrentLocation.setLatitude((double) mCurrentLatitude);
//            mCurrentLocation.setLongitude((double) mCurrentLongitude);
//
//            Log.d("Curr_Location", mCurrentLocation.toString());
//
//
//            mStoreLocation = new Location("");
//
//            Log.d("mStoreLat",mStoreLatitude + "");
//
//            mStoreLocation.setLatitude(mStoreLatitude);
//            mStoreLocation.setLongitude(mStoreLongitude);
//            Log.d("Store_Location", mStoreLocation.toString());
//
//
//        }
//
//        if(mCurrentLocation != null){
//            Log.d("Curr_Loc", "true");
//        }
//
//        if(mStoreLocation !=null){
//
//            Log.d("Store_Loc", "true");
////            checkIfAtLocation(mCurrentLocation, mStoreLocation);
//
//        }
//
//
//
//
//
//    }
}
