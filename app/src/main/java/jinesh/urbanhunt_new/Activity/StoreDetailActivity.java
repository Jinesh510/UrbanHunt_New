package jinesh.urbanhunt_new.Activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.focus.android.sdk.FocusSdk;
import com.focus.android.sdk.services.exceptions.FocusSdkInitializationException;

import jinesh.urbanhunt_new.BillUploadActivity;
import jinesh.urbanhunt_new.LocationInterface;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Brands;
import jinesh.urbanhunt_new.model.Stores;

/**
 * Created by Jinesh on 02/04/16.
 */
public class StoreDetailActivity extends AppCompatActivity implements LocationInterface {

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
    boolean atLocation;


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

//        Picasso.with(this).load(mStor)

        mStoreLatitude = mStore.getLatitude();
        mStoreLongitude = mStore.getLongitude();
        mStoreLocation = new Location("Store_Loc");

        mStoreLocation.setLatitude(mStoreLatitude);
        mStoreLocation.setLongitude(mStoreLongitude);

        mCurrentLocation = new Location("Current_Loc");

        try {
            FocusSdk.getInstance().predictLocation();
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


    @Override
    public void OnDataReceive(float mLat, float mLang) {

        //sets current latitude and longitude

        mCurrentLatitude = mLat;
        mCurrentLongitude = mLang;

        mCurrentLocation.setLatitude(mCurrentLatitude);
        mCurrentLocation.setLongitude(mCurrentLongitude);

        checkIfAtLocation(mCurrentLocation, mStoreLocation);
    }

    private void checkIfAtLocation(Location from, Location to) {

//        float dist = (float) Math.sqrt(Math.pow((mCurrentLatitude - mStoreLatitude),2) +
//                Math.pow((mCurrentLongitude - mStoreLongitude),2));

        float distance = from.distanceTo(to);
        if(distance < 200){

            IsAtLocation(true);
        }
        else{

        }

    }

    @Override
    public void IsAtLocation(boolean a) {

        atLocation = a;

        if(atLocation){
            Snackbar.make(mRelativeLayout,"You are at the outlet",Snackbar.LENGTH_LONG).show();

            //Make Bill Upload Btn Visible i.e set overlay == invisible
        }else{


        }


    }
}
