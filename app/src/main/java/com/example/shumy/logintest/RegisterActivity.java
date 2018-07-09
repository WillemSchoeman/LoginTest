package com.example.shumy.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    Button submitButton;
    EditText enterUsernameText;
    EditText enterEmailText;
    EditText enterPasswordText;
    EditText repeatPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        submitButton = findViewById(R.id.submit_button);
        enterUsernameText = findViewById(R.id.enter_user_editText);
        enterEmailText = findViewById(R.id.email_editText);
        enterPasswordText = findViewById(R.id.create_password_editText);
        repeatPasswordText = findViewById(R.id.repeat_password_editText);

    }
}
