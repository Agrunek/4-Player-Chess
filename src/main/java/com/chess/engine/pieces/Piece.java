package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.IllegalMoveException;
import com.chess.engine.utils.Point;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

import static com.chess.engine.utils.HelpMethods.ImageManagement.getPieceImage;
import static com.chess.engine.utils.HelpMethods.Validation.checkValidate;

public abstract class Piece extends Group {

    protected final PieceType type;
    protected final PieceColor color;
    protected final Point point;

    private boolean firstMove = true;
    private boolean promotion = false;

    Piece(PieceType type, PieceColor color, Point point) {
        this.type = type;
        this.color = color;
        this.point = point;
        ImageView pieceTexture = getPieceImage(color, type);
        getChildren().add(pieceTexture);
    }

    public abstract boolean validate(Board board, int x, int y);

    public Piece move(Board board, int x, int y) throws IllegalMoveException {

        if (!validate(board, x, y)) {
            throw new IllegalMoveException();
        }

        if (!checkValidate(board, this, x, y)) {
            throw new IllegalMoveException();
        }

        Piece target = board.getTile(x, y).getPiece();
        board.getPieces().remove(target);

        board.getTile(x, y).setPiece(this);
        board.getTile(point.getX(), point.getY()).setPiece(null);
        board.getTile(point.getX(), point.getY()).setChecked(false);

        point.setX(x);
        point.setY(y);

        useFirstMove();

        if (target != null) {
            if (target.type == PieceType.KING) {
                board.getKings().get(target.color).forceLose(board);
            }
        }

        board.getKings().values().forEach(e -> e.updateStates(board));

        return target;
    }

    public void useFirstMove() {
        firstMove = false;
    }

    public void usePromotion() {
        promotion = true;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public boolean isPromotion() {
        return promotion;
    }
}
