package com.chess.engine.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.PieceColor;
import com.chess.engine.utils.IllegalMoveException;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;
import static com.chess.engine.utils.HelpMethods.Initialization.initTiles;
import static com.chess.engine.utils.HelpMethods.Initialization.initPieces;

public class Board extends Group {

    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final HashMap<PieceColor, King> kings = new HashMap<>();
    private Piece movePiece = null;

    public static final int WIDTH = 14;
    public static final int HEIGHT = 14;

    private final Tile[][] tiles = new Tile[HEIGHT][WIDTH];

    public Board() {

        initTiles(tiles);
        setBoard();
        initPieces(this);
        this.setOnMouseClicked(this::mouseEvent);
    }

    private void mouseEvent(MouseEvent mouseEvent) {
        int x = (int) Math.floor(mouseEvent.getX() / TILE_SIZE);
        int y = (int) Math.floor(mouseEvent.getY() / TILE_SIZE);

        if (movePiece == null) {
            System.out.println("selected: " + x + " " + y);
            movePiece = tiles[y][x].getPiece();
            if (movePiece == null) {
                System.out.println("not a piece");
            }
        } else {
            try {
                int oldX = movePiece.getPoint().getX();
                int oldY = movePiece.getPoint().getY();
                System.out.println("moved from " + oldX + "x" + oldY + " to " + x + "x" + y);
                tiles[oldY][oldX].getPiece().move(this, x, y);
            } catch (IllegalMoveException e) {
                System.out.println("Illegal Move");
            }
            movePiece = null;
        }
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
