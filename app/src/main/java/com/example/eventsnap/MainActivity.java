package com.example.eventsnap;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventsnap.ApiList.EventListUsingAPI;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivityMainBinding;
import com.example.eventsnap.util.AnalyticsLogger;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        context = MainActivity.this;

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               startActivityWithAnimastion(MainActivity.this, SignInActivity.class);
               finish();
           }
       },1000);

    }

}