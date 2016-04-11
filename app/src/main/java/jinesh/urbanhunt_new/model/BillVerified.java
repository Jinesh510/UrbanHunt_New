package jinesh.urbanhunt_new.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jinesh on 09/04/16.
 */
public class BillVerified {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("bill_amount")
    @Expose
    private int billAmount;
    @SerializedName("verified_date")
    @Expose
    private String verifiedDate;

    @SerializedName("points_earned")
    @Expose
    private int points_earned;

    /**
     *
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }



    /**
     *
     * @return
     *     The billAmount
     */
    public int getBillAmount() {
        return billAmount;
    }


    /**
     *
     * @return
     *     The verifiedDate
     */
    public String getVerifiedDate() {
        return verifiedDate;
    }

    public int getPoints_earned() {
        return points_earned;
    }
}
