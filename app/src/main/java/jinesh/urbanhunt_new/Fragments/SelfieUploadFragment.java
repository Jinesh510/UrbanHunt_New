package jinesh.urbanhunt_new.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import jinesh.urbanhunt_new.Dummy;
import jinesh.urbanhunt_new.MainActivity;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by Jinesh on 10/04/16.
 */
public class SelfieUploadFragment extends Fragment {

    ImageView mSelfieIV;
    EditText mStatusUpdateEt;
    Button mPickImageBtn;
    Button mUploadImageBtn;

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final String photoFileName = System.currentTimeMillis() + ".jpg" ;
    public final String APP_TAG = "UHApp";

    public static PhotoBooleanFragment newInstance() {
        PhotoBooleanFragment fragment = new PhotoBooleanFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.selfie_upload_frag, container, false);

        mSelfieIV = (ImageView)view.findViewById(R.id.selfieImage);
        mStatusUpdateEt = (EditText)view.findViewById(R.id.statusUpdate);
        mPickImageBtn = (Button)view.findViewById(R.id.pickImageBtn);
        mUploadImageBtn = (Button)view.findViewById(R.id.selfieUploadBtn);


        mPickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                selectImage();
                onLaunchCamera();
            }
        });

        mUploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String token = "Token ";
                final String access_token = SaveSharedPreference.getFBUserAccessToken(getActivity());
                String access_token_wo_quotes = access_token.replace("\"", "");
                final String s = token.concat(access_token_wo_quotes);


                TypedFile typedFile = new TypedFile("multipart/form-data", new File((getPhotoFileUri(photoFileName).getPath())));

                //Upload Bill
                RestClient.get().uploadBill(s, typedFile, new Callback<Dummy>() {
                    @Override
                    public void success(Dummy dummy, Response response) {

                        String a = "Uploaded";
                        Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(getActivity(), "Failed to Upload", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });



        return view;
    }

    public void onLaunchCamera() {

        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    public Uri getPhotoFileUri(String fileName) {
        // Only continue if the SD Card is mounted
        if (isExternalStorageAvailable()) {
            // Get safe storage directory for photos
            // Use `getExternalFilesDir` on Context to access package-specific directories.
            // This way, we don't need to request external read/write runtime permissions.
            File mediaStorageDir = new File(
                    getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){

                Log.d(APP_TAG, "failed to create directory");
            }

            // Return the file target for the photo based on filename
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    // Returns true if external storage for photos is available
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());

                // Load the taken image into a preview
                mSelfieIV.setImageBitmap(takenImage);

                mPickImageBtn.setVisibility(View.INVISIBLE);

                mUploadImageBtn.setVisibility(View.VISIBLE);


            } else { // Result was a failure
                Toast.makeText(getActivity(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
