package jinesh.urbanhunt_new;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by Jinesh on 24/03/16.
 */
public class LocationTestActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener{


    GoogleApiClient mGoogleApiClient = null;
    private Location mLastLocation;
    private EditText mLatitudeText;
    private EditText mLongitudeText;
    LocationRequest mLocationRequest = null;
    ProgressBar pb;

    final int REQUEST_CHECK_SETTINGS = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_test);

        mLatitudeText = (EditText) findViewById(R.id.latitudeTxt);
        mLongitudeText = (EditText) findViewById(R.id.longitudeTxt);
        pb = (ProgressBar)findViewById(R.id.pb);


        if (mGoogleApiClient == null) {
            Log.d("Enable Google Client", "true");
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Log.d("Request set", "true");
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    //
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(Bundle bundle) {

        Log.d("ApiClient Connected", "true");

//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if (mLastLocation != null) {
//            Log.d("DEBUG", "current location: " + mLastLocation.toString());
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//        }


        if (mLocationRequest == null) {
            createLocationRequest();
            Log.d("location request", "not null");
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(mLocationRequest);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());


            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

                @Override
                public void onResult(LocationSettingsResult result) {

                    Log.d("result callback", "true");

                    final Status status = result.getStatus();
                    final LocationSettingsStates locationSettingsStates = result.getLocationSettingsStates();


                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            Log.d("GPS Available", "true");

                            if (ActivityCompat.checkSelfPermission(LocationTestActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationTestActivity.this, android.Manifest.
                                    permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                Log.d("Permission", "Not Granted");
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }

                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,LocationTestActivity.this);

//                            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//                            if (mLastLocation != null) {
//
//                                Log.d("DEBUG 1 ", "current location: " + mLastLocation.toString());
//
//                                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//                                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//
//                                pb.setVisibility(View.INVISIBLE);
//                                mGoogleApiClient.disconnect();
//                            }


                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            Log.d("GPS Available", "false");


                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        LocationTestActivity.this, REQUEST_CHECK_SETTINGS);

                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.

                            Log.d(" No Settings Available", "true");
                            break;
                    }
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(intent);

        Log.d("On Activity Result", "true");
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made

                        Log.d("Loc Settings Enabled", "true");
                        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) LocationTestActivity.this);
//                        pb.setVisibility(View.VISIBLE);
//                        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//                        if (mLastLocation != null) {
//                            Log.d("Fetch Lat-Lng","true");
//                            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//                            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//                        }
//                        else{
//                            Log.d("Fetch Lat-Lng","false");
//                        }
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to

                        Log.d("Loc Settings Enabled","false");

                        break;
                    default:
                        break;
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if(mLastLocation != null){
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,LocationTestActivity.this);
        }

    }
}
