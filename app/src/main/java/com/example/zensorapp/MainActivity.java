package com.example.zensorapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    // Widgets
    Button loginBtn, createAccountBtn;
    private EditText emailET, passwordET;

    // Firebase Auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Removed EdgeToEdge.enable(this); as it might not be necessary or might need to be handled differently

        createAccountBtn = findViewById(R.id.create_account);

        // Corrected typo in variable name and added closing parenthesis and semicolon
        createAccountBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(i);
        }); // Corrected by adding this closing parenthesis and semicolon

        //login
        loginBtn = findViewById(R.id.email_sign_in_button);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);

        // Firebase Auth initialization
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(v ->{
                logEmailPassUser(
                        emailET.getText().toString().trim(),
                        passwordET.getText().toString().trim()
                );
        });
        // Adjusting window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void logEmailPassUser(String email, String password) {
        // Implement the logic to log in the user using email and password
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Intent i = new Intent(MainActivity.this, DashboardPhaseListActivity.class);
                            startActivity(i);

                        }
                    });

        }
    }
}
