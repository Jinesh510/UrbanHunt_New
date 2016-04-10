package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 27/03/16.
 */
public class UserPointsFragment extends Fragment {



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


        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
