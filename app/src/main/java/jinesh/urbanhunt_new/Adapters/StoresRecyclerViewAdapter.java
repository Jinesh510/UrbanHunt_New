package jinesh.urbanhunt_new.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Stores;

/**
 * Created by Jinesh on 27/03/16.
 */
public class StoresRecyclerViewAdapter extends RecyclerView.Adapter<StoresRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Stores> mStores;

    @Override
    public StoresRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sector_item,parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(StoresRecyclerViewAdapter.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return mStores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(View itemView) {
            super(itemView);


        }
    }

    public void updateList(ArrayList<Stores> data) {
        mStores = data;
        notifyDataSetChanged();
    }
}
