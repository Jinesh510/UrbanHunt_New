package jinesh.urbanhunt_new.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import jinesh.urbanhunt_new.Adapters.StoresRecyclerViewAdapter;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.Stores;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 05/04/16.
 */
public class StoresListDummyActivity extends AppCompatActivity {


    float mLatitude;
    float mLongitude;
    RecyclerView mRecyclerView;
    int mSector_id;
    StoresRecyclerViewAdapter mStoresRecyclerViewAdapter;
    ArrayList<Stores> mStores = new ArrayList<Stores>();
    AutoCompleteTextView mAutoCompleteTextView;
    Button mSearchLocationBtn;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tv = (TextView)findViewById(R.id.helloTxt);

//
//        mRecyclerView = (RecyclerView)findViewById(R.id.storesRecyclerView);
//        mAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.locationSearchTxt);
//        mSearchLocationBtn = (Button)findViewById(R.id.locationSearchBtn);
//
//        mStoresRecyclerViewAdapter = new StoresRecyclerViewAdapter(this,mStores);
//        mLinearLayoutManager = new LinearLayoutManager(this);
//
        mSector_id = 3;

        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this);
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);


        RestClient.get().getStores(s,mSector_id, new Callback<ArrayList<Stores>>() {
            @Override
            public void success(ArrayList<Stores> stores, Response response) {

                Log.d("Store_List","success");

                Log.d("Stores_Size", "" + stores.size());
//
//                for (int i = 0; i < stores.size(); i++) {
//                    mStores.add(stores.get(i));
//                    Log.d("mStores",mStores.size()+"");
//                }
//
//
//                mRecyclerView.setHasFixedSize(true);
//                mRecyclerView.setLayoutManager(mLinearLayoutManager);
//                mRecyclerView.setAdapter(mStoresRecyclerViewAdapter);
//                mStoresRecyclerViewAdapter.updateList(mStores);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Failure","true");
                Log.d("Error",error.getResponse().getReason());
                Log.d("ErrorStr",error.toString());
            }
        });

    }
}
