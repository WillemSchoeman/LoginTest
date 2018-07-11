package com.example.shumy.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Button submitButton;
    private EditText enterUsernameText;
    private EditText enterEmailText;
    private EditText enterPasswordText;
    private EditText repeatPasswordText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = findViewById(R.id.submit_button);
        enterUsernameText = findViewById(R.id.enter_user_editText);
        enterEmailText = findViewById(R.id.email_editText);
        enterPasswordText = findViewById(R.id.create_password_editText);
        repeatPasswordText = findViewById(R.id.repeat_password_editText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void attemptRegistration() {

        String username = enterUsernameText.getText().toString();
        String email = enterEmailText.getText().toString();
        String password = enterPasswordText.getText().toString();
        String repeatPassword = repeatPasswordText.getText().toString();

        View focusView = null;
        boolean cancel = false;

        // Check if username is valid

        if(TextUtils.isEmpty(username)) {
            enterUsernameText.setError("Username is required.");
            focusView = enterUsernameText;
            cancel = true;
        }

        // Check if email is valid

        if(TextUtils.isEmpty(email)) {
            enterEmailText.setError("Email is required.");
            focusView = enterEmailText;
            cancel = true;
        }
        else if(!isEmailValid(email)) {
            enterEmailText.setError("This email is not valid");
            focusView = enterEmailText;
            cancel = true;
        }

        // Check if password is valid

        if(!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            enterPasswordText.setError("Password not valid or does not match.");
            focusView = enterPasswordText;
            cancel = true;
        }

        if(cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {
            createFirebaseUser();
        }
    }




    
}




















