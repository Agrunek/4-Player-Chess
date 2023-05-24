package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.Point;

import java.util.List;

import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;
import static com.chess.engine.utils.HelpMethods.Validation.checkValidate;

/**
 * TODO: Castling...
 */

public class King extends Piece {

    private boolean inCheck = false;

    public King(PieceColor color, int x, int y) {
        super(PieceType.KING, color, new Point(x, y));
    }

    @Override
    public boolean validate(Board board, int x, int y) {

        if (!defaultValidate(board, this, x, y)) {
            return false;
        }

        int deltaX = Math.abs(getPoint().getX() - x);
        int deltaY = Math.abs(getPoint().getY() - y);

        return (deltaX <= 1 && deltaY <= 1);
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void updateInCheck(Board board) {
        inCheck = board.getPieces().stream().anyMatch(e -> e.validate(board, point.getX(), point.getY()));
    }

    public boolean anyMovesLeft(Board board) {

        List<Piece> pieces = board.getPieces().stream().filter(e -> e.getColor() == color).toList();

        for (int y = 0; y < Board.HEIGHT; y++) {
            for (int x = 0; x < Board.WIDTH; x++) {
                if (canMove(board, pieces, x, y)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canMove(Board board, List<Piece> pieces, int x, int y) {
        return pieces.stream().filter(e -> e.validate(board, x, y)).anyMatch(e -> checkValidate(board, e, x, y));
    }
}
