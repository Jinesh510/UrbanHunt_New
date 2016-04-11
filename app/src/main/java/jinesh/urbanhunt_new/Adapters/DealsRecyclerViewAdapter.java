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
import jinesh.urbanhunt_new.model.Sectors;

/**
 * Created by Jinesh on 27/03/16.
 */
public class DealsRecyclerViewAdapter extends RecyclerView.Adapter<DealsRecyclerViewAdapter.ViewHolder> {


    private ArrayList<Sectors> mSectors;
    Context context;
    final String ROOT = "http://uhtest.herokuapp.com";

    public DealsRecyclerViewAdapter(Context context,ArrayList<Sectors> mSectors) {
        this.context = context;
        this.mSectors = mSectors;
        Log.d("DealsAdapter","true");
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
        Log.d("onCreateVH","true");
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(DealsRecyclerViewAdapter.ViewHolder holder, int position) {


        Sectors sector = mSectors.get(position);

        String BackgroundUrl = ROOT + sector.getBackgroundImg();
        holder.sectorName.setText(sector.getLabel());
        Picasso.with(context).load(BackgroundUrl).into(holder.sectorBackground);
        Log.d("onBind","true");


    }

    @Override
    public int getItemCount() {
        return mSectors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView sectorName;
        ImageView sectorBackground;


        public ViewHolder(final View itemView) {
            super(itemView);

            sectorName = (TextView)itemView.findViewById(R.id.sectorName);
            sectorBackground = (ImageView) itemView.findViewById(R.id.sectorBackground);

            Log.d("ViewHolder","true");

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
