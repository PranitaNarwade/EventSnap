package com.example.eventsnap.base;

import android.content.Context;

public interface BaseInterface {
    void showPrograssDialog(Context ctx);
    void hidePrograssDialog();
    void toastIconError(Context context,String msg);
    void toastIconSuccess(Context context,String msg) ;
    void toastIconInfo(Context context,String msg);

}
