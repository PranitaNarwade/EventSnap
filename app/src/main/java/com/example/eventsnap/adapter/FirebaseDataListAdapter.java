package com.example.eventsnap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventsnap.databinding.ItemsFirebaseDataBinding;
import com.example.eventsnap.model.EventsModel;

import java.util.List;

public class FirebaseDataListAdapter extends RecyclerView.Adapter<FirebaseDataListAdapter.MyViewHolder> {
    Context context;
    List<EventsModel> eventModelList;
    FirebaseDataInterface firebaseDataInterface;

    public FirebaseDataListAdapter(Context context, List<EventsModel> eventModelList, FirebaseDataInterface firebaseDataInterface) {
        this.context = context;
        this.eventModelList = eventModelList;
        this.firebaseDataInterface = firebaseDataInterface;
    }

    public void setData(List<EventsModel> eventModelList) {
        this.eventModelList = eventModelList;
        notifyDataSetChanged();
    }

    public interface FirebaseDataInterface {
        void onClickEvent(EventsModel model);
    }

    @NonNull
    @Override
    public FirebaseDataListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemsFirebaseDataBinding binding =  ItemsFirebaseDataBinding.inflate(LayoutInflater.from(context),viewGroup,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseDataListAdapter.MyViewHolder holder, int i) {
        holder.binding.txtTitle.setText(eventModelList.get(i).getTitle());
        holder.binding.txtDate.setText(eventModelList.get(i).getDate());
        holder.binding.txtNotes.setText(eventModelList.get(i).getNotes());

        holder.binding.cardViewItemMaster.setOnClickListener(v->{
            firebaseDataInterface.onClickEvent(eventModelList.get(i));
        });
    }

    @Override
    public int getItemCount() {
        return eventModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemsFirebaseDataBinding binding;
        public MyViewHolder(ItemsFirebaseDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
