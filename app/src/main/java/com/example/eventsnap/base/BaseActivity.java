package com.example.eventsnap.base;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.eventsnap.R;
import com.example.eventsnap.util.Tools;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class BaseActivity extends AppCompatActivity implements BaseInterface{
    public static ProgressDialog progressDialog;

    @Override
    public void showPrograssDialog(Context ctx) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(ctx, "", "Please Wait", true);
            progressDialog.setContentView(R.layout.progress_dialog);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void hidePrograssDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void toastIconError(Context context, String msg) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(msg);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close_round);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.red_600));

        toast.setView(custom_view);
        toast.show();
    }

    @Override
    public void toastIconSuccess(Context context, String msg) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(msg);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    @Override
    public void toastIconInfo(Context context, String msg) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);

        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(msg);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.blue_500));

        toast.setView(custom_view);
        toast.show();
    }

    public void startActivityWithAnimastion(AppCompatActivity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.transition.slide_from_right, R.transition.slide_to_left);
    }

    public void startActivityWithAnimastionData(AppCompatActivity activity, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        overridePendingTransition(R.transition.slide_from_right, R.transition.slide_to_left);
    }

    public void showDatePickerDialogFromCurrentDate(Context context, TextInputEditText editText) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        editText.setText(Tools.getDateDDMMMYYYYFormat(calendar.getTime()));

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
