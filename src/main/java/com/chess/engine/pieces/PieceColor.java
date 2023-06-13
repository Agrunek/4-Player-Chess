package com.chess.engine.pieces;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.chess.engine.utils.Constants.Colors.*;

public enum PieceColor {

    RED(RED_PLAYER),
    BLUE(BLUE_PLAYER),
    YELLOW(YELLOW_PLAYER),
    GREEN(GREEN_PLAYER);

    private final Color color;
    private final Color textColor;
    private Color highlightColor;

    PieceColor(Color color) {
        this.color = color;
        if (color == BLUE_PLAYER || color == RED_PLAYER) {
            this.textColor = Color.WHITE;
        } else {
            this.textColor = Color.BLACK;
        }
        if (color == BLUE_PLAYER) {
            highlightColor = BLUE_PLAYER_HIGHLIGHT;
        }
        if (color == RED_PLAYER) {
            highlightColor = RED_PLAYER_HIGHLIGHT;

        }
        if (color == GREEN_PLAYER) {
            highlightColor = GREEN_PLAYER_HIGHLIGHT;

        }
        if (color == YELLOW_PLAYER) {
            highlightColor = YELLOW_PLAYER_HIGHLIGHT;
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

    public Paint getHighlightColor() {
        return highlightColor;
    }
}
