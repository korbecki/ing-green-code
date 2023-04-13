package org.example.onlinegame.dto;

public class Clan implements Comparable<Clan> {
    private final int numberOfPlayers;
    private final int points;

    public Clan(int numberOfPlayers, int points) {
        this.numberOfPlayers = numberOfPlayers;
        this.points = points;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public int compareTo(Clan o) {
        int pointCompare = Integer.compare(o.points, this.points);
        if (pointCompare != 0)
            return pointCompare;

        return Integer.compare(this.numberOfPlayers, o.numberOfPlayers);
    }
}
