package jinesh.urbanhunt_new.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jinesh on 27/03/16.
 */
public class Brands implements Serializable{

    @SerializedName("brand_name")
    @Expose
    private String brandName;

    @SerializedName("background")
    @Expose
    private String background;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("cashback")
    @Expose
    private int cashback;


    public String getBrandName() {
        return brandName;
    }

    public String getBackground() {
        return background;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     *
     * @return
     * The cashback
     */
    public int getCashback() {
        return cashback;
    }

    /**
     *
     * @param cashback
     * The cashback
     */
    public void setCashback(int cashback) {
        this.cashback = cashback;
    }
}
