package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerActivity extends AppCompatActivity {

    private TextView tv_playerscore;
    private EditText et_playername;
    private RadioGroup rg_avatar;
    private int finalScore;
    private Leaderboard leaderboardInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        tv_playerscore = findViewById(R.id.tv_playerscore);
        et_playername = findViewById(R.id.et_playername);
        rg_avatar = findViewById(R.id.rg_avator);

    }

    public void onclickSubmit(View view){
        // Create an Intent to navigate from the current Activity to LeaderboardActivity
        Intent intent = new Intent(this, LeaderboardActivity.class);
        // Start the LeaderboardActivity to proceed to the leaderboard screen
        startActivity(intent);
    }

}