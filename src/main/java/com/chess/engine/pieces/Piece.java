package com.chess.engine.pieces;

import com.chess.engine.core.Board;
import com.chess.engine.utils.IllegalMoveException;
import com.chess.engine.utils.Point;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.chess.engine.utils.Constants.Sizes.*;
import static com.chess.engine.utils.HelpMethods.Validation.checkValidate;

public abstract class Piece extends Group {

    protected final PieceType type;
    protected final PieceColor color;
    protected final Point point;

    private boolean firstMove = true;

    private final ImageView pieceTexture;

    Piece(PieceType type, PieceColor color, Point point) {

        this.type = type;
        this.color = color;
        this.point = point;

        pieceTexture = new ImageView(new Image("file:src/main/java/com/chess/gui/" + color.toString().charAt(0) + "_" + type.toString() + ".png", TILE_SIZE, TILE_SIZE, true, false));
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

        point.setX(x);
        point.setY(y);

        firstMove = false;

        board.getKings().values().forEach(e -> e.updateInCheck(board));

        board.getKings().values().stream().filter(e -> !e.anyMovesLeft(board)).forEach(e -> {
            board.getPieces().stream().filter(f -> f.getColor() == e.getColor())
                    .forEach(f -> board.getTile(f.getPoint().getX(), f.getPoint().getY()).setPiece(null));
            board.getPieces().removeIf(f -> f.getColor() == e.getColor());
        });

        return target;
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
}
