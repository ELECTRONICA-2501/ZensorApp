//package com.example.zensorapp;
//
//import android.os.Bundle;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import android.util.Log;
//
//public class MeditationSessionActivity extends AppCompatActivity {
//
//    private TextView bpmDataView, oxygenDataView, perspirationDataView, recommendedSessionView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.startmeditationsession);
//
//        // Initialize the TextViews
//        bpmDataView = findViewById(R.id.bpmDataView);
//        oxygenDataView = findViewById(R.id.oxygenDataView);
//        perspirationDataView = findViewById(R.id.perspirationDataView);
//
//        // Reference to your Firebase path to the mock data
//        DatabaseReference mockDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");
//
//        // Attach a listener to read the data
//        mockDataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //String emotionState = snapshot.child("Emotion State").getValue(String.class);
//                    Double heartRate = snapshot.child("Heart Rate").getValue(Double.class);
//                    Double oxygenLevel = snapshot.child("Oxygen Level").getValue(Double.class);
//                    Double perspiration = snapshot.child("Perspiration").getValue(Double.class);
//
//                    // Update the TextViews with the data
//                    //emotionStateView.setText("Emotion State: " + emotionState);
//                    bpmDataView.setText("Heart Rate: " + formatDouble(heartRate));
//                    oxygenDataView.setText("Oxygen Level: " + formatDouble(oxygenLevel));
//                    perspirationDataView.setText("Perspiration: " + formatDouble(perspiration));
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Failed to read value
//                Log.w("MeditationSessionActivity", "Failed to read value.", databaseError.toException());
//            }
//        });
//    }
//
//    private String formatDouble(Double value) {
//        return value != null ? String.format("%.2f", value) : "--";
//    }
//}

// Method to recommend a meditation session based on the emotion code
//    private void recommendMeditationSession(String emotionCode) {
//        String recommendedSession;
//        switch (emotionCode) {
//            case "Neutral Emotion":
//                recommendedSession = "Try a calming session to maintain your balance.";
//                break;
//            // Add more cases for different emotion codes
//            default:
//                recommendedSession = "Session recommendation not available.";
//                break;
//        }
//        recommendedSessionView.setText("Recommended Session: " + recommendedSession);
//    }

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

    private TextView bpmDataView, oxygenDataView, perspirationDataView, recommendedSessionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startmeditationsession);

        // Initialize the TextViews
        bpmDataView = findViewById(R.id.bpmDataView);
        oxygenDataView = findViewById(R.id.oxygenDataView);
        perspirationDataView = findViewById(R.id.perspirationDataView);
        recommendedSessionView = findViewById(R.id.recommendedSessionView); // ID for the recommended session TextView

        // Reference to your Firebase path to the sensor data
        DatabaseReference sensorDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");

        // Reference to your Firebase path to the emotion results
        DatabaseReference emotionResultsRef = FirebaseDatabase.getInstance().getReference().child("emotions_result");//.child("emotion_20240425121048");//.child("emotion_code");

        // Attach a listener to read the sensor data
        sensorDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Double heartRate = snapshot.child("Heart Rate").getValue(Double.class);
                    Double oxygenLevel = snapshot.child("Oxygen Level").getValue(Double.class);
                    Double perspiration = snapshot.child("Perspiration").getValue(Double.class);

                    // Update the TextViews with the data
                    bpmDataView.setText("Heart Rate: " + formatDouble(heartRate));
                    oxygenDataView.setText("Oxygen Level: " + formatDouble(oxygenLevel));
                    perspirationDataView.setText("Perspiration: " + formatDouble(perspiration));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("MeditationSessionActivity", "Failed to read sensor data.", databaseError.toException());
            }
        });

        // Attach a listener to read the emotion results data
        emotionResultsRef.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        String emotionCode = childSnapshot.child("emotion_code").getValue(String.class);
                        // Update the recommended meditation session based on emotion code
                        //recommendMeditationSession(emotionCode);
                        recommendedSessionView.setText("Recommended Meditation: " + emotionCode);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w("MeditationSessionActivity", "Failed to read emotion results.", databaseError.toException());
            }
        });
    }

    private String formatDouble(Double value) {
        return value != null ? String.format("%.2f", value) : "--";
    }


}

