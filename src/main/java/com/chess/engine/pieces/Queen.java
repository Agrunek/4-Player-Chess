package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.Point;

import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;
import static com.chess.engine.utils.HelpMethods.Validation.interruptValidate;

public class Queen extends Piece {

    public Queen(PieceColor color, int x, int y) {
        super(PieceType.QUEEN, color, new Point(x, y));
    }

    @Override
    public boolean validate(Board board, int x, int y) {

        if (!defaultValidate(board, this, x, y)) {
            return false;
        }

        if (getPoint().getX() != x && getPoint().getY() != y && Math.abs(point.getX() - x) != Math.abs(point.getY() - y)) {
            return false;
        }

        return interruptValidate(board, this, x, y);
    }
}
