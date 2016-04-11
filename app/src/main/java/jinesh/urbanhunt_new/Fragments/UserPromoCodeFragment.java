package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 27/03/16.
 */
public class UserPromoCodeFragment extends Fragment {

    TextView mPromoCodeTxt;

    EditText mPromoCodeEt;
    Button mSubmitBtn;


    public static UserPromoCodeFragment newInstance() {
        UserPromoCodeFragment fragment = new UserPromoCodeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_promo_code_frag, container, false);

        mPromoCodeTxt = (TextView)view.findViewById(R.id.promocodeTxt);
        mPromoCodeEt = (EditText)view.findViewById(R.id.promocodeEt);

        mSubmitBtn = (Button)view.findViewById(R.id.submitBtn);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return view;
    }
}
