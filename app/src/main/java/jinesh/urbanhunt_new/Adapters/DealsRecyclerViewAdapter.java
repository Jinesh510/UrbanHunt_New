package jinesh.urbanhunt_new.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import jinesh.urbanhunt_new.R;
import jinesh.urbanhunt_new.model.Sectors;

/**
 * Created by Jinesh on 27/03/16.
 */
public class DealsRecyclerViewAdapter extends RecyclerView.Adapter<DealsRecyclerViewAdapter.ViewHolder> {


    private ArrayList<Sectors> mSectors;

    public DealsRecyclerViewAdapter(ArrayList<Sectors> mSectors) {
        this.mSectors = mSectors;
    }


    public interface DealsOnItemClicklistener {
        void onItemClick(View itemView,int position);
    }

    private static DealsOnItemClicklistener listener;

    public void setOnItemClickListener(DealsOnItemClicklistener listener){
        this.listener = listener;

    }

    @Override
    public DealsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sector_item,parent,false);
        ViewHolder mViewHolder = new ViewHolder(view);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(DealsRecyclerViewAdapter.ViewHolder holder, int position) {

        Sectors sector = mSectors.get(position);
        holder.sectorName.setText(sector.getLabel());


    }

    @Override
    public int getItemCount() {
        return mSectors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView sectorName;
        public ImageView sectorBackground;


        public ViewHolder(final View itemView) {
            super(itemView);

            sectorName = (TextView)itemView.findViewById(R.id.sectorName);
            sectorBackground = (ImageView) itemView.findViewById(R.id.sectorBackground);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener !=null){
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });

        }



    }



}
