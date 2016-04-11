package jinesh.urbanhunt_new.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 10/04/16.
 */
public class PhotoBooleanFragment extends Fragment {

    public static PhotoBooleanFragment newInstance() {
        PhotoBooleanFragment fragment = new PhotoBooleanFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.photo_boolean_frag, container, false);

        return view;
    }
}
