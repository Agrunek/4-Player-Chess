package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.Point;

import static com.chess.engine.utils.HelpMethods.Validation.defaultValidate;

public class Knight extends Piece {

    public Knight(PieceColor color, int x, int y) {
        super(PieceType.KNIGHT, color, new Point(x, y));
    }

    @Override
    public boolean validate(Board board, int x, int y) {

        if (!defaultValidate(board, this, x, y)) {
            return false;
        }

        int deltaX = Math.abs(getPoint().getX() - x);
        int deltaY = Math.abs(getPoint().getY() - y);

        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}
