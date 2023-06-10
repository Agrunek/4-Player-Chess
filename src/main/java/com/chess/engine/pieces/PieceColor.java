package com.chess.engine.pieces;

import javafx.scene.paint.Color;

public enum PieceColor {

    RED(Color.RED),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN);

    private final Color color;

    PieceColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
