package com.example.studentapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class DataBeanAdapter extends RecyclerView.Adapter<DataBeanAdapter.ViewHolder>{
    private List<DataBean> items;
    private int itemLayout;
    private Context context;

    public DataBeanAdapter(List<DataBean> items){
        this.items = items;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView classAndSection;
        public CircleImageView civViewDataProfile, civViewDataNext;

        public ViewHolder(View itemView) {
            super(itemView);
            civViewDataProfile = (CircleImageView) itemView.findViewById(R.id.civ_view_data_profile);
            civViewDataNext = (CircleImageView) itemView.findViewById(R.id.civ_view_data_next);
            name = (TextView) itemView.findViewById(R.id.tv_view_data_name);
            classAndSection = (TextView) itemView.findViewById(R.id.tv_class_section_school_name);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.list_view_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataBean item = items.get(position);
        holder.name.setText(item.name);
        holder.classAndSection.setText(item.classNo);
        holder.civViewDataProfile.setImageBitmap(item.image);
        holder.civViewDataNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });
        //All the thing you gonna show in the item
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
