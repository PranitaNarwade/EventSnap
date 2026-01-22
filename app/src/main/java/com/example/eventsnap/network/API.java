package com.example.eventsnap.network;

import com.example.eventsnap.model.TodoModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("todos")
    Call<TodoModel> todos();
}
