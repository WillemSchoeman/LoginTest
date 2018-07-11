package com.example.shumy.logintest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private Button registerButton;
    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.login_button);
        emailText = findViewById(R.id.email_text);
        passwordText = findViewById(R.id.password_text);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    // Executed when Register button pressed

    public void registerNewUser() {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        finish();
        startActivity(registerIntent);
    }

    private void attemptLogin() {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty())
            if (email.equals("") || password.equals("")) return;
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d(TAG, "signInWithEmail() onComplete: " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.d(TAG, "Problem Signing in: " + task.getException());
                    showErrorDialog("There was a problem signing in");
                } else {
                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(loginIntent);
                }
            }
        });
    }

    private void showErrorDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
