package com.chess.engine.core;

import com.chess.engine.pieces.PieceColor;

public class Player {

    private final PieceColor color;
    private int score = 0;

    public Player(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }
}
