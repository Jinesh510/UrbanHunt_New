package jinesh.urbanhunt_new.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Brands;
import jinesh.urbanhunt_new.model.Stores;
import jinesh.urbanhunt_new.model.Transactions;

/**
 * Created by Jinesh on 09/04/16.
 */

public class TransactionsRecyclerViewAdapter extends RecyclerView.Adapter<TransactionsRecyclerViewAdapter.ViewHolder> {


    ArrayList<Transactions> mTransactions;
    Context context;

    public TransactionsRecyclerViewAdapter(ArrayList<Transactions> mTransactions, Context context) {
        this.mTransactions = mTransactions;
        this.context = context;
    }

    @Override
    public TransactionsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item,parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);
        Log.d("onCreateVH", "true");
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(TransactionsRecyclerViewAdapter.ViewHolder holder, int position) {

        Transactions transaction = mTransactions.get(position);
        Stores store = transaction.getStore();
        Brands brand = store.getBrand();
        String brandName = brand.getBrandName();

//        String BackgroundUrl = ROOT + sector.getBackgroundImg();
        holder.billAmount.setText(transaction.getBillVerified().getBillAmount());
        holder.billStatus.setText(transaction.getVerified().toString());
        holder.brandName.setText(brandName);
//        holder.pointsEarned.setText(transaction.);
//        Picasso.with(context).load(BackgroundUrl).into(holder.sectorBackground);
        Log.d("onBind","true");


    }

    @Override
    public int getItemCount() {
        return mTransactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView billAmount;
        TextView billStatus;
        TextView brandName;
        TextView pointsEarned;

        public ViewHolder(View itemView) {
            super(itemView);

            billAmount = (TextView)itemView.findViewById(R.id.billAmountTxt);
            billStatus = (TextView)itemView.findViewById(R.id.billStatusTxt);
            brandName = (TextView)itemView.findViewById(R.id.brandNameTxt);
            pointsEarned = (TextView)itemView.findViewById(R.id.pointsEarnedTxt);


        }
    }

    public void updateList(ArrayList<Transactions> data) {
        mTransactions = data;
        notifyDataSetChanged();
    }


}
