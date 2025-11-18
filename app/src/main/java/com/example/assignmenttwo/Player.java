package com.example.assignmenttwo;

import android.graphics.drawable.Drawable;

public class Player {

    // Private attributes of the Player class
    private final String playerName;
    private final Drawable playerAvatar;
    private final int playerScore;

    // Constructor to initialize player information
    public Player(String name, Drawable avatar, int score) {
        this.playerName = name;
        this.playerAvatar = avatar;
        this.playerScore = score;
    }

    // Getter method for player's name
    public String getPlayerName() {
        return playerName;
    }

    // Getter method for player's avatar
    public Drawable getPlayerAvatar() {
        return playerAvatar;
    }

    // Getter method for player's score
    public int getPlayerScore() {
        return playerScore;
    }

}
