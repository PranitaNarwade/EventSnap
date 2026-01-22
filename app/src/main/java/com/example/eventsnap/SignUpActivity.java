package com.example.eventsnap;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.example.eventsnap.base.BaseActivity;
import com.example.eventsnap.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;
    Context context;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        context = SignUpActivity.this;
        auth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener(v->{
            registerNewUser();
        });
    }

    private void registerNewUser() {
        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            toastIconInfo(context,"Please enter credentials");
            return;
        }

        if(password.length()<6)
        {
            toastIconInfo(context,"Password must contain more than 6 characters");
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            toastIconSuccess(context,"Registration successful!");
                            startActivityWithAnimastion(SignUpActivity.this,SignInActivity.class);;
                            finish();
                        } else {
                            toastIconError(SignUpActivity.this,"Registration failed! Please try again later");
                        }
                    }
                });

    }
}