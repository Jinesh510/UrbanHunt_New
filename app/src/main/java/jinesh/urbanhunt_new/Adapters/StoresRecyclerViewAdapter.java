package jinesh.urbanhunt_new.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Brands;
import jinesh.urbanhunt_new.model.Stores;

/**
 * Created by Jinesh on 27/03/16.
 */
public class StoresRecyclerViewAdapter extends RecyclerView.Adapter<StoresRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Stores> mStores;
    Context context;
    final String ROOT = "http://uhtest.herokuapp.com";


    public StoresRecyclerViewAdapter(Context context,ArrayList<Stores> mStores) {
        this.context = context;
        this.mStores = mStores;
        Log.d("StoresAdapter","true");

    }

    @Override
    public StoresRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item,parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(StoresRecyclerViewAdapter.ViewHolder holder, int position) {

        Stores mStore = mStores.get(position);
        Brands mBrand = mStore.getBrand();

        String LogoUrl = ROOT + mBrand.getLogo();
        String BackgroundUrl = ROOT + mBrand.getBackground();



        holder.brandName.setText(mBrand.getBrandName());
        Picasso.with(context).load(LogoUrl).into(holder.brandLogo);
        Picasso.with(context).load(BackgroundUrl).into(holder.brandBackground);
        holder.brandDescription.setText(mBrand.getDescription());
        holder.storePoints.setText(mBrand.getCashback() + "");

        Log.d("onBind", "true");

    }


    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView brandName;
        TextView brandDescription;
        TextView storePoints;
        ImageView brandLogo;
        ImageView brandBackground;


        public ViewHolder(View itemView) {
            super(itemView);

            brandName = (TextView)itemView.findViewById(R.id.brandName);
            brandDescription = (TextView)itemView.findViewById(R.id.brandDescription);
            storePoints = (TextView)itemView.findViewById(R.id.storePoints);
            brandLogo = (ImageView)itemView.findViewById(R.id.brandLogo);
            brandBackground = (ImageView) itemView.findViewById(R.id.brandBackground);

            Log.d("ViewHolder","true");

        }
    }

    public void updateList(ArrayList<Stores> data) {
        mStores = data;
        notifyDataSetChanged();
    }
}
