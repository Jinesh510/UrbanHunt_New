package jinesh.urbanhunt_new.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jinesh on 15/03/16.
 */
public class User {

    @SerializedName("email")
    String email;

    @SerializedName("user_type")
    String user_type;

    @SerializedName("password")
    String password;

    public User(String email, String user_type, String password ) {
        this.email = email;
        this.user_type = user_type;
        this.password = password;
    }


}
