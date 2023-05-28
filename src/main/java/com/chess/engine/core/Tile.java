package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.chess.engine.utils.Constants.Colors.*;
import static com.chess.engine.utils.Constants.Sizes.*;

public class Tile extends Rectangle {

    private Piece piece;

    public Tile(int x, int y) {
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        if ((x + y) % 2 == 0) {
            setFill(Color.valueOf(BOARD_COLOR));
        } else {
            setFill(Color.WHITE);
        }
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
