package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void  onClickPlay(View view){
        // Create an Intent object to navigate from the current Activity to GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        // Start the target activity to implement page redirection
        startActivity(intent);
    }

    public void onClickLeaderboard(View view){
        // Create an Intent object to navigate from the current Activity to LeaderboardActivity
        Intent intent = new Intent(this, LeaderboardActivity.class);
        // Start the target activity to implement page redirection
        startActivity(intent);
    }
}