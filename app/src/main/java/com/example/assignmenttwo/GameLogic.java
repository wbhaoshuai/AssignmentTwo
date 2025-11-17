package com.example.assignmenttwo;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Looper;
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

        this.MOLE_DISPLAY_TIME = 1500;
        this.GAME_DURATION = 30000;
        this.currentScore = 0;
        this.timeRemaining = 30;
        this.currentMoleIndex = -1; // -1 indicates no mole display
        this.moleHandler =  new Handler(Looper.getMainLooper());

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

    public void startGame(){
        isGameRunning = true;
        startMoleLoop();
        startTimer();
        updateScoreText();
    }

    public void  startTimer(){
        gameTimer = new CountDownTimer(GAME_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = (int) (millisUntilFinished / 1000);
                timerTextView.setText(context.getString(R.string.timer_text, timeRemaining));
            }

            @Override
            public void onFinish() {
                isGameRunning = false;
                stopMoleLoop();
            }
        }.start();
    }

    private void startMoleLoop(){

    }

    private void stopMoleLoop(){

    }

    private void showMole(int index){

    }

    private void hideMole(){

    }

    private void updateScoreText(){

    }

}
