package com.example.eventsnap.firebaseDataStore;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventsnap.R;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivityEventDetailsBinding;
import com.example.eventsnap.model.EventsModel;
import com.example.eventsnap.util.AnalyticsLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventDetailsActivity extends BaseActivity {

    ActivityEventDetailsBinding binding;
    Context context;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = EventDetailsActivity.this;

        db = FirebaseFirestore.getInstance();

        String eventId = getIntent().getStringExtra("event_id");

        if (eventId != null) {
            fetchEventDetails(eventId);
        }
    }

    private void fetchEventDetails(String eventId) {

        db.collection("Events")
                .document(eventId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    if (documentSnapshot.exists()) {

                        EventsModel event = documentSnapshot.toObject(EventsModel.class);

                        if (event != null) {
                            showEventData(event);
                            AnalyticsLogger.logEventViewed(context,eventId, event.getTitle());
                        }
                    }
                })
                .addOnFailureListener(e ->{
                    FirebaseCrashlytics.getInstance().recordException(e);
                    toastIconError(context,"Failed to load event");
               });
    }

    private void showEventData(EventsModel event) {
        binding.txtTitle.setText(event.getTitle());
        binding.txtDate.setText(event.getDate());
        binding.txtNotes.setText(event.getNotes());
    }

}