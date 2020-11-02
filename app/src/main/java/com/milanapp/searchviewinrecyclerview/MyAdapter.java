package com.milanapp.searchviewinrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Model>  modelList = new ArrayList<>();
    private List<Model>  backuplist = new ArrayList<>();

    public MyAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        backuplist = new ArrayList<>(modelList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView.setText(modelList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter  filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<Model> filteredDeta = new ArrayList<>();

            if (keyword.toString().isEmpty())
                filteredDeta.addAll(backuplist);
            else {
                for (Model obj : backuplist){

                    if (obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filteredDeta.add(obj);
                }
            }

            FilterResults results = new FilterResults();
            results.values=filteredDeta ;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            modelList.clear();
            modelList.addAll((ArrayList<Model>)results.values);
            notifyDataSetChanged();

        }
    };
}
