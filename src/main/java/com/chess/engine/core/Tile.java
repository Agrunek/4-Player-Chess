package com.chess.engine.core;

import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;
import static com.chess.engine.utils.Constants.Textures.ROOT_PATH;
import static com.chess.engine.utils.HelpMethods.ImageManagement.getTileImage;
import static com.chess.engine.utils.HelpMethods.ImageManagement.getTileImageHighilight;

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
    public void highlightTile(Board board, int x, int y){

        if(board.getKings().containsValue(piece)) {
            King king = board.getKings().values().stream().filter(k -> k.getPoint().equals(piece.getPoint())).findFirst().get();
            if (king.isInCheck()) {
                System.out.println("king picked and checked");
                getChildren().add(new ImageView(new Image(ROOT_PATH + "tile_check_highlight.png", TILE_SIZE, TILE_SIZE, true, false)));
                if (piece != null) setPiece(piece);
                return;
            }
        }
        getChildren().add(getTileImageHighilight(x, y));
        if (piece != null) setPiece(piece);

    }

    public void unhighlightTile(Board board, int x, int y){

        if(board.getKings().containsValue(piece)) {
            King king = board.getKings().values().stream().filter(k -> k.getPoint().equals(piece.getPoint())).findFirst().get();
            if (king.isInCheck()) {
                getChildren().add(new ImageView(new Image(ROOT_PATH + "tile_check.png", TILE_SIZE, TILE_SIZE, true, false)));
                if (piece != null) setPiece(piece);
                return;
            }
        }
        getChildren().add(getTileImage(x, y));
        if (piece != null) setPiece(piece);

    }

    public void checkTile(){
        getChildren().add(new ImageView(new Image(ROOT_PATH + "tile_check.png", TILE_SIZE, TILE_SIZE, true, false)));
        if(piece != null)setPiece(piece);

    }

}
