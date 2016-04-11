package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.BillVerified;
import jinesh.urbanhunt_new.model.Brands;
import jinesh.urbanhunt_new.model.Transactions;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 09/04/16.
 */
public class TransactionDetailFragment extends Fragment {

    TextView mBillAmount;
    TextView mBillStatus;
    TextView mPointsEarned;
    ImageView mBillImage;
    TextView mBrandName;

    Transactions mTransaction;


    public static TransactionDetailFragment newInstance(int transactionId) {
        TransactionDetailFragment fragment = new TransactionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("transaction_id",transactionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TransactionDetailFragment newInstance(Transactions transaction) {
        TransactionDetailFragment fragment = new TransactionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("transaction", transaction);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.transaction_detail_frag, container, false);


        mBillAmount = (TextView)view.findViewById(R.id.billAmountTxt);
        mBillStatus = (TextView)view.findViewById(R.id.billStatusTxt);
        mPointsEarned = (TextView)view.findViewById(R.id.pointsEarnedTxt);
        mBrandName = (TextView)view.findViewById(R.id.brandName);

        mBillImage = (ImageView)view.findViewById(R.id.billImage);

        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(getActivity());
        String access_token_wo_quotes = access_token.replace("\"", "");
        final String s = token.concat(access_token_wo_quotes);

        int transaction_id = getArguments().getInt("transaction_id");

        mTransaction = (Transactions) getArguments().getSerializable("transaction");


        if (transaction_id != 0){

            RestClient.get().getTransactionDetail(s, transaction_id, new Callback<Transactions>() {
                @Override
                public void success(Transactions transactions, Response response) {

                    Transactions mTransaction = transactions;

                    Picasso.with(getContext()).load(mTransaction.getImage()).into(mBillImage);

                    BillVerified mBillVerified = mTransaction.getBillVerified();

                    mBillAmount.setText(mBillVerified.getBillAmount());
                    mBillStatus.setText(mTransaction.getVerified().toString());
                    mPointsEarned.setText(mBillVerified.getPoints_earned());

                    Brands mBrand = mTransaction.getStore().getBrand();

                    mBrandName.setText(mBrand.getBrandName());
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        }
        else{
            Picasso.with(getContext()).load(mTransaction.getImage()).into(mBillImage);

            BillVerified mBillVerified = mTransaction.getBillVerified();

            mBillAmount.setText(mBillVerified.getBillAmount());
            mBillStatus.setText(mTransaction.getVerified().toString());
            mPointsEarned.setText(mBillVerified.getPoints_earned());

            Brands mBrand = mTransaction.getStore().getBrand();

            mBrandName.setText(mBrand.getBrandName());

        }



        return view;
    }
}
