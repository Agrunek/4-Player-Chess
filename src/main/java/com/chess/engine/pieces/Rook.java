package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.Point;

import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;
import static com.chess.engine.utils.HelpMethods.Validation.interruptValidate;

public class Rook extends Piece {

    public Rook(PieceColor color, int x, int y) {
        super(PieceType.ROOK, color, new Point(x, y));
    }

    @Override
    public boolean validate(Board board, int x, int y) {

        if (!defaultValidate(board, this, x, y)) {
            return false;
        }

        if (getPoint().getX() != x && getPoint().getY() != y) {
            return false;
        }

        return interruptValidate(board, this, x, y);
    }
}
