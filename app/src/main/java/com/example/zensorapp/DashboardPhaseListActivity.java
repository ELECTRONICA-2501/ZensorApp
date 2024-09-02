package com.example.zensorapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardPhaseListActivity extends AppCompatActivity {

    private Button startTrainingSessionButton;
    private Button startMeditationSessionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard_phase_list);

        // Setup the main layout to handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets; // It's important to return the insets result
        });

        // Initialize and set up the Start Session button
        startMeditationSessionButton = findViewById(R.id.startMeditationButton);
        startMeditationSessionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Session started!", Toast.LENGTH_SHORT).show();
            // Navigate to PromptUserQs activity to handle session functionality
            Intent intent = new Intent(DashboardPhaseListActivity.this, MeditationSessionActivity.class);
            startActivity(intent);
        });

        //takes you to promptUserQs activity
        startTrainingSessionButton = findViewById(R.id.startSessionButton);
        startTrainingSessionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Session started!", Toast.LENGTH_SHORT).show();
            // Navigate to PromptUserQs activity to handle session functionality
            Intent intent = new Intent(DashboardPhaseListActivity.this, PromptUserQs.class);
            startActivity(intent);
        });
    }
}
