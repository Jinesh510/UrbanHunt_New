package jinesh.urbanhunt_new.Fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import jinesh.urbanhunt_new.Adapters.DealsRecyclerViewAdapter;
import jinesh.urbanhunt_new.model.Sectors;

/**
 * Created by Jinesh on 02/04/16.
 */
public class RewardsFragment extends Fragment {

    RecyclerView rewardsRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Sectors> mSectors = new ArrayList<Sectors>();
    DealsRecyclerViewAdapter mDealsRecyclerViewAdapter;


}
