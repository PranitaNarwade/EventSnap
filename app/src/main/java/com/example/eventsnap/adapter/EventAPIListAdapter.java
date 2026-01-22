package com.example.eventsnap.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventsnap.databinding.ItemsEventapilistBinding;
import com.example.eventsnap.model.TodoModel;

import java.util.List;

public class EventAPIListAdapter extends RecyclerView.Adapter<EventAPIListAdapter.MyViewHolder> {
    Context context;
    List<TodoModel> todoModelList;

    public EventAPIListAdapter(Context context, List<TodoModel> todoModelList) {
        this.context = context;
        this.todoModelList = todoModelList;
    }

    public void setData(List<TodoModel> todoModelList){
        this.todoModelList = todoModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventAPIListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemsEventapilistBinding binding = ItemsEventapilistBinding.inflate(LayoutInflater.from(context),viewGroup,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAPIListAdapter.MyViewHolder holder, int i) {
        holder.binding.txtTask.setText(todoModelList.get(i).getTodo());
        if(todoModelList.get(i).isCompleted())
            holder.binding.txtStatus.setText("Completed");
        else
            holder.binding.txtStatus.setText("Pending");
    }

    @Override
    public int getItemCount() {
        return todoModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemsEventapilistBinding binding;
        public MyViewHolder(ItemsEventapilistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
