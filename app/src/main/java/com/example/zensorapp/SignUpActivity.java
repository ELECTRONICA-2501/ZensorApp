package com.example.zensorapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task; // Corrected: Added import for Task
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SignUpActivity extends AppCompatActivity {

    // Widgets
    EditText password_create, email_create, username_create;
    Button createBTN;

    // Firebase Auth
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    // FAirebase Firestore connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createBTN = findViewById(R.id.acc_sign_up_button);
        password_create = findViewById(R.id.password_create);
        email_create = findViewById(R.id.email_create);
        username_create = findViewById(R.id.username_create_ET);

        // Firebase Auth initialization
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
            }
        };

        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(email_create.getText().toString())
                        && !TextUtils.isEmpty(username_create.getText().toString())
                        && !TextUtils.isEmpty(password_create.getText().toString())) { // Corrected: Added missing parenthesis

                    String email = email_create.getText().toString().trim();
                    String pass = password_create.getText().toString().trim();
                    String username = username_create.getText().toString().trim();

                    CreateUserEmailAccount(email, pass, username);
                } else {
                    Toast.makeText(SignUpActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CreateUserEmailAccount(String email, String pass, String username) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(username)) {
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // User created successfully
                                Toast.makeText(SignUpActivity.this, "Account created Successfully", Toast.LENGTH_SHORT).show();

                                // Navigate to the DashboardPhaseListActivity
                                Intent intent = new Intent(SignUpActivity.this, DashboardPhaseListActivity.class);
                                startActivity(intent);
                                finish(); // Finish the current activity
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(SignUpActivity.this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
        }
    }

}
//package com.example.zensorapp;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//import android.widget.Button;
//import android.content.Intent;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//public class SignUpActivity extends AppCompatActivity {
//
//    // Widgets
//    EditText password_create, email_create, username_create;
//    Button createBTN;
//
//    // Firebase Auth
//    private FirebaseAuth firebaseAuth;
//
//    // Firebase Firestore connection
//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private CollectionReference collectionReference = db.collection("Users");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        createBTN = findViewById(R.id.acc_sign_up_button);
//        password_create = findViewById(R.id.password_create);
//        email_create = findViewById(R.id.email_create);
//        username_create = findViewById(R.id.username_create_ET);
//
//        // Firebase Auth initialization
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        createBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = email_create.getText().toString().trim();
//                String pass = password_create.getText().toString().trim();
//                String username = username_create.getText().toString().trim();
//
//                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(username)) {
//                    createUserEmailAccount(email, pass, username);
//                } else {
//                    Toast.makeText(SignUpActivity.this, "Empty Fields Not Allowed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void createUserEmailAccount(String email, String pass, String username) {
//        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
//            if (task.isSuccessful()) {
//                // User created successfully
//                Toast.makeText(SignUpActivity.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
//
//                // Optionally store additional user data in Firestore using `username`
//                storeUserData(username);
//
//                // Navigate to the DashboardPhaseListActivity
//                Intent intent = new Intent(SignUpActivity.this, DashboardPhaseListActivity.class);
//                startActivity(intent);
//                finish(); // Finish the current activity
//            } else {
//                // If sign in fails, display a message to the user.
//                Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void storeUserData(String username) {
//        if (firebaseAuth.getCurrentUser() != null) {
//            String userId = firebaseAuth.getCurrentUser().getUid();
//            // Store other user info like username in Firestore
//            collectionReference.document(userId).set(new User(username));
//        }
//    }
//
//    static class User {
//        public String username;
//
//        public User(String username) {
//            this.username = username;
//        }
//    }
//}

