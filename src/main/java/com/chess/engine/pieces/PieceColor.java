package com.chess.engine.pieces;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.chess.engine.utils.Constants.Colors.*;

public enum PieceColor {

    RED(RED_PLAYER, Color.WHITE, RED_PLAYER_HIGHLIGHT),
    BLUE(BLUE_PLAYER, Color.WHITE, BLUE_PLAYER_HIGHLIGHT),
    YELLOW(YELLOW_PLAYER, Color.BLACK, YELLOW_PLAYER_HIGHLIGHT),
    GREEN(GREEN_PLAYER, Color.BLACK, GREEN_PLAYER_HIGHLIGHT);

    private final Color color;
    private final Color textColor;
    private final Color highlightColor;

    PieceColor(Color color, Color textColor, Color highlightColor) {
        this.color = color;
        this.textColor = textColor;
        this.highlightColor = highlightColor;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public Color getColor() {
        return color;
    }

    public Paint getTextColor() {
        return textColor;
    }

    public Paint getHighlightColor() {
        return highlightColor;
    }
}
