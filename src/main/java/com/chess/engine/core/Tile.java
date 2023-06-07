package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;
import static com.chess.engine.utils.HelpMethods.ImageManagement.getTileImage;

public class Tile extends StackPane {

    private Piece piece;

    public Tile(int x, int y) {
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        ImageView tileBackground = getTileImage(x, y);
        getChildren().add(tileBackground);
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {

        if (hasPiece()) {
            getChildren().remove(this.piece);
        }

        if (piece != null) {
            getChildren().add(piece);
        }

        this.piece = piece;
    }
}
