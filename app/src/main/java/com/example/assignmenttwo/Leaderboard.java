package com.example.assignmenttwo;

import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard {

    private static Leaderboard leaderboardInstance;
    private final ArrayList<Player> leaderboard;
    private final int MAX_LEADERBOARD_SIZE;

    // constructor
    private Leaderboard(){
        this.leaderboard = new ArrayList<>();
        this.MAX_LEADERBOARD_SIZE = 5;
    }

    // Get singleton instance of Leaderboard
    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            leaderboardInstance = new Leaderboard();
        }
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

    // Get the current leaderboard list
    public ArrayList<Player> getLeaderboard() {
        return leaderboard;
    }


}
