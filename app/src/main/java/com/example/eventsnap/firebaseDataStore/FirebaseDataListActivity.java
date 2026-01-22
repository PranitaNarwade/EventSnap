package com.example.eventsnap.firebaseDataStore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eventsnap.adapter.FirebaseDataListAdapter;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivityFirebaseDataListBinding;
import com.example.eventsnap.model.EventsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDataListActivity extends BaseActivity implements FirebaseDataListAdapter.FirebaseDataInterface {

    ActivityFirebaseDataListBinding binding;
    Context context;
    List<EventsModel> eventModelList;
    FirebaseDataListAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirebaseDataListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        context = FirebaseDataListActivity.this;
        eventModelList = new ArrayList<>();
        adapter = new FirebaseDataListAdapter(context,eventModelList,this);
        binding.rvEvents.setLayoutManager(new LinearLayoutManager(context));
        binding.rvEvents.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        showPrograssDialog(context);

        db.collection("Events").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            eventModelList.clear();
                            for (DocumentSnapshot d : list) {
                                EventsModel eventModel = d.toObject(EventsModel.class);
                                eventModel.setEventId(d.getId());
                                eventModelList.add(eventModel);
                            }
                            adapter.setData(eventModelList);
                            hidePrograssDialog();
                        } else {
                            hidePrograssDialog();
                            toastIconInfo(context,"No data found in Database");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        hidePrograssDialog();
                        FirebaseCrashlytics.getInstance().recordException(e);
                        toastIconError(context,"Fail to get the data.");
                    }
                });
    }

    @Override
    public void onClickEvent(EventsModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("event_id", model.getEventId());
        startActivityWithAnimastionData(this,EventDetailsActivity.class,bundle);
    }
}