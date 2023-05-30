package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import static com.chess.engine.utils.Constants.Sizes.*;

public class Tile extends StackPane {

    private Piece piece;

    private final ImageView tileBackground;

    public Tile(int x, int y) {
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        if ((x + y) % 2 == 0) {
            tileBackground = new ImageView(new Image("file:src/main/java/com/chess/gui/tile_red.png", TILE_SIZE, TILE_SIZE, true, false));
        } else {
            tileBackground = new ImageView(new Image("file:src/main/java/com/chess/gui/tile_white.png", TILE_SIZE, TILE_SIZE, true, false));
        }
        getChildren().add(tileBackground);
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            getChildren().remove(piece);
            getChildren().add(piece);
        }

    }
}
