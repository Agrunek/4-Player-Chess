package com.chess.engine.pieces;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum PieceColor {

    RED(Color.RED),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    GREEN(Color.GREEN);

    private final Color color;
    private final Color textColor;

    PieceColor(Color color) {
        this.color = color;
        if (color == Color.BLUE || color == Color.RED) {
            this.textColor = Color.WHITE;
        } else {
            this.textColor = Color.BLACK;
        }
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public Paint getTextColor() {
        return textColor;
    }
}
