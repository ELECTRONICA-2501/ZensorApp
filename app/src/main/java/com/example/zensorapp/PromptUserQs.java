//package com.example.zensorapp;
//
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.example.zensorapp.R;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class PromptUserQs extends AppCompatActivity {
//
//
//    private Button Anxiety, Sadness, Happiness, Neutral;
//
//    DatabaseReference sensorDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");
//    private TextView bpmDataView, oxygenDataView, perspirationDataView, emotionStateView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_prompt_user_qs);
//
//        // Initialize UI components for displaying the sensor data and emotion buttons
//
//        Anxiety = findViewById(R.id.Anxiety);
//        Sadness = findViewById(R.id.Sadness);
//        Happiness = findViewById(R.id.Happiness);
//        Neutral = findViewById(R.id.Neutral);
//        // Initialize the TextViews
//        bpmDataView = findViewById(R.id.bpmDataView);
//        oxygenDataView = findViewById(R.id.oxygenDataView);
//        perspirationDataView = findViewById(R.id.perspirationDataView);
//
//        // Setup button listeners for each emotion which will update Firebase
//        setupButtonListeners();
//
//        // Setup the ListView with an ArrayAdapter to display sensor readings
//
//        // Firebase reference pointing to "SensorReadings"
//        DatabaseReference sensorReadings = FirebaseDatabase.getInstance().getReference().child("sensor_data");
//        sensorReadings.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    //String emotionState = snapshot.child("Emotion State").getValue(String.class);
//                    Double heartRate = snapshot.child("Heart Rate").getValue(Double.class);
//                    Double oxygenLevel = snapshot.child("Oxygen Level").getValue(Double.class);
//                    Double perspiration = snapshot.child("Perspiration").getValue(Double.class);
//
//                    // Update the TextViews with the data
//                    //emotionStateView.setText("Emotion State: " + emotionState);
//                    bpmDataView.setText("Heart Rate: " + heartRate);
//                    oxygenDataView.setText("Oxygen Level: " + oxygenLevel);
//                    perspirationDataView.setText("Perspiration: " + perspiration);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(PromptUserQs.this, "Failed to read data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
////    private void setupButtonListeners() {
////        // Setup listeners for each button, recording the emotion to Firebase when clicked
////        Anxiety.setOnClickListener(v -> recordEmotion("Anxiety"));
////        Sadness.setOnClickListener(v -> recordEmotion("Sadness"));
////        Happiness.setOnClickListener(v -> recordEmotion("Happiness"));
////        Neutral.setOnClickListener(v -> recordEmotion("Neutral"));
////    }
//private void setupButtonListeners() {
//    // Setup listeners for each button, recording the emotion and sensor data to Firebase when clicked
//    Anxiety.setOnClickListener(v -> recordEmotion("Anxiety", getHeartRate(), getOxygenLevel(), getPerspiration()));
//    Sadness.setOnClickListener(v -> recordEmotion("Sadness", getHeartRate(), getOxygenLevel(), getPerspiration()));
//    Happiness.setOnClickListener(v -> recordEmotion("Happiness", getHeartRate(), getOxygenLevel(), getPerspiration()));
//    Neutral.setOnClickListener(v -> recordEmotion("Neutral", getHeartRate(), getOxygenLevel(), getPerspiration()));
//}
//
//    // Define methods to get the sensor data values (replace these with your actual methods)
//    private Double getHeartRate(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Heart Rate").getValue(Double.class);
//
//    }
//
//    private Double getOxygenLevel(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Oxygen Level").getValue(Double.class);
//
//    }
//
//    private Double getPerspiration(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Perspiration").getValue(Double.class);
//
//    }
//
//
//    //    private void recordEmotion(String emotion) {
////        // This function pushes the selected emotion to Firebase under the corresponding child node
////        FirebaseDatabase.getInstance().getReference().child(emotion).push().child("Emotion").setValue(emotion);
////        Toast.makeText(this, "Emotion " + emotion + " selected. Data point used to train our ML model", Toast.LENGTH_SHORT).show();
////    }
//private void recordEmotion(String emotion, Double heartRate, Double oxygenLevel, Double perspiration) {
//    // This function pushes the selected emotion and sensor data to Firebase under the corresponding child node
//    DatabaseReference emotionRef = FirebaseDatabase.getInstance().getReference().child(emotion);
//    String key = emotionRef.push().getKey();
//    if (key != null) {
//        DatabaseReference childRef = emotionRef.child(key);
//        childRef.child("Emotion").setValue(emotion);
//        childRef.child("Heart Rate").setValue(heartRate);
//        childRef.child("Oxygen Level").setValue(oxygenLevel);
//        childRef.child("Perspiration").setValue(perspiration);
//        Toast.makeText(this, "Emotion " + emotion + " selected. Data point used to train our ML model", Toast.LENGTH_SHORT).show();
//    } else {
//        Toast.makeText(this, "Failed to push data to Firebase.", Toast.LENGTH_SHORT).show();
//    }
//}
//
//    private String formatDouble(Double value) {
//        return value != null ? String.format("%.2f", value) : "--";
//    }
//
//}


//package com.example.zensorapp;
//
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class PromptUserQs extends AppCompatActivity {
//
//    private Button Anxiety, Sadness, Happiness, Neutral;
//    private DatabaseReference sensorDataRef;
//    private TextView bpmDataView, oxygenDataView, perspirationDataView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_prompt_user_qs);
//
//        // Initialize UI components
//        Anxiety = findViewById(R.id.Anxiety);
//        Sadness = findViewById(R.id.Sadness);
//        Happiness = findViewById(R.id.Happiness);
//        Neutral = findViewById(R.id.Neutral);
//        bpmDataView = findViewById(R.id.bpmDataView);
//        oxygenDataView = findViewById(R.id.oxygenDataView);
//        perspirationDataView = findViewById(R.id.perspirationDataView);
//
//        // Firebase reference pointing to "SensorReadings"
//        sensorDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");
//
//        // Setup button listeners
//        setupButtonListeners();
//
//        // Read initial data
//        readSensorData();
//    }
//
//    private void setupButtonListeners() {
//        Anxiety.setOnClickListener(v -> recordEmotion("Anxiety"));
//        Sadness.setOnClickListener(v -> recordEmotion("Sadness"));
//        Happiness.setOnClickListener(v -> recordEmotion("Happiness"));
//        Neutral.setOnClickListener(v -> recordEmotion("Neutral"));
//    }
//
//    private void readSensorData() {
//        sensorDataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Read sensor data when onDataChange is triggered
//                Double heartRate = getHeartRate(dataSnapshot);
//                Double oxygenLevel = getOxygenLevel(dataSnapshot);
//                Double perspiration = getPerspiration(dataSnapshot);
//
//                // Update UI with the latest sensor data
//                bpmDataView.setText("Heart Rate: " + formatDouble(heartRate));
//                oxygenDataView.setText("Oxygen Level: " + formatDouble(oxygenLevel));
//                perspirationDataView.setText("Perspiration: " + formatDouble(perspiration));
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(PromptUserQs.this, "Failed to read data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void recordEmotion(String emotion) {
//        // This function pushes the selected emotion and sensor data to Firebase under the corresponding child node
//        String key = sensorDataRef.push().getKey();
//        if (key != null) {
//            DatabaseReference childRef = sensorDataRef.child(key);
//            childRef.child("Emotion").setValue(emotion);
//            // Use the latest snapshot of sensor data
//            childRef.child("Heart Rate").setValue(getHeartRate(sensorDataRef));
//            childRef.child("Oxygen Level").setValue(getOxygenLevel(sensorDataRef));
//            childRef.child("Perspiration").setValue(getPerspiration(sensorDataRef));
//            Toast.makeText(this, "Emotion " + emotion + " selected. Data point used to train our ML model", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to push data to Firebase.", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private Double getHeartRate(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Heart Rate").getValue(Double.class);
//    }
//
//    private Double getOxygenLevel(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Oxygen Level").getValue(Double.class);
//    }
//
//    private Double getPerspiration(DataSnapshot dataSnapshot) {
//        return dataSnapshot.child("Perspiration").getValue(Double.class);
//    }
//
//    private String formatDouble(Double value) {
//        return value != null ? String.format("%.2f", value) : "--";
//    }
//}

package com.example.zensorapp;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.zensorapp.R;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PromptUserQs extends AppCompatActivity {


    private Button Anxiety, Sadness, Happiness, Neutral;

    public String heartRate1, oxygenLevel1, perspiration1;

    DatabaseReference sensorDataRef = FirebaseDatabase.getInstance().getReference().child("sensor_data");
    private TextView bpmDataView, oxygenDataView, perspirationDataView, emotionStateView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_user_qs);

        // Initialize UI components for displaying the sensor data and emotion buttons

        Anxiety = findViewById(R.id.Anxiety);
        Sadness = findViewById(R.id.Sadness);
        Happiness = findViewById(R.id.Happiness);
        Neutral = findViewById(R.id.Neutral);
        // Initialize the TextViews
        bpmDataView = findViewById(R.id.bpmDataView);
        oxygenDataView = findViewById(R.id.oxygenDataView);
        perspirationDataView = findViewById(R.id.perspirationDataView);

        // Setup button listeners for each emotion which will update Firebase
        setupButtonListeners();

        // Setup the ListView with an ArrayAdapter to display sensor readings

        // Firebase reference pointing to "SensorReadings"
        DatabaseReference sensorReadings = FirebaseDatabase.getInstance().getReference().child("sensor_data");
        sensorReadings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //String emotionState = snapshot.child("Emotion State").getValue(String.class);
                    Double heartRate = snapshot.child("Heart Rate").getValue(Double.class);
                    Double oxygenLevel = snapshot.child("Oxygen Level").getValue(Double.class);
                    Double perspiration = snapshot.child("Perspiration").getValue(Double.class);

                    // Update the TextViews with the data
                    //emotionStateView.setText("Emotion State: " + emotionState);
                    bpmDataView.setText("Heart Rate: " + heartRate);
                    heartRate1 = heartRate.toString();
                    oxygenDataView.setText("Oxygen Level: " + oxygenLevel);
                    oxygenLevel1 = oxygenLevel.toString();
                    perspirationDataView.setText("Perspiration: " + perspiration);
                    perspiration1 = perspiration.toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PromptUserQs.this, "Failed to read data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void recordEmotion(String emotion, Double heartRate, Double oxygenLevel, Double perspiration) {
        // This function pushes the selected emotion and sensor data to Firebase under the corresponding child node
        DatabaseReference emotionRef = FirebaseDatabase.getInstance().getReference().child(emotion);
        String key = emotionRef.push().getKey();
        if (key != null) {
            DatabaseReference childRef = emotionRef.child(key);
            childRef.child("Emotion").setValue(emotion);
            childRef.child("Heart Rate").setValue(heartRate);
            childRef.child("Oxygen Level").setValue(oxygenLevel);
            childRef.child("Perspiration").setValue(perspiration);
            Toast.makeText(this, "Emotion " + emotion + " selected. Data point used to train our ML model", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to push data to Firebase.", Toast.LENGTH_SHORT).show();
        }
    }


    private void setupButtonListeners() {
        // Setup listeners for each button, recording the emotion to Firebase when clicked
        Anxiety.setOnClickListener(v -> recordEmotion("Anxiety"));
        Sadness.setOnClickListener(v -> recordEmotion("Sadness"));
        Happiness.setOnClickListener(v -> recordEmotion("Happiness"));
        Neutral.setOnClickListener(v -> recordEmotion("Neutral"));
    }
    // Define methods to get the sensor data values (replace these with your actual methods)
    private Double getHeartRate() {
        // Implement your logic to get the heart rate value
        return 0.0; // Replace with the actual value
    }

    private Double getOxygenLevel() {
        // Implement your logic to get the oxygen level value
        return 0.0; // Replace with the actual value
    }

    private Double getPerspiration() {
        // Implement your logic to get the perspiration value
        return 0.0; // Replace with the actual value
    }

    private void recordEmotion(String emotion) {

        Map<String, Object> emotionData = new HashMap<>();
        emotionData.put("Emotion", emotion);
        emotionData.put("HeartRate", heartRate1);
        emotionData.put("Oxygen", oxygenLevel1);
        emotionData.put("Perspiration", perspiration1);

        // This function pushes the selected emotion to Firebase under the corresponding child node
        FirebaseDatabase.getInstance().getReference().child(emotion).push().child("Data").setValue(emotionData);
        Toast.makeText(this, "Emotion " + emotion + " selected. Data point used to train our ML model", Toast.LENGTH_SHORT).show();
    }
    private String formatDouble(Double value) {
        return value != null ? String.format("%.2f", value) : "--";
    }

}


