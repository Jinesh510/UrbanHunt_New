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

import jinesh.urbanhunt_new.Activity.StoreDetailActivity;
import jinesh.urbanhunt_new.Adapters.TransactionsRecyclerViewAdapter;
import jinesh.urbanhunt_new.ItemClickSupport;
import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.RestClient;
import jinesh.urbanhunt_new.SaveSharedPreference;
import jinesh.urbanhunt_new.model.Transactions;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jinesh on 27/03/16.
 */
public class UserTransactionsListFragment extends Fragment {

    RecyclerView mTransactionRecyclerView;
    LinearLayoutManager mLayoutManager;
    TransactionsRecyclerViewAdapter mTransactionsRecyclerViewAdapter;
    ArrayList<Transactions> mTransactions;

    public static UserTransactionsListFragment newInstance() {
        UserTransactionsListFragment fragment = new UserTransactionsListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.deals_frag, container, false);
        mTransactionRecyclerView = (RecyclerView) view.findViewById(R.id.transactionRecyclerView);

        mTransactionsRecyclerViewAdapter = new TransactionsRecyclerViewAdapter(mTransactions,getActivity());

        mLayoutManager = new LinearLayoutManager(getContext());

//        mDealsRecyclerViewAdapter = new DealsRecyclerViewAdapter(getActivity(),mSectors);


        final String token = "Token ";
        final String access_token = SaveSharedPreference.getFBUserAccessToken(this.getActivity());
        String access_token_wo_quotes = access_token.replace("\"","");
        final String s = token.concat(access_token_wo_quotes);

        RestClient.get().getUserTransactions(s, new Callback<ArrayList<Transactions>>() {
            @Override
            public void success(ArrayList<Transactions> transactions, Response response) {

                Log.d("All_Transactions", "success");
                Log.d("Transactions_Size", "" + transactions.size());

                for (int i = 0; i < transactions.size(); i++) {

                    mTransactions.add(transactions.get(i));

                }

                mTransactionRecyclerView.setHasFixedSize(true);
                mTransactionRecyclerView.setLayoutManager(mLayoutManager);
                mTransactionRecyclerView.setAdapter(mTransactionsRecyclerViewAdapter);
                mTransactionsRecyclerViewAdapter.updateList(mTransactions);

                startDetailActivity(mTransactionRecyclerView);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return view;
    }

    private void startDetailActivity(RecyclerView recyclerView) {


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView mRecyclerView, int position, View v) {

                Intent i = new Intent(getActivity(), StoreDetailActivity.class);
                Transactions transaction = mTransactions.get(position);
//                int store_id = transaction.getId();
//                i.putExtra("store_id",store_id);
//                i.putExtra("store_object",store);
//                i.putExtra("")
                startActivity(i);

            }
        });

    }

}
