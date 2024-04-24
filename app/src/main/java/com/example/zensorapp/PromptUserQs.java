package com.example.zensorapp;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.zensorapp.R;

import java.util.ArrayList;

public class PromptUserQs extends AppCompatActivity {

    private ListView listView;
    private Button Anxiety, Sadness, Happiness, Neutral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt_user_qs);

        // Initialize UI components for displaying the sensor data and emotion buttons
        listView = findViewById(R.id.listView);
        Anxiety = findViewById(R.id.Anxiety);
        Sadness = findViewById(R.id.Sadness);
        Happiness = findViewById(R.id.Happiness);
        Neutral = findViewById(R.id.Neutral);

        // Setup button listeners for each emotion which will update Firebase
        setupButtonListeners();

        // Setup the ListView with an ArrayAdapter to display sensor readings
        final ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        // Firebase reference pointing to "SensorReadings"
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("SensorReadings");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear(); // Clear the existing list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue().toString()); // Add new data to the list
                }
                adapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PromptUserQs.this, "Failed to read data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupButtonListeners() {
        // Setup listeners for each button, recording the emotion to Firebase when clicked
        Anxiety.setOnClickListener(v -> recordEmotion("Anxiety"));
        Sadness.setOnClickListener(v -> recordEmotion("Sadness"));
        Happiness.setOnClickListener(v -> recordEmotion("Happiness"));
        Neutral.setOnClickListener(v -> recordEmotion("Neutral"));
    }

    private void recordEmotion(String emotion) {
        // This function pushes the selected emotion to Firebase under the corresponding child node
        FirebaseDatabase.getInstance().getReference().child(emotion).push().child("Emotion").setValue(emotion);
        Toast.makeText(this, "Emotion " + emotion + " selected.", Toast.LENGTH_SHORT).show();
    }
}
