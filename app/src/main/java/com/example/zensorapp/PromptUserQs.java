package com.example.zensorapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
//import firebase realtimedatabase
import com.google.firebase.database.FirebaseDatabase;

public class PromptUserQs extends AppCompatActivity {

    private TextView bpmData, oxygenData, perspirationData;
    private RadioGroup emotionGroup;
    private Button submitButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prompt_user_qs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Initialize UI components
        bpmData = findViewById(R.id.bpmData);
        oxygenData = findViewById(R.id.oxygenData);
        perspirationData = findViewById(R.id.perspirationData);
        emotionGroup = findViewById(R.id.emotionGroup);
        submitButton = findViewById(R.id.submitButton);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Set up button click listener
        submitButton.setOnClickListener(v -> handleSubmit());
    }

    private void handleSubmit() {
        int selectedId = emotionGroup.getCheckedRadioButtonId();
        // Check if any radio button is selected
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an emotion.", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedEmotion = selectedRadioButton.getText().toString();
        String bpm = bpmData.getText().toString();
        String oxygen = oxygenData.getText().toString();
        String perspiration = perspirationData.getText().toString();

        SensorData sensorData = new SensorData(bpm, oxygen, perspiration, selectedEmotion);

        db.collection("SensorReadings").add(sensorData)
                .addOnSuccessListener(documentReference -> Toast.makeText(PromptUserQs.this, "Data saved successfully!", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(PromptUserQs.this, "Error saving data.", Toast.LENGTH_SHORT).show());
    }

    static class SensorData {
        String bpm, oxygen, perspiration, emotion;

        public SensorData(String bpm, String oxygen, String perspiration, String emotion) {
            this.bpm = bpm;
            this.oxygen = oxygen; // Corrected a potential typo here
            this.perspiration = perspiration;
            this.emotion = emotion;
        }
    }
}
