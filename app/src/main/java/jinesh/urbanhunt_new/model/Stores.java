package jinesh.urbanhunt_new.model;

/**
 * Created by Jinesh on 27/03/16.
 */
public class Stores {

    String store;
    float latitude;
    float longitude;

    String address;
    int contact_number;


    String discount;
    int id;

    public Stores(int store_id, String store, float latitude, float longitude, String address, int contact_number, String discount) {
        this.id = store_id;
        this.store = store;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.contact_number = contact_number;
        this.discount = discount;
    }


    public int getId() {
        return id;
    }


    public String getStore() {
        return store;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public int getContact_number() {
        return contact_number;
    }

    public String getDiscount() {
        return discount;
    }





}
