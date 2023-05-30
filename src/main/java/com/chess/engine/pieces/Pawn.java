package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.Point;

import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;

/**
 * TODO: Add Promotion & En Passant! ;-;
 */

public class Pawn extends Piece {

    public Pawn(PieceColor color, int x, int y) {
        super(PieceType.PAWN, color, new Point(x, y));
    }

    @Override
    public boolean validate(Board board, int x, int y) {

        if (!defaultValidate(board, this, x, y)) {
            return false;
        }

        boolean isCorrectMove = switch (color) {
            case RED -> validateRed(board, x, y);
            case BLUE -> validateBlue(board, x, y);
            case YELLOW -> validateYellow(board, x, y);
            case GREEN -> validateGreen(board, x, y);
        };
        if (isCorrectMove) {
            boolean isPromotion = switch (color) {
                case RED -> isPromotionRed(board, x, y);
                case BLUE -> isPromotionBlue(board, x, y);
                case YELLOW -> isPromotionYellow(board, x, y);
                case GREEN -> isPromotionGreen(board, x, y);
            };
        }
        return isCorrectMove;
    }

    private boolean validateRed(Board board, int x, int y) {

        if (point.getX() == x && !board.getTile(x, point.getY() - 1).hasPiece()) {

            if (point.getY() - 1 == y) {
                return true;
            } else {
                return isFirstMove() && point.getY() - 2 == y && !board.getTile(x, y).hasPiece();
            }

        } else if (Math.abs(point.getX() - x) == 1 && point.getY() - 1 == y) {

            if (board.getTile(x, y).hasPiece()) {
                return board.getTile(x, y).getPiece().getColor() != color;
            }
        }

        return false;
    }

    private boolean validateBlue(Board board, int x, int y) {

        if (point.getY() == y && !board.getTile(point.getX() + 1, y).hasPiece()) {
            if (point.getX() + 1 == x) {
                return true;
            } else {
                return isFirstMove() && point.getX() + 2 == x && !board.getTile(x, y).hasPiece();
            }

        } else if (Math.abs(point.getY() - y) == 1 && point.getX() + 1 == x) {

            if (board.getTile(x, y).hasPiece()) {
                return board.getTile(x, y).getPiece().getColor() != color;
            }
        }

        return false;
    }

    private boolean validateYellow(Board board, int x, int y) {

        if (point.getX() == x && !board.getTile(x, point.getY() + 1).hasPiece()) {
            if (point.getY() + 1 == y) {
                return true;
            } else {
                return isFirstMove() && point.getY() + 2 == y && !board.getTile(x, y).hasPiece();
            }

        } else if (Math.abs(point.getX() - x) == 1 && point.getY() + 1 == y) {

            if (board.getTile(x, y).hasPiece()) {
                return board.getTile(x, y).getPiece().getColor() != color;
            }
        }
        return false;
    }

    private boolean validateGreen(Board board, int x, int y) {

        if (point.getY() == y && !board.getTile(point.getX() - 1, y).hasPiece()) {

            if (point.getX() - 1 == x) {
                return true;
            } else {
                return isFirstMove() && point.getX() - 2 == x && !board.getTile(x, y).hasPiece();
            }

        } else if (Math.abs(point.getY() - y) == 1 && point.getX() - 1 == x) {

            if (board.getTile(x, y).hasPiece()) {
                return board.getTile(x, y).getPiece().getColor() != color;
            }

        }

        return false;
    }

    private boolean isPromotionRed(Board board, int x, int y) {
        return point.getY() == 6;
    }

    private boolean isPromotionBlue(Board board, int x, int y) {
        return point.getX() == 7;
    }

    private boolean isPromotionYellow(Board board, int x, int y) {
        return point.getY() == 7;
    }

    private boolean isPromotionGreen(Board board, int x, int y) {
        return point.getX() == 6;
    }
}
