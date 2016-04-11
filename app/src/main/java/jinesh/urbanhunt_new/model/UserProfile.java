package jinesh.urbanhunt_new.model;

import java.io.Serializable;

/**
 * Created by Jinesh on 10/04/16.
 */
public class UserProfile implements Serializable {

    String Username;
    UserDetails Details;
    UserPoints Points;

    public String getUsername() {
        return Username;
    }

    public UserDetails getDetails() {
        return Details;
    }

    public UserPoints getPoints() {
        return Points;
    }
}
