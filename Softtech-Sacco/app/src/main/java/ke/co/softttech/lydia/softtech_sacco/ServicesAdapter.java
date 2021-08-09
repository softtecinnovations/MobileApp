package ke.co.softttech.lydia.softtech_sacco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.ServiceModel;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    private Context mContext;
    private List<ServiceModel> serviceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public AppCompatImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (AppCompatImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public ServicesAdapter(Context context, List<ServiceModel> serviceList) {
        mContext = context;
        this.serviceList = serviceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ServiceModel serviceModel = serviceList.get(position);
        holder.title.setText(serviceModel.getTittle());

        // loading image
        Glide.with(mContext).load(serviceModel.getThumbnail()).placeholder(R.drawable.ic_baseline_account_balance_wallet_24).into(holder.thumbnail);

//        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(mContext,SendActivity.class);
//                mContext.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }
}