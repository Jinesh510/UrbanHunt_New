package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 09/04/16.
 */
public class TransactionDetailFragment extends Fragment {

    TextView mBillAmount;
    TextView mBillStatus;
    TextView mPointsEarned;


    public static TransactionDetailFragment newInstance() {
        TransactionDetailFragment fragment = new TransactionDetailFragment();
        Bundle bundle = new Bundle();
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


        return view;
    }
}
