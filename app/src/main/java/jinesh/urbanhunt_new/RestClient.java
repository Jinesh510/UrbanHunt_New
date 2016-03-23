package jinesh.urbanhunt_new;

import jinesh.urbanhunt_new.API.uhapi;
import retrofit.RestAdapter;

/**
 * Created by Jinesh on 23/03/16.
 */
public class RestClient {

    private static uhapi REST_CLIENT;

    private static String ROOT =
            "http://192.168.1.104:8000/";

    static {
        setupRestClient();
    }

    private RestClient() {};

    public static uhapi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).
                setEndpoint(ROOT).build();

        REST_CLIENT = restAdapter.create(uhapi.class);
    }


}
