package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.IllegalMoveException;
import com.chess.engine.utils.Point;

import java.util.List;

import static com.chess.engine.utils.HelpMethods.Validation.checkValidate;
import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;
import static com.chess.engine.utils.HelpMethods.Validation.interruptValidate;

public class King extends Piece {

    private boolean inCheck = false;
    private boolean lost = false;

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

    public boolean hasLost() {
        return lost;
    }

    public void updateStates(Board board) {

        inCheck = board.getPieces().stream().anyMatch(e -> e.validate(board, point.getX(), point.getY()));

        List<Piece> pieces = board.getPieces().stream().filter(e -> e.getColor() == color).toList();

        lost = !anyMovesLeft(board, pieces);

        if (lost) {
            clearPieces(board, pieces);
        }
    }

    private boolean anyMovesLeft(Board board, List<Piece> pieces) {

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

    private void clearPieces(Board board, List<Piece> pieces) {
        pieces.forEach(e -> board.getTile(e.getPoint().getX(), e.getPoint().getY()).setPiece(null));
        board.getPieces().removeIf(e -> e.getColor() == color);
    }

    public void castle(Board board, int x, int y) throws IllegalMoveException {

        if (!validateCastle(board, x, y)) {
            throw new IllegalMoveException();
        }

        int xDiff = x - point.getX();
        int yDiff = y - point.getY();

        Piece rook = board.getTile(x, y).getPiece();

        board.getTile(point.getX(), point.getY()).setPiece(null);
        board.getTile(rook.point.getX(), rook.point.getY()).setPiece(null);

        switch (color) {
            case RED, YELLOW -> {
                point.setX(point.getX() + ((xDiff > 0) ? 2 : -2));
                rook.point.setX(point.getX() + ((xDiff > 0) ? -1 : 1));
            }
            case BLUE, GREEN -> {
                point.setY(point.getY() + ((yDiff > 0) ? 2 : -2));
                rook.point.setY(point.getY() + ((yDiff > 0) ? -1 : 1));
            }
        }

        board.getTile(point.getX(), point.getY()).setPiece(this);
        board.getTile(rook.point.getX(), rook.point.getY()).setPiece(rook);

        useFirstMove();
        rook.useFirstMove();

        board.getKings().values().forEach(e -> e.updateStates(board));
    }

    private boolean validateCastle(Board board, int x, int y) {

        if (!board.getTile(x, y).hasPiece()) {
            return false;
        }

        if (board.getTile(x, y).getPiece().getType() != PieceType.ROOK) {
            return false;
        }

        if (inCheck || !isFirstMove() || !board.getTile(x, y).getPiece().isFirstMove()) {
            return false;
        }

        int xDiff = x - point.getX();
        int yDiff = y - point.getY();

        if (xDiff != 0 && yDiff != 0) {
            return false;
        }

        int checkX1 = (xDiff == 0) ? x : point.getX() + ((xDiff > 0) ? 1 : -1);
        int checkX2 = (xDiff == 0) ? x : point.getX() + ((xDiff > 0) ? 2 : -2);
        int checkY1 = (yDiff == 0) ? y : point.getY() + ((yDiff > 0) ? 1 : -1);
        int checkY2 = (yDiff == 0) ? y : point.getY() + ((yDiff > 0) ? 2 : -2);

        if (board.getPieces().stream().filter(e -> e.getColor() != color)
                .anyMatch(e -> e.validate(board, checkX1, checkY1) || e.validate(board, checkX2, checkY2))) {
            return false;
        }

        return interruptValidate(board, this, x, y);
    }
}
