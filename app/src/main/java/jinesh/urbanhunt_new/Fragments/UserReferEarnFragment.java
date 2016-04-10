package jinesh.urbanhunt_new.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 27/03/16.
 */
public class UserReferEarnFragment extends Fragment {

    ImageView mReferralIV;
    TextView mReferralTxt;
    Button mReferralBtn;
    TextView mReferralCodeTxt;

    public static UserReferEarnFragment newInstance() {
        UserReferEarnFragment fragment = new UserReferEarnFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_refer_earn_frag, container, false);

        mReferralIV = (ImageView)view.findViewById(R.id.referralIV);
        mReferralTxt = (TextView)view.findViewById(R.id.referralTxt);
        mReferralCodeTxt = (TextView)view.findViewById(R.id.referralCodeTxt);

        mReferralBtn = (Button)view.findViewById(R.id.referralBtn);

        final Bitmap referral_icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.ic_referral);

        mReferralIV.setImageBitmap(referral_icon);


        mReferralBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inviteFriends();

            }
        });



        return view;
    }

    public void inviteFriends(){

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, use this code to join UrbanHunt and earn 200 free points ");
        startActivity(Intent.createChooser(shareIntent, "Share link using"));

    }
}
