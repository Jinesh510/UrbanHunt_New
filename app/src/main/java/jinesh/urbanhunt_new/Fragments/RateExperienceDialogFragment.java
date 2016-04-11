package jinesh.urbanhunt_new.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.widget.TextView;

import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 10/04/16.
 */
public class RateExperienceDialogFragment extends DialogFragment {

    TextView mExperienceTxt;
    AppCompatRatingBar mExperienceRatingBar;
    private static final String KEY_SAVE_RATING_BAR_VALUE = "KEY_SAVE_RATING_BAR_VALUE";

    RateExperienceListener mListener;
    int num_stars;


    public static RateExperienceDialogFragment newInstance() {
        RateExperienceDialogFragment fragment = new RateExperienceDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public interface RateExperienceListener {

        void onFinishRatingDialog(int mStars);

    }

    public void setRateExperienceListener(RateExperienceListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.rate_exp_dialog_frag,null);

        mExperienceRatingBar = (AppCompatRatingBar)view.findViewById(R.id.experienceRating);

        num_stars = (int)mExperienceRatingBar.getRating();


        mExperienceTxt = (TextView)view.findViewById(R.id.experienceTxt);

//        if (savedInstanceState != null) {
//            if (savedInstanceState.containsKey(KEY_SAVE_RATING_BAR_VALUE)) {
//                mExperienceRatingBar.setRating(savedInstanceState.getFloat(KEY_SAVE_RATING_BAR_VALUE));
//            }
//        }

        alertDialogBuilder.setView(view);
        alertDialogBuilder.setTitle("Rate your Experience");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), getString(R.string.dialog_positive_toast_message), Toast.LENGTH_SHORT).show();
                mListener.onFinishRatingDialog(num_stars);
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), getString(R.string.dialog_negative_toast_message), Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        return alertDialogBuilder.create();
    }

}
