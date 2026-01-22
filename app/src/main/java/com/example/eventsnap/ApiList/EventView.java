package com.example.eventsnap.ApiList;

import com.example.eventsnap.model.TodoModel;

import java.util.List;

public interface EventView {
    void noDataFound();
    void getTodosSuccess(List<TodoModel> todos);
    void getTodoFailure();
}
