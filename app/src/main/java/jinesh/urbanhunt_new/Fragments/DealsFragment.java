package jinesh.urbanhunt_new.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jinesh.urbanhunt_new.Activity.StoresListActivity;
import jinesh.urbanhunt_new.Adapters.DealsRecyclerViewAdapter;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.Sectors;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 26/03/16.
 */
public class DealsFragment extends Fragment {

    RecyclerView sectorRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Sectors> mSectors = new ArrayList<Sectors>();
    DealsRecyclerViewAdapter mDealsRecyclerViewAdapter;


    public static DealsFragment newInstance() {
        DealsFragment fragment = new DealsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.deals_frag, container, false);
        sectorRecyclerView = (RecyclerView) view.findViewById(R.id.sectorRecyclerView);

        layoutManager = new LinearLayoutManager(getContext());

        mDealsRecyclerViewAdapter = new DealsRecyclerViewAdapter(getActivity(),mSectors);


        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this.getActivity());
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);

        RestClient.get().getSectors(s, new Callback<ArrayList<Sectors>>() {
            @Override
            public void success(ArrayList<Sectors> sectors, Response response) {

                Log.d("Success","true");


                for (int i = 0; i < sectors.size(); i++) {
//                    Sectors sec = new Sectors(sectors.get(i).getId(),sectors.get(i).getLabel(),sectors.get(i).getBackgroundImg());
                    if(sectors.get(i) != null){
                        Log.d("sector_obj",sectors.get(i).toString());
                    }

                    mSectors.add(sectors.get(i));
//                    mSectors.add(sec);
                    Log.d("AddSector","true");
                }

                int size = mSectors.size();
                Log.d("mSectors", size + "" );

                sectorRecyclerView.setHasFixedSize(true);
                sectorRecyclerView.setLayoutManager(layoutManager);

                sectorRecyclerView.setAdapter(mDealsRecyclerViewAdapter);
                mDealsRecyclerViewAdapter.notifyDataSetChanged();

//                ItemClickSupport.addTo(sectorRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//
//                        Intent i = new Intent(getActivity(), StoresListActivity.class);
//                        i.putExtra("sector_id",position);
//                        startActivity(i);
//
//                    }
//                });

                mDealsRecyclerViewAdapter.setOnItemClickListener(new DealsRecyclerViewAdapter.DealsOnItemClicklistener() {
                    @Override
                    public void onItemClick(View itemView, int position) {

                        int sector_id = mSectors.get(position).getId();
                        Intent i = new Intent(getActivity(), StoresListActivity.class);
                        i.putExtra("sector_id",sector_id);
                        startActivity(i);

                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return view;
    }
}
