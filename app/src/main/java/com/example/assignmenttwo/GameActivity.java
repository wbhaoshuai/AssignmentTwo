package com.example.assignmenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

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

        TextView tv_time = findViewById(R.id.tv_timer_text);

        new CountDownTimer(30000, 1000) { // Total time, tick interval
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView
                int remainingSeconds = (int) (millisUntilFinished / 1000);
                tv_time.setText(getString(R.string.timer_text, remainingSeconds));
            }
            public void onFinish() {
                // End the game and move to the next screen
                tv_time.setText(R.string.Timer);
                Intent intent = new Intent(GameActivity.this, PlayerActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

}