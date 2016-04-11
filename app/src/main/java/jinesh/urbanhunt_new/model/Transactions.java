package jinesh.urbanhunt_new.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jinesh on 09/04/16.
 */
public class Transactions implements Serializable {

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("uploaded_date")
    @Expose
    private String uploadedDate;

    @SerializedName("bill_verified")
    @Expose
    private BillVerified billVerified = new BillVerified();

    @SerializedName("store")
    @Expose
    private Stores store;

    @SerializedName("verified")
    @Expose
    private Boolean verified;

    /**
     *
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return
     *     The uploadedDate
     */
    public String getUploadedDate() {
        return uploadedDate;
    }

    /**
     *
     * @param uploadedDate
     *     The uploaded_date
     */
    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    /**
     *
     * @return
     *     The billVerified
     */
    public BillVerified getBillVerified() {
        return billVerified;
    }

    /**
     *
     * @param billVerified
     *     The bill_verified
     */
    public void setBillVerified(BillVerified billVerified) {
        this.billVerified = billVerified;
    }

    /**
     *
     * @return
     *     The store
     */
    public Stores getStore() {
        return store;
    }

    /**
     *
     * @param store
     *     The store
     */
    public void setStore(Stores store) {
        this.store = store;
    }

    /**
     *
     * @return
     *     The verified
     */
    public Boolean getVerified() {
        return verified;
    }

    /**
     *
     * @param verified
     *     The verified
     */
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
