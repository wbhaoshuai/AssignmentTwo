package com.example.assignmenttwo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Declare the control array corresponding to the 5 rankings in the layout (avatar, name, score)
        ImageView[] avatarViews = new ImageView[5];
        TextView[] nameViews = new TextView[5];
        TextView[] scoreViews = new TextView[5];

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leaderboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize controls in the layout and bind them to array indexes
        avatarViews[0] = findViewById(R.id.iv_leaderboard_avatar1);
        avatarViews[1] = findViewById(R.id.iv_leaderboard_avatar2);
        avatarViews[2] = findViewById(R.id.iv_leaderboard_avatar3);
        avatarViews[3] = findViewById(R.id.iv_leaderboard_avatar4);
        avatarViews[4] = findViewById(R.id.iv_leaderboard_avatar5);

        nameViews[0] = findViewById(R.id.tv_leaderboard_name1);
        nameViews[1] = findViewById(R.id.tv_leaderboard_name2);
        nameViews[2] = findViewById(R.id.tv_leaderboard_name3);
        nameViews[3] = findViewById(R.id.tv_leaderboard_name4);
        nameViews[4] = findViewById(R.id.tv_leaderboard_name5);

        scoreViews[0] = findViewById(R.id.tv_leaderboard_score1);
        scoreViews[1] = findViewById(R.id.tv_leaderboard_score2);
        scoreViews[2] = findViewById(R.id.tv_leaderboard_score3);
        scoreViews[3] = findViewById(R.id.tv_leaderboard_score4);
        scoreViews[4] = findViewById(R.id.tv_leaderboard_score5);


        // Obtain ranking data from a single instance
        ArrayList<Player> leaderboard = Leaderboard.getInstance().getLeaderboard();

        // Traverse the control of 5 rankings and fill in data
        for (int i = 0; i < 5; i++) {
            // If the ranking data is less than 5 items, empty data will be displayed
            if (i < leaderboard.size()) {
                Player player = leaderboard.get(i);
                avatarViews[i].setImageDrawable(player.getPlayerAvatar());
                nameViews[i].setText(player.getPlayerName());
                scoreViews[i].setText(getString(R.string.score_text, player.getPlayerScore()));
            } else {
                // Clear control when data is insufficient
                avatarViews[i].setImageDrawable(null);
                nameViews[i].setText("");
                scoreViews[i].setText("");
            }
        }

    }
}