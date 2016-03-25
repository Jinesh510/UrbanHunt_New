package jinesh.urbanhunt_new;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by Jinesh on 22/03/16.
 */
public class StoreDetailActivity extends AppCompatActivity {

    Button billUploadBtn;
    ImageView billImage;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public final String photoFileName = System.currentTimeMillis() + ".jpg" ;
    public final String APP_TAG = "UHApp";
    Button pickImageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail);

        billImage = (ImageView)findViewById(R.id.billImage);
        pickImageBtn = (Button)findViewById(R.id.pickImageBtn);
        billUploadBtn = (Button)findViewById(R.id.billUploadBtn);


        pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                selectImage();
                onLaunchCamera();
            }
        });

        billUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String token = "Token ";
                final String access_token = SaveSharedPreference.getFBUserAccessToken(StoreDetailActivity.this);
                String access_token_wo_quotes = access_token.replace("\"","");
                final String s = token.concat(access_token_wo_quotes);



                TypedFile typedFile = new TypedFile("multipart/form-data",new File((getPhotoFileUri(photoFileName).getPath())));

                //Upload Bill
                RestClient.get().uploadBill(s, typedFile, new Callback<Dummy>() {
                    @Override
                    public void success(Dummy dummy, Response response) {

                        String a = "Uploaded";
                        Toast.makeText(StoreDetailActivity.this,a,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Toast.makeText(StoreDetailActivity.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }

    public void onLaunchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
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
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

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
            if (resultCode == RESULT_OK) {
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());

                // Load the taken image into a preview
                billImage.setImageBitmap(takenImage);

                pickImageBtn.setVisibility(View.INVISIBLE);

                billUploadBtn.setVisibility(View.VISIBLE);


            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    public void selectImage(){
//
//        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if(items[i].equals("Take Photo")) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, 1);
//                }
//                else if(items[i].equals("Choose from Gallery")){
////                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    intent.setType("image/*");
//                    startActivityForResult(Intent.createChooser(intent,"Select File"),2);
//
//                } else if (items[i].equals("Cancel")){
//                    dialogInterface.dismiss();
//                }
//
//
//            }
//        });
//
//        builder.show();
//    }
//
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//        if(resultCode == this.RESULT_OK) {
//            if(requestCode == 1){
//                Bitmap bp = (Bitmap) data.getExtras().get("data");
//
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                bp.compress(Bitmap.CompressFormat.JPEG,40,bytes);
//
//                File destination = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
//
//
//                FileOutputStream fo;
//                try {
//                    destination.createNewFile();
//                    fo = new FileOutputStream(destination);
//                    fo.write(bytes.toByteArray());
//                    fo.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//
//                byte[] imgByteArray = bytes.toByteArray();
//
//                imgByte = imgByteArray;
//
//
//
//                //  ImagePath = destination;
//
//                billImage.setImageBitmap(bp);
//
//            }
//            else if (requestCode == 2){
//                Uri selectedImageUri = data.getData();
////                String[] projection = {MediaStore.MediaColumns.DATA};
////                Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
////                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
////                cursor.moveToFirst();
////
////
////                String selectedImagePath = cursor.getString(column_index);
////
////                cursor.close();
//
//                //trial
//                try {
//                    Bitmap bp1= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
//
//                    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
//                    bp1.compress(Bitmap.CompressFormat.JPEG,40,bytes1);
//                    imgByte = bytes1.toByteArray();
//
//                    billImage.setImageBitmap(bp1);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//
//                //ends
////                Bitmap bp;
////                BitmapFactory.Options options = new BitmapFactory.Options();
////                bp = BitmapFactory.decodeFile(selectedImagePath, options);
////
////                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
////                bp.compress(Bitmap.CompressFormat.JPEG,90,bytes);
////                imgByte = bytes.toByteArray();
////
////
////
//////                File destination = new File(selectedImagePath);
//////
//////                ImagePath = destination;
////
////                iv.setImageBitmap(bp);
//
//
//
//            }
//        }
//
//
//
//    }

}
