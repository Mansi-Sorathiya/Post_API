package com.example.post_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.Holder> {
    MainActivity mainActivity;
    ArrayList<DataModel> dataList;

    public Recycler_Adapter(MainActivity mainActivity, ArrayList<DataModel> dataList) {
        this.mainActivity = mainActivity;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mainActivity).inflate(R.layout.recycle_item,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.userid.setText("UserId : "+dataList.get(position).getUserid());
        holder.id.setText("Id : "+dataList.get(position).getId());
        holder.title.setText("title : "+dataList.get(position).getTitle());
        holder.body.setText("Body : "+dataList.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView userid,id,title,body;
        public Holder(@NonNull View itemView) {
            super(itemView);
            userid=itemView.findViewById(R.id.userid);
            id=itemView.findViewById(R.id.id);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);
        }
    }
}
