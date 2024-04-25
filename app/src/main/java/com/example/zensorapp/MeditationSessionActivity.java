package com.example.zensorapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

public class MeditationSessionActivity extends AppCompatActivity {

    private TextView bpmDataView, oxygenDataView, perspirationDataView, emotionStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startmeditationsession);

        // Initialize the TextViews
        bpmDataView = findViewById(R.id.bpmDataView);
        oxygenDataView = findViewById(R.id.oxygenDataView);
        perspirationDataView = findViewById(R.id.perspirationDataView);
        //emotionStateView = findViewById(R.id.emotionStateView); // Make sure this ID exists in your XML

        // Reference to your Firebase path to the mock data
        DatabaseReference mockDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");

        // Attach a listener to read the data
        mockDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //String emotionState = snapshot.child("Emotion State").getValue(String.class);
                    Double heartRate = snapshot.child("Heart Rate").getValue(Double.class);
                    Double oxygenLevel = snapshot.child("Oxygen Level").getValue(Double.class);
                    Double perspiration = snapshot.child("Perspiration").getValue(Double.class);

                    // Update the TextViews with the data
                    //emotionStateView.setText("Emotion State: " + emotionState);
                    bpmDataView.setText("Heart Rate: " + formatDouble(heartRate));
                    oxygenDataView.setText("Oxygen Level: " + formatDouble(oxygenLevel));
                    perspirationDataView.setText("Perspiration: " + formatDouble(perspiration));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("MeditationSessionActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }

    private String formatDouble(Double value) {
        return value != null ? String.format("%.2f", value) : "--";
    }
}
