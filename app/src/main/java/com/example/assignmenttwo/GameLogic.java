package com.example.assignmenttwo;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import android.os.Handler;

public class GameLogic {

    private final long MOLE_DISPLAY_TIME;
    private final long GAME_DURATION;
    private  int currentScore;
    private int timeRemaining;
    private int currentMoleIndex;
    private Handler moleHandler;
    private  Runnable moleRunnable;
    private CountDownTimer gameTimer;
    private Random random;
    private ArrayList<Mole> moles;
    private TextView scoreTextView;
    private  TextView timerTextView;
    private boolean isGameRunning;
    private Context context;

    // constructor
    public GameLogic(Context context, ArrayList<ImageView> moleViews, TextView scoreTextView, TextView timerTextView){

        // Initialize constants
        this.MOLE_DISPLAY_TIME = 1500;
        this.GAME_DURATION = 30000;

        this.currentScore = 0;
        this.timeRemaining = 30;
        this.currentMoleIndex = -1; // -1 indicates no mole display
        this.moleHandler =  new Handler();

        // Initialize Runnable (delay initialization, assign specific logic when used)
        this.moleRunnable = null;
        this.gameTimer = null; // The countdown timer is initialized during startGame
        this.random = new Random();

        this.moles = new ArrayList<>();
        // Initialize the groundhog list
        for (int i = 0; i < moleViews.size(); i++) {
            this.moles.add(new Mole(i, moleViews.get(i)));
        }

        this.scoreTextView = scoreTextView;
        this.timerTextView = timerTextView;
        this.isGameRunning = false;
        this.context = context;

    }

    // Start the game
    public void startGame(){
        if (isGameRunning) return; // Avoid repeated startup

        isGameRunning = true;
        currentScore = 0;
        startMoleLoop();
        startTimer();
        updateScoreText();
    }

    // Start game countdown
    public void  startTimer(){
        // Cancel existing timer (to prevent duplication)
        if (gameTimer != null) {
            gameTimer.cancel();
        }

        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = (int) (millisUntilFinished / 1000);
                timerTextView.setText(context.getString(R.string.timer_text, timeRemaining));
            }

            @Override
            public void onFinish() {
                // Game over: Stop groundhog loop, update status
                isGameRunning = false;
                timerTextView.setText(context.getString(R.string.timer_text, 0));
                stopMoleLoop();
                hideMole(); // Hide the last mole
            }
        }.start();
    }

    // Start the groundhog cycle
    private void startMoleLoop(){

        moleRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isGameRunning) return; // Must loop during gameplay

                // Randomly select a mole
                int randomIndex;
                do {
                    randomIndex = random.nextInt(moles.size());
                } while (randomIndex == currentMoleIndex && moles.size() > 1);

                // Display the selected mole
                showMole(randomIndex);

                // Hide the groundhog after delaying MOLE_DISPLAY_TIME and continue the loop
                moleHandler.postDelayed(() -> {
                    hideMole();
                    // After the groundhog hides, display the next one every 500ms
                    moleHandler.postDelayed(this, 500);
                }, MOLE_DISPLAY_TIME);
            }
        };

        // Start the loop
        moleHandler.post(moleRunnable);
    }

    // Stop the mole cycle
    private void stopMoleLoop(){
        if (moleHandler != null && moleRunnable != null) {
            moleHandler.removeCallbacks(moleRunnable);
        }
    }

    // Display the mole with the specified index
    private void showMole(int index){

        // Hide the current mole (to prevent multiple moles from being displayed simultaneously)
        hideMole();

        // Show new mole
        currentMoleIndex = index;
        moles.get(index).setVisible(true);

    }

    // Hide the currently displayed mole
    private void hideMole(){
        moles.get(currentMoleIndex).setVisible(false);
        currentMoleIndex = -1;
    }

    // Update score display
    private void updateScoreText(){
        scoreTextView.setText(context.getString(R.string.score_text, currentScore));
    }

}
