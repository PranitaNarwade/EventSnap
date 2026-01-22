package com.example.eventsnap.ApiList;

import android.content.Context;

import com.example.eventsnap.model.TodoModel;
import com.example.eventsnap.network.API;
import com.example.eventsnap.network.ApiClientUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventPresenter implements EventPresentView {
    Context context;
    EventView eventView;

    public EventPresenter(Context context, EventView eventView) {
        this.context = context;
        this.eventView = eventView;
    }

    @Override
    public void getTodos() {
        API api = ApiClientUser.getClient().create(API.class);
        Call<TodoModel> call = api.todos();
        call.enqueue(new Callback<TodoModel>() {
            @Override
            public void onResponse(Call<TodoModel> call, Response<TodoModel> response) {
                try {
                    if (response.isSuccessful()) {
                        if(!response.body().getTodos().isEmpty()){
                            eventView.getTodosSuccess(response.body().getTodos());
                        }else {
                            eventView.noDataFound();
                        }
                    } else {
                        eventView.getTodoFailure();
                    }
                } catch (Exception e) {
                    FirebaseCrashlytics.getInstance().recordException(e);
                    eventView.getTodoFailure();
                }
            }

            @Override
            public void onFailure(Call<TodoModel> call, Throwable t) {
                FirebaseCrashlytics.getInstance().recordException(t);
                eventView.getTodoFailure();
            }
        });
    }

}
