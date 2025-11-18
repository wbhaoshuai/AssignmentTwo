package com.example.assignmenttwo;

import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {

    private Leaderboard leaderboardInstance;
    private ArrayList<Player> leaderboard;
    private final int MAX_LEADERBOARD_SIZE = 5;

    // constructor
    private Leaderboard(){

    }

    // Get singleton instance of Leaderboard
    public Leaderboard getInstance() {
        return leaderboardInstance;
    }

    // Update leaderboard with new player, maintaining size and sorting
    public void updateLeaderboard(Player newPlayer) {
        // Add new player
        leaderboard.add(newPlayer);
        // Sort players by score in descending order
        leaderboard.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getPlayerScore(), p1.getPlayerScore());
            }
        });
        // Ensure leaderboard doesn't exceed max size
        while (leaderboard.size() > MAX_LEADERBOARD_SIZE) {
            leaderboard.remove(leaderboard.size() - 1);
        }
    }


}
