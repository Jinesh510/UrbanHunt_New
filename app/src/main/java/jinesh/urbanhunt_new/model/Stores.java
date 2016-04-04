package jinesh.urbanhunt_new.model;

import java.io.Serializable;

/**
 * Created by Jinesh on 27/03/16.
 */
public class Stores implements Serializable {

    float latitude;
    float longitude;
    String address;
    int contact_number;
    String discount;
    int id;
    Brands brand;


    String sub_locality;


    public Stores(Brands brand, int store_id,float latitude, float longitude,String sub_locality, String address, int contact_number, String discount) {
        this.brand = brand;
        this.id = store_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sub_locality = sub_locality;
        this.address = address;
        this.contact_number = contact_number;
        this.discount = discount;
    }

    public Brands getBrand() {
        return brand;
    }

    public int getId() {
        return id;
    }



    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }


    public String getSub_locality() {
        return sub_locality;
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
