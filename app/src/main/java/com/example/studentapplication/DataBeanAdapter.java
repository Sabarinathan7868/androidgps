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

public class DataBeanAdapter extends RecyclerView.Adapter<DataBeanAdapter.ViewHolder> {
    private List<DataBean> items;
    private int itemLayout;
    private Context context;

    public DataBeanAdapter(List<DataBean> items) {
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DataBean item = items.get(position);
        holder.name.setText(item.name);
        holder.classAndSection.setText(item.classNo);
        holder.civViewDataProfile.setImageBitmap(item.image);
        holder.civViewDataNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBean item = items.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image", item.image);
                intent.putExtra("class", item.classNo);
                intent.putExtra("name", item.name);
                intent.putExtra("class", item.classNo);
                intent.putExtra("section", item.section);
                intent.putExtra("schoolName", item.schoolName);
                intent.putExtra("gender", item.gender);
                intent.putExtra("dob", item.dob);
                intent.putExtra("bloodGroup", item.bloodGroup);
                intent.putExtra("fatherName", item.fatherName);
                intent.putExtra("motherName", item.motherName);
                intent.putExtra("parentContactNo", item.parentContactNo);
                intent.putExtra("add1", item.add1);
                intent.putExtra("add2", item.add2);
                intent.putExtra("city", item.city);
                intent.putExtra("state", item.state);
                intent.putExtra("zip", item.zip);
                intent.putExtra("emergencyContactNo", item.emergencyContactNo);
                intent.putExtra("location", item.location);
                context.startActivity(intent);

            }
        });

        //All the thing you gonna show in the item
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
