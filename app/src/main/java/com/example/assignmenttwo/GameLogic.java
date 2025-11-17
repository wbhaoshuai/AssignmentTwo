package com.example.assignmenttwo;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

public class GameLogic {

    private long MOLE_DISPLAY_TIME;
    private long GAME_DURATION;
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
    
}
