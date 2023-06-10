package com.chess.engine.core;

import com.chess.engine.pieces.PieceColor;
import javafx.scene.Group;

public class Player extends Group {

    private final PieceColor color;
    private int score = 0;
    private final ScoreBoard scoreBoard;

    public Player(PieceColor color) {
        this.color = color;
        this.scoreBoard = new ScoreBoard(this);
        getChildren().add(scoreBoard);
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
