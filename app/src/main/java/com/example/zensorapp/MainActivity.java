//package com.example.zensorapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.EditText;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    // Widgets
//    Button loginBtn, createAccountBtn;
//    private EditText emailET, passwordET;
//
//    // Firebase Auth
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener authStateListener;
//    private FirebaseUser currentUser;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); // Removed EdgeToEdge.enable(this); as it might not be necessary or might need to be handled differently
//
//        createAccountBtn = findViewById(R.id.create_account);
//
//        // Corrected typo in variable name and added closing parenthesis and semicolon
//        createAccountBtn.setOnClickListener(v -> {
//            Intent i = new Intent(MainActivity.this, SignUpActivity.class);
//            startActivity(i);
//        }); // Corrected by adding this closing parenthesis and semicolon
//
//        //login
//        loginBtn = findViewById(R.id.email_sign_in_button);
//        emailET = findViewById(R.id.email);
//        passwordET = findViewById(R.id.password);
//
//        // Firebase Auth initialization
//        firebaseAuth = FirebaseAuth.getInstance();
//        loginBtn.setOnClickListener(v ->{
//                logEmailPassUser(
//                        emailET.getText().toString().trim(),
//                        passwordET.getText().toString().trim()
//                );
//        });
//        // Adjusting window insets
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//    private void logEmailPassUser(String email, String password) {
//        // Implement the logic to log in the user using email and password
//        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
//            firebaseAuth.signInWithEmailAndPassword(email, password)
//                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                            Intent i = new Intent(MainActivity.this, DashboardPhaseListActivity.class);
//                            startActivity(i);
//
//                        }
//                    });
//
//        }
//    }
//}
package com.example.zensorapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn, createAccountBtn;
    private EditText emailET, passwordET;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            navigateToDashboard();
        }

        loginBtn = findViewById(R.id.email_sign_in_button);
        createAccountBtn = findViewById(R.id.create_account);
        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);

        createAccountBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignUpActivity.class)));
        loginBtn.setOnClickListener(v -> logInUser(emailET.getText().toString().trim(), passwordET.getText().toString().trim()));
    }

    private void logInUser(String email, String password) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, DashboardPhaseListActivity.class);
                } else {
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Email and password must not be empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(MainActivity.this, DashboardPhaseListActivity.class);
        startActivity(intent);
        finish();
    }
}
