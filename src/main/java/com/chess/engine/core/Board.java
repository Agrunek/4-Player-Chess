package com.chess.engine.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.PieceColor;
import com.chess.engine.pieces.PieceType;
import com.chess.engine.utils.IllegalMoveException;
import javafx.scene.Group;

import static com.chess.engine.utils.HelpMethods.Initialization.initTiles;
import static com.chess.engine.utils.HelpMethods.Initialization.initPieces;

public class Board extends Group {

    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;

    private final Tile[][] tiles = new Tile[HEIGHT][WIDTH];

    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final HashMap<PieceColor, King> kings = new HashMap<>();

    public Board() {
        initTiles(tiles);
        setBoard();
        initPieces(this);
    }

    public Piece move(Piece piece, int x, int y) throws IllegalMoveException {

        if (isCastle(piece, x, y)) {
            getKings().get(piece.getColor()).castle(this, x, y);
            return null;
        }

        return piece.move(this, x, y);
    }

    private boolean isCastle(Piece piece, int x, int y) {

        if (getTile(x, y) == null) {
            return false;
        }

        if (!getTile(x, y).hasPiece()) {
            return false;
        }

        return piece.getType() == PieceType.KING && piece.getColor() == getTile(x, y).getPiece().getColor();
    }

    private void setBoard() {
        for (int y = 0; y < Board.HEIGHT; y++) {
            for (int x = 0; x < Board.WIDTH; x++) {
                if (getTile(x, y) != null) {
                    getChildren().add(getTile(x, y));
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public HashMap<PieceColor, King> getKings() {
        return kings;
    }
}
