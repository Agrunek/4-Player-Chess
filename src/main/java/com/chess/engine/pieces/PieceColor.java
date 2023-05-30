package com.chess.engine.pieces;

public enum PieceColor {

    RED,
    BLUE,
    YELLOW,
    GREEN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
