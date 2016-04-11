package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.UserPoints;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 27/03/16.
 */
public class UserPointsFragment extends Fragment {


    TextView mPointsLeft;


    public static UserPointsFragment newInstance() {

        UserPointsFragment fragment = new UserPointsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_points_frag, container, false);

        mPointsLeft = (TextView)view.findViewById(R.id.pointsLeft);

        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this.getActivity());
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);

        RestClient.get().getUserPoints(s, new Callback<UserPoints>() {
            @Override
            public void success(UserPoints userPoints, Response response) {

                mPointsLeft.setText(userPoints.getPoints_left());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return view;
    }
}
