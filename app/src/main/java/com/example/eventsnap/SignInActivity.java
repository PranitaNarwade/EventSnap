package com.example.eventsnap;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventsnap.ApiList.EventListUsingAPI;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends BaseActivity {
    private ActivitySignInBinding binding;
    private Context context;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        context = SignInActivity.this;
        auth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(v->{
            loginUserAccount();
        });

        binding.btnSignUp.setOnClickListener(v->{
            startActivityWithAnimastion(this, SignUpActivity.class);
        });
    }

    private void loginUserAccount() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            toastIconInfo(context,"Please enter credentials");
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            toastIconSuccess(context, "Login successful!!");
                            startActivityWithAnimastion(SignInActivity.this, EventListUsingAPI.class);
                            finish();
                        } else {
                            toastIconError(context,"Login failed!!");
                        }
                    }
                });

    }
}