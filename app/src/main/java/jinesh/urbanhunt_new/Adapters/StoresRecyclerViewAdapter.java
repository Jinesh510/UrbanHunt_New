package jinesh.urbanhunt_new.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jinesh.urbanhunt_new.model.Stores;

/**
 * Created by Jinesh on 27/03/16.
 */
public class StoresRecyclerViewAdapter extends RecyclerView.Adapter<StoresRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Stores> mStores;

    @Override
    public StoresRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
}
