package com.chess.engine.core;

import com.chess.engine.pieces.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;

public class PromotionUI extends HBox {

    private final Board board;
    private final Piece piece;
    private final EventHandler<Event> onPromote;

    public PromotionUI(Board board, Piece piece, EventHandler<Event> onPromote) {

        super();

        this.board = board;
        this.piece = piece;
        this.onPromote = onPromote;

        setAlignment(Pos.CENTER);
        setMaxHeight(TILE_SIZE * 2);
        setMaxWidth(TILE_SIZE * 5);
        setSpacing(20);
        setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.70), CornerRadii.EMPTY, Insets.EMPTY)));

        getChildren().add(createBox(PieceType.QUEEN));
        getChildren().add(createBox(PieceType.KNIGHT));
        getChildren().add(createBox(PieceType.BISHOP));
        getChildren().add(createBox(PieceType.ROOK));
    }

    private StackPane createBox(PieceType type) {

        StackPane box = new StackPane();

        Piece selectPiece = switch (type) {
            case QUEEN -> new Queen(piece.getColor(), piece.getPoint().getX(), piece.getPoint().getY());
            case KNIGHT -> new Knight(piece.getColor(), piece.getPoint().getX(), piece.getPoint().getY());
            case BISHOP -> new Bishop(piece.getColor(), piece.getPoint().getX(), piece.getPoint().getY());
            case ROOK -> new Rook(piece.getColor(), piece.getPoint().getX(), piece.getPoint().getY());
            default -> null;
        };

        box.getChildren().add(selectPiece);
        box.setOnMouseClicked(e -> promote(selectPiece));

        return box;
    }

    private void promote(Piece selectedPiece) {

        board.getPieces().remove(piece);
        board.getPieces().add(selectedPiece);
        board.getTile(piece.getPoint().getX(), piece.getPoint().getY()).setPiece(selectedPiece);
        board.getKings().values().forEach(e -> e.updateStates(board));
        onPromote.handle(null);
    }
}
