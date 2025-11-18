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
        // Initialize the mole list
        for (int i = 0; i < moleViews.size(); i++) {
            // Create mole object and add it to the list
            Mole mole = new Mole(i, moleViews.get(i));
            this.moles.add(mole);

            // Bind click events for each groundhog's imageView
            mole.getImageView().setOnClickListener(v -> {
                // Clicking is only effective when the groundhog is visible
                if (mole.isVisible()) {
                    currentScore += 10;
                    updateScoreText();
                    mole.setVisible(false);
                }
            });
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

    // Start the mole cycle
    private void startMoleLoop(){

        moleRunnable = new Runnable() {
            @Override
            public void run() {

                if(currentMoleIndex != -1){
                    hideMole();
                }

                // Randomly select a mole
                int randomIndex;
                // Ensure not to select the same mole
                do {
                    randomIndex = random.nextInt(moles.size());
                } while (randomIndex == currentMoleIndex);

                // Display the selected mole
                showMole(randomIndex);

                // Schedule this to run again after a delay
                moleHandler.postDelayed(this, MOLE_DISPLAY_TIME);
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
