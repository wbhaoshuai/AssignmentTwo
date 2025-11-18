package com.example.assignmenttwo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the ImageView list of the groundhog
        ArrayList<ImageView> moleViews = new ArrayList<>();
        moleViews.add(findViewById(R.id.iv_without_mole_01));
        moleViews.add(findViewById(R.id.iv_without_mole_02));
        moleViews.add(findViewById(R.id.iv_without_mole_03));
        moleViews.add(findViewById(R.id.iv_without_mole_04));
        moleViews.add(findViewById(R.id.iv_without_mole_05));
        moleViews.add(findViewById(R.id.iv_without_mole_06));
        moleViews.add(findViewById(R.id.iv_without_mole_07));
        moleViews.add(findViewById(R.id.iv_without_mole_08));
        moleViews.add(findViewById(R.id.iv_without_mole_09));

        // Initialize score and timer TextView
        TextView scoreTextView = findViewById(R.id.tv_score_text);
        TextView timerTextView = findViewById(R.id.tv_timer_text);

        //Initialize Game logic
        gameLogic = new GameLogic(this, moleViews, scoreTextView, timerTextView);
        // Start the game
        gameLogic.startGame();

    }

    // Clean up all background tasks when GameActivity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Clear all Handler tasks from the main thread
        new Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null);

        // Cancel countdown timer in GameLogic through reflection
        if (gameLogic != null) {
            try {
                Field timerField = GameLogic.class.getDeclaredField("gameTimer");
                timerField.setAccessible(true);
                CountDownTimer timer = (CountDownTimer) timerField.get(gameLogic);
                if (timer != null) {
                    timer.cancel(); // Cancel timer to avoid triggering onFinish jump
                }
            } catch (Exception e) {
                // Reflection failure does not affect, main thread task cleared
            }
        }
    }
}