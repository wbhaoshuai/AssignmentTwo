package com.example.assignmenttwo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayerActivity extends AppCompatActivity {

    private  TextView tv_playerscore;
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

        // Obtain the final score passed from GameLogic
        finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);
        tv_playerscore.setText(getString(R.string.final_score_text, finalScore));

        // Obtain Leaderboard singleton instance
        leaderboardInstance = Leaderboard.getInstance();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onclickSubmit(View view){

        // Retrieve the name entered by the player
        String playerName = et_playername.getText().toString().trim();
        if (playerName.isEmpty()) {
            et_playername.setError("Please enter a name");
            return;
        }

        // Get the selected avatar ID
        int selectedAvatarId = rg_avatar.getCheckedRadioButtonId();

        // Define player avatar variables
        Drawable playerAvatar;

        // Match the corresponding avatar resources based on the selected ID
        if (selectedAvatarId == R.id.avatar1) {
            playerAvatar = getResources().getDrawable(R.drawable.img_grey_mole, getTheme());
        } else if (selectedAvatarId == R.id.avatar2) {
            playerAvatar = getResources().getDrawable(R.drawable.img_blue_mole, getTheme());
        } else if (selectedAvatarId == R.id.avatar3) {
            playerAvatar = getResources().getDrawable(R.drawable.img_orange_mole, getTheme());
        } else if (selectedAvatarId == R.id.avatar4) {
            playerAvatar = getResources().getDrawable(R.drawable.img_green_mole, getTheme());
        } else if (selectedAvatarId == R.id.avatar5) {
            playerAvatar = getResources().getDrawable(R.drawable.img_purple_mole, getTheme());
        } else if (selectedAvatarId == R.id.avatar6) {
            playerAvatar = getResources().getDrawable(R.drawable.img_pink_mole, getTheme());
        } else {
            // Default avatar (if no option is selected)
            playerAvatar = getResources().getDrawable(R.drawable.img_grey_mole, getTheme());
        }


        // Create a Player object and update the leaderboard
        Player newPlayer = new Player(playerName, playerAvatar, finalScore);
        leaderboardInstance.updateLeaderboard(newPlayer);

        // Create an Intent to navigate from the current Activity to LeaderboardActivity
        Intent intent = new Intent(this, LeaderboardActivity.class);
        // Start the LeaderboardActivity to proceed to the leaderboard screen
        startActivity(intent);
    }

}