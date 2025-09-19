package com.hostellite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);
        EditText confirmPassword = findViewById(R.id.editTextConfirmPassword);
        Button signupBtn = findViewById(R.id.buttonSignup);
        TextView loginLink = findViewById(R.id.textLogin);

        signupBtn.setOnClickListener(v -> {
            // For now, just go to MainActivity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        loginLink.setOnClickListener(v -> {
            finish();
        });
    }
}
