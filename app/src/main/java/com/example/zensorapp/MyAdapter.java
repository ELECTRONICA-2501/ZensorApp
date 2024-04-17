package com.example.zensorapp;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import java.util.List;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import android.text.format.DateUtils;
import com.example.zensorapp.DashboardPhaseListActivity;
import com.example.zensorapp.Zensor;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //variables.
    private Context context;
    private List<Zensor> ZensorDashboardList;

    //constructor.
    public MyAdapter(Context context, List<Zensor> ZensorDashboardList) {
        this.context = context;
        this.ZensorDashboardList = ZensorDashboardList;
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zensor_dashboard, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Zensor Zen = ZensorDashboardList.get(position);
        holder.title.setText(Zen.getPageTitle());
        holder.emotion.setText(Zen.getEmotion());
        holder.meditation.setText(Zen.getMeditation());
        holder.pageContent.setText(Zen.getPageContent());

        // Assuming 'image' is an ImageView and you want to set an image from a URL
        String imageUrl = Zen.getImageURL();
        // Glide requires a URL, so your image reference should be a String URL
        Glide.with(context).load(imageUrl).fitCenter().into(holder.image);

        String timeAgo = (String) DateUtils.getRelativeTimeSpanString(Zen.getTimeAdded().getSeconds() * 1000);
        holder.dateAdded.setText(timeAgo);
    }

    @Override
    public int getItemCount() {
        return ZensorDashboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, emotion, meditation, dateAdded, pageContent;
        public ImageView image, shareButton, deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.dashboard_title_list);
            //emotion = itemView.findViewById(R.id.);
            meditation = itemView.findViewById(R.id.dashboard_description_list);
            //dateAdded = itemView.findViewById(R.id.date_added);
            //pageContent = itemView.findViewById(R.id.d);
            image = itemView.findViewById(R.id.dashboard_image_list);
            shareButton = itemView.findViewById(R.id.dashboard_share_button);

            // Set up the click listeners for your buttons
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Share button clicked
                    // Implement your share logic here
                }
            });


        }
    }

}


