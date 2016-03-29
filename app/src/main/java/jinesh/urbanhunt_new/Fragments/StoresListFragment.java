package jinesh.urbanhunt_new.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jinesh.urbanhunt_new.Adapters.StoresRecyclerViewAdapter;
import jinesh.urbanhunt_new.ItemClickSupport;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.StoreDetailActivity;
import jinesh.urbanhunt_new.model.Sectors;
import jinesh.urbanhunt_new.model.Stores;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 27/03/16.
 */
public class StoresListFragment extends Fragment {

    RecyclerView storesRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Sectors> mSectors = new ArrayList<Sectors>();
    StoresRecyclerViewAdapter mStoresRecyclerViewAdapter;
    float latitude;
    float longitude;
    int sector_id;
    ArrayList<Stores> mStores;
    String sub_locality;



    public static StoresListFragment newInstance(int sector_id) {
        StoresListFragment fragment = new StoresListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("sector", sector_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static StoresListFragment newInstance(int sector_id, float Lat, float Lng) {
        StoresListFragment fragment = new StoresListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("sector", sector_id);
        bundle.putFloat("Lat", Lat);
        bundle.putFloat("Lng", Lng);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static StoresListFragment newInstance(String sector_id, String sub_locality) {
        StoresListFragment fragment = new StoresListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sector", sector_id);
        bundle.putString("sub_locality", sub_locality);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stores_list_frag, container, false);
        storesRecyclerView = (RecyclerView) view.findViewById(R.id.storesRecyclerView);

        sector_id = getArguments().getInt("sector");

        latitude = getArguments().getFloat("Lat");
        longitude = getArguments().getFloat("Lng");

        sub_locality = getArguments().getString("sub_locality");


        layoutManager = new LinearLayoutManager(getContext());

        mStoresRecyclerViewAdapter = new StoresRecyclerViewAdapter();


        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this.getActivity());
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);

        if(latitude == 0){

            RestClient.get().getStores(s, sector_id, latitude, longitude, new Callback<ArrayList<Stores>>() {
                @Override
                public void success(ArrayList<Stores> stores, Response response) {

//                    for (int i = 0; i < stores.size(); i++) {
//                        mStores.add(stores.get(i));
//                    }
//
//                    storesRecyclerView.setHasFixedSize(true);
//                    storesRecyclerView.setLayoutManager(layoutManager);
//
//                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
//                    mStoresRecyclerViewAdapter.notifyDataSetChanged();

                    setAdapterView(stores);

                    startDetailActivity(storesRecyclerView);

//                    ItemClickSupport.addTo(storesRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//                        @Override
//                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//
//                            Intent i = new Intent(getActivity(), StoreDetailActivity.class);
//                            Stores store = mStores.get(position);
////                            int store_id = store.getStore();
//                            i.putExtra("store_id",position);
//                            startActivity(i);
//
//                        }
//                    });

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

        if(sub_locality != null){

            RestClient.get().getStores(s, sector_id, sub_locality, new Callback<ArrayList<Stores>>() {
                @Override
                public void success(ArrayList<Stores> stores, Response response) {


                    setAdapterView(stores);

                    startDetailActivity(storesRecyclerView);

//                    for (int i = 0; i < stores.size(); i++) {
//                        mStores.add(stores.get(i));
//                    }
//
//                    storesRecyclerView.setHasFixedSize(true);
//                    storesRecyclerView.setLayoutManager(layoutManager);
//
//                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
//                    mStoresRecyclerViewAdapter.notifyDataSetChanged();


                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

        }


        return view;
    }

    public void setAdapterView(ArrayList<Stores> stores){

        for (int i = 0; i < stores.size(); i++) {
            mStores.add(stores.get(i));
        }

        storesRecyclerView.setHasFixedSize(true);
        storesRecyclerView.setLayoutManager(layoutManager);

        storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
        mStoresRecyclerViewAdapter.notifyDataSetChanged();

    }

    public void startDetailActivity(RecyclerView recyclerView){

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent i = new Intent(getActivity(), StoreDetailActivity.class);
                Stores store = mStores.get(position);
                int store_id = store.getId();
                i.putExtra("store_id",store_id);
//                i.putExtra("")
                startActivity(i);

            }
        });






    }
}
