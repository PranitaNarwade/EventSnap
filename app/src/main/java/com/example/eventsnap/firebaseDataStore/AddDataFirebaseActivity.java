package com.example.eventsnap.firebaseDataStore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivityAddDataFirebaseBinding;
import com.example.eventsnap.model.EventsModel;
import com.example.eventsnap.util.AnalyticsLogger;
import com.example.eventsnap.util.LocalNotificationUtil;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddDataFirebaseActivity extends BaseActivity {
    ActivityAddDataFirebaseBinding binding;
    Context context;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataFirebaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        context = AddDataFirebaseActivity.this;
        db = FirebaseFirestore.getInstance();


        binding.layoutDate.setEndIconOnClickListener(v -> {
            showDatePickerDialogFromCurrentDate(context,binding.etDate);
        });

        binding.btnSave.setOnClickListener(view->{
            String title = binding.etTitle.getText().toString().trim();
            String date = binding.etDate.getText().toString().trim();
            String notes = binding.etNotes.getText().toString().trim();

            if(title.isEmpty())
            {
                toastIconInfo(context,"Please enter the title");
                return;
            }

            if(date.isEmpty())
            {
                toastIconInfo(context,"Please enter the date");
                return;
            }

            addDataToFirestore(title,date,notes);

        });

        binding.btnEventsList.setOnClickListener(v->{
            startActivityWithAnimastion(this,FirebaseDataListActivity.class);
        });

    }

    private void addDataToFirestore(String title, String date, String notes) {

        showPrograssDialog(context);
        CollectionReference dbEvents = db.collection("Events");
        EventsModel model = new EventsModel(title, date, notes);

        dbEvents.add(model).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                hidePrograssDialog();
                binding.etTitle.setText("");
                binding.etDate.setText("");
                binding.etNotes.setText("");
                AnalyticsLogger.logEventAdded(context,documentReference.getId(),model.getTitle());
                toastIconSuccess(context,"Your Event has been added to Firebase Firestore");
                LocalNotificationUtil.show(
                        context,
                        "Event Added 🎉",
                        "Your event was added successfully"
                );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                hidePrograssDialog();
                FirebaseCrashlytics.getInstance().recordException(e);
                toastIconError(context,"Fail to add Event");
            }
        });
    }
}