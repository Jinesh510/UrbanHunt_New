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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

import jinesh.urbanhunt_new.Activity.StoreDetailActivity;
import jinesh.urbanhunt_new.Adapters.StoresRecyclerViewAdapter;
import jinesh.urbanhunt_new.ItemClickSupport;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.Stores;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 27/03/16.
 */
public class StoresListFragment extends Fragment{

    RecyclerView storesRecyclerView;
    RecyclerView.LayoutManager layoutManager;
//    ArrayList<Sectors> mSectors = new ArrayList<Sectors>();
    StoresRecyclerViewAdapter mStoresRecyclerViewAdapter;
    float latitude;
    float longitude;
    int sector_id;
    ArrayList<Stores> mStores = new ArrayList<Stores>();
    String sub_locality;
    AutoCompleteTextView mLocationSearchTxt;
    Button mLocationSearchBtn;
    String sub_localityTxt;



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

        Log.d("StoresListFragment","true");
        storesRecyclerView = (RecyclerView) view.findViewById(R.id.storesRecyclerView);

        sector_id = getArguments().getInt("sector");

        latitude = getArguments().getFloat("Lat");
        longitude = getArguments().getFloat("Lng");

        sub_locality = getArguments().getString("sub_locality");


        // Location Search

        ArrayList<String> sub_locality_list = new ArrayList<>();

        sub_locality_list.add("Malad East");
        sub_locality_list.add("Andheri East");
        sub_locality_list.add("All");

        mLocationSearchTxt = (AutoCompleteTextView)view.findViewById(R.id.locationSearchTxt);
        mLocationSearchBtn = (Button)view.findViewById(R.id.locationSearchBtn);

        ArrayAdapter<String> sub_locality_adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.autocomplete_tv_item,sub_locality_list);

        mLocationSearchTxt.setAdapter(sub_locality_adapter);
        mLocationSearchTxt.setThreshold(1);

        layoutManager = new LinearLayoutManager(getContext());

        mStoresRecyclerViewAdapter = new StoresRecyclerViewAdapter(getActivity(),mStores);

        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this.getActivity());
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);

        if(latitude != 0){

            Log.d("Latitude","Available");
            Log.d("Sector_id",sector_id+"");

            RestClient.get().getStores(s, sector_id, new Callback<ArrayList<Stores>>() {
                @Override
                public void success(ArrayList<Stores> stores, Response response) {

                    Log.d("Response",response.getUrl());

//                    setAdpterView(stores);
                    Log.d("All_Stores","success");
                    Log.d("Stores_Size", "" + stores.size());

                    for (int i = 0; i < stores.size(); i++) {

                            mStores.add(stores.get(i));

                    }

                    storesRecyclerView.setHasFixedSize(true);
                    storesRecyclerView.setLayoutManager(layoutManager);
                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
                    mStoresRecyclerViewAdapter.updateList(mStores);

                    startDetailActivity(storesRecyclerView);

                }

                @Override
                public void failure(RetrofitError error) {

                }
            });

//            RestClient.get().getStores(s, sector_id, latitude, longitude, new Callback<ArrayList<Stores>>() {
//                @Override
//                public void success(ArrayList<Stores> stores, Response response) {
//
//                    Log.d("Stores_Success","true");
//                    Log.d("Stores_Size",""+stores.size());
////                    sub_localityTxt = stores.get(1).getSub_locality();
////                    mLocationSearchTxt.setText(sub_localityTxt);
//
//
////                    for (int i = 0; i < stores.size(); i++) {
////                        mStores.add(stores.get(i));
////                    }
////
////                    storesRecyclerView.setHasFixedSize(true);
////                    storesRecyclerView.setLayoutManager(layoutManager);
////
////                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
////                    mStoresRecyclerViewAdapter.notifyDataSetChanged();
//
//
//                    for (int i = 0; i < stores.size(); i++) {
//                        mStores.add(stores.get(i));
//                    }
//
//                    storesRecyclerView.setHasFixedSize(true);
//                    storesRecyclerView.setLayoutManager(layoutManager);
//
//                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
//                    mStoresRecyclerViewAdapter.notifyDataSetChanged();
//
////                    setAdpterView(stores);
//
//                    startDetailActivity(storesRecyclerView);
//
////                    ItemClickSupport.addTo(storesRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
////                        @Override
////                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
////
////                            Intent i = new Intent(getActivity(), BillUploadActivity.class);
////                            Stores store = mStores.get(position);
//////                            int store_id = store.getStore();
////                            i.putExtra("store_id",position);
////                            startActivity(i);
////
////                        }
////                    });
//
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//
//                }
//            });
        }
        else {

            Log.d("Latitude","Unavailable");
            RestClient.get().getStores(s, sector_id, new Callback<ArrayList<Stores>>() {
                @Override
                public void success(ArrayList<Stores> stores, Response response) {

//                    mLocationSearchTxt.setText("All");
                    setAdpterView(stores);
                    startDetailActivity(storesRecyclerView);


                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

//        if(sub_locality != null){
//
//            RestClient.get().getStores(s, sector_id, sub_locality, new Callback<ArrayList<Stores>>() {
//                @Override
//                public void success(ArrayList<Stores> stores, Response response) {
//
//
//                    setAdapterView(stores);
//
//                    startDetailActivity(storesRecyclerView);
//
////                    for (int i = 0; i < stores.size(); i++) {
////                        mStores.add(stores.get(i));
////                    }
////
////                    storesRecyclerView.setHasFixedSize(true);
////                    storesRecyclerView.setLayoutManager(layoutManager);
////
////                    storesRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
////                    mStoresRecyclerViewAdapter.notifyDataSetChanged();
//
//
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//
//                }
//            });
//
//        }

        mLocationSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStores.clear();
                String locationTxt = mLocationSearchTxt.getText().toString();

                RestClient.get().getStores(s, sector_id, locationTxt, new Callback<ArrayList<Stores>>() {
                @Override
                public void success(ArrayList<Stores> stores, Response response) {

                    for (int i = 0; i < stores.size(); i++) {
                        mStores.add(stores.get(i));
                    }

                    mStoresRecyclerViewAdapter.updateList(mStores);

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
        });



        return view;
    }

    public void setAdpterView(ArrayList<Stores> stores){

        for (int i = 0; i < stores.size(); i++) {
            mStores.add(stores.get(i));
            Log.d("mStores",mStores.size()+"");
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
                i.putExtra("store_object",store);
//                i.putExtra("")
                startActivity(i);

            }
        });



    }

}
