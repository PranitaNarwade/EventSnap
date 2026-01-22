package com.example.eventsnap.ApiList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eventsnap.adapter.EventAPIListAdapter;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.firebaseDataStore.AddDataFirebaseActivity;
import com.example.eventsnap.model.TodoModel;
import com.example.eventsnap.util.NetworkUtil;
import com.example.eventsnap.databinding.ActivityEventListUsingApiBinding;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appcheck.interop.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class EventListUsingAPI extends BaseActivity implements EventView {

    ActivityEventListUsingApiBinding binding;
    Context context;
    EventPresenter presenter;
    List<TodoModel> todoModelList;
    EventAPIListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventListUsingApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        context = EventListUsingAPI.this;
        presenter = new EventPresenter(context,this);
        todoModelList = new ArrayList<>();
        adapter = new EventAPIListAdapter(context,todoModelList);
        binding.rvEvents.setLayoutManager(new LinearLayoutManager(context));
        binding.rvEvents.setAdapter(adapter);

        Button crashButton = new Button(this);
        crashButton.setText("Test Crash");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        logCustomAppOpen();

        binding.fabAdd.setOnClickListener(view->{
            startActivityWithAnimastion(this, AddDataFirebaseActivity.class);
        });

        binding.showHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    binding.fabAdd.setVisibility(View.VISIBLE);
                else
                    binding.fabAdd.setVisibility(View.GONE);
            }
        });

        getTodo();

    }

    private void logCustomAppOpen() {
        Bundle bundle = new Bundle();
        bundle.putString("app_version", BuildConfig.VERSION_NAME);
        bundle.putString("open_source", "launcher");

        FirebaseAnalytics.getInstance(context)
                .logEvent("custom_app_open", bundle);
    }

    private void getTodo() {
        if (NetworkUtil.isNetworkAvailable(context)) {
            showPrograssDialog(context);
            presenter.getTodos();
        } else {
            toastIconInfo(context, "No Internet Connection");
        }
    }


    @Override
    public void noDataFound() {
        hidePrograssDialog();
        toastIconInfo(context, "No data found");
    }

    @Override
    public void getTodosSuccess(List<TodoModel> todos) {
        hidePrograssDialog();
        todoModelList.clear();
        todoModelList.addAll(todos);
        adapter.setData(todoModelList);
    }

    @Override
    public void getTodoFailure() {
        hidePrograssDialog();
        toastIconError(context, "Slow internet speed");
    }

}