package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.IllegalMoveException;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import static com.chess.engine.pieces.PieceColor.*;
import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;

public class Game extends Group {

    private final Board board = new Board();

    private final Player[] players = new Player[4];
    private int iterator = 0;

    private Piece piece = null;

    public Game() {
        players[0] = new Player(RED);
        players[1] = new Player(BLUE);
        players[2] = new Player(YELLOW);
        players[3] = new Player(GREEN);
        getChildren().add(board);
        setOnMouseClicked(this::mouseEvent);
    }

    private void mouseEvent(MouseEvent mouseEvent) {

        int x = (int) Math.floor(mouseEvent.getX() / TILE_SIZE);
        int y = (int) Math.floor(mouseEvent.getY() / TILE_SIZE);

        if (piece == null) {
            selectPiece(x, y);
        } else {
            movePiece(x, y);
        }
    }

    private void selectPiece(int x, int y) {

        if (board.getTile(x, y) == null) {
            return;
        }

        Piece selected = board.getTile(x, y).getPiece();
        if (selected == null) {
            return;
        }

        if (selected.getColor() != players[iterator].getColor()) {
            return;
        }

        piece = selected;
    }

    private void movePiece(int x, int y) {

        try {
            Piece popped = board.move(piece, x, y);
            if (popped != null) {
                players[iterator].addScore(popped.getType().getValue());
            }
            System.out.println("Player " + players[iterator].getColor().toString() + " score is: " + players[iterator].getScore());
            updateIterator();
        } catch (IllegalMoveException e) {
            System.out.println("ILLEGAL MOVE!");
        }

        piece = null;
    }

    private void updateIterator() {
        iterator = (iterator >= 3) ? 0 : iterator + 1;
        if (board.getKings().get(players[iterator].getColor()).hasLost()) {
            updateIterator();
        }
    }
}
