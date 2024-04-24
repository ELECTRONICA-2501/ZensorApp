package com.example.zensorapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeditationSessionActivity extends AppCompatActivity {

    private static final String TAG = "MeditationSessionActivity"; // Ensure you have this TAG declared if you are using it in Log.w

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.startmeditationsession);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        Thread thread = new Thread(this::basicReadSensors);
        thread.start();
    }

    public void basicReadSensors() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Readings");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bpm = dataSnapshot.child("BPM").getValue(String.class);
                String oxygen = dataSnapshot.child("Oxygen").getValue(String.class);
                String perspiration = dataSnapshot.child("Perspiration").getValue(String.class);

                TextView bpmData = findViewById(R.id.bpmData);
                TextView oxygenData = findViewById(R.id.oxygenData);
                TextView perspirationData = findViewById(R.id.perspirationData);

                runOnUiThread(() -> {
                    bpmData.setText(bpm);
                    oxygenData.setText(oxygen);
                    perspirationData.setText(perspiration);
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
