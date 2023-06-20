package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.Point;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;
import static com.chess.engine.utils.HelpMethods.ImageManagement.getTileImage;
import static com.chess.engine.utils.HelpMethods.ImageManagement.getTileImageHighlight;

public class Tile extends StackPane {

    private final Point point;
    private Piece piece;

    private ImageView background;

    private boolean picked = false;
    private boolean checked = false;

    public Tile(int x, int y) {
        point = new Point(x, y);
        relocate(x * TILE_SIZE, y * TILE_SIZE);
        background = getTileImage(x, y, false);
        getChildren().add(background);
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

    private void updateVisuals() {

        Piece tempPiece = piece;
        ImageView tempBackground = background;
        background = picked ? getTileImageHighlight(point.getX(), point.getY(), checked) : getTileImage(point.getX(), point.getY(), checked);

        getChildren().add(background);
        getChildren().remove(tempBackground);
        setPiece(null);
        setPiece(tempPiece);
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
        updateVisuals();
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        updateVisuals();
    }
}
