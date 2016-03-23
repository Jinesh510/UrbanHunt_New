package jinesh.urbanhunt_new.API;

import com.google.gson.JsonObject;

import java.util.Map;

import jinesh.urbanhunt_new.Dummy;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by Jinesh on 04/03/16.
 */
public interface uhapi {

//    @POST("/register/{}")
//    public void createUser(@Query("email") String email,@Query("user_type") String user_type,
//                           @Query("password") String password, Callback<Dummy> Response);

//    @POST("/register/")
//    public void createNewUser(@Body User user,Callback<Dummy> Response);

    @FormUrlEncoded
    @POST("/auth/register/")
    public void createUserNew(@FieldMap Map<String,String> params, Callback<Dummy> Response);

    @FormUrlEncoded
    @POST("/auth/login/")
    public void loginUser(@FieldMap Map<String,String> params,Callback<JsonObject> Response);

//    @FormUrlEncoded
//    @POST("/convert-token/")
//    public void facebookLoginUser(@FieldMap Map<String,String> params,Callback<JsonObject> Response);

//    @FormUrlEncoded
//    @POST("/invalidate-sessions/")
//    public void faceboookLogoutUser(@Header("Authorization") String auth_token,@Field("client_id") String client_id,
//                                    Callback<Dummy> Response);
//
//
    @FormUrlEncoded
    @POST("/myuser/convert-token/")
    public void facebookLoginUser(@FieldMap Map<String,String> params,Callback<JsonObject> Response);


    @FormUrlEncoded
    @POST("/auth1/logout/")
    public void faceboookLogoutUser(@Field("client_id") String client_id,
                                    Callback<Dummy> Response);


    @Multipart
    @POST("/user_profile/upload/")
    public void uploadBill(@Header("Authorization") String auth_token,@Part("image") TypedFile image,Callback<Dummy> Response );

}
