package com.chess.engine.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.PieceColor;
import javafx.scene.Group;

import static com.chess.engine.utils.HelpMethods.Initialization.initTiles;
import static com.chess.engine.utils.HelpMethods.Initialization.initPieces;

public class Board extends Group {

    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final HashMap<PieceColor, King> kings = new HashMap<>();

    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;

    private final Tile[][] tiles = new Tile[HEIGHT][WIDTH];

    public Board() {

        initTiles(tiles);
        setBoard();
        initPieces(this);
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
