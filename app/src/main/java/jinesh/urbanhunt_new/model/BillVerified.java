package jinesh.urbanhunt_new.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jinesh on 09/04/16.
 */
public class BillVerified {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("bill_amount")
    @Expose
    private Integer billAmount;
    @SerializedName("verified_date")
    @Expose
    private String verifiedDate;

    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The billAmount
     */
    public Integer getBillAmount() {
        return billAmount;
    }

    /**
     *
     * @param billAmount
     *     The bill_amount
     */
    public void setBillAmount(Integer billAmount) {
        this.billAmount = billAmount;
    }

    /**
     *
     * @return
     *     The verifiedDate
     */
    public String getVerifiedDate() {
        return verifiedDate;
    }

    /**
     *
     * @param verifiedDate
     *     The verified_date
     */
    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }
}
