package com.chess.engine.core;

import com.chess.engine.pieces.*;
import com.chess.engine.utils.Point;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;

public class PromotionUI extends HBox {

    private Board board;
    private Point point;
    private EventHandler<Event> onPromote;

    public PromotionUI(Board board, Point point, PieceColor color, EventHandler<Event> event) {
        super();
        this.onPromote = event;
        this.board = board;
        this.point = point;
        setAlignment(Pos.CENTER);
        setMaxHeight(TILE_SIZE * 2);
        setMaxWidth(TILE_SIZE * 5);
        setSpacing(20);
        setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.70), CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(createBox(PieceType.QUEEN, color));
        getChildren().add(createBox(PieceType.KNIGHT, color));
        getChildren().add(createBox(PieceType.BISHOP, color));
        getChildren().add(createBox(PieceType.ROOK, color));
    }


    private StackPane createBox(PieceType type, PieceColor color) {
        StackPane temp = new StackPane();
        switch (type) {
            case KNIGHT -> temp.getChildren().add(new Knight(color, 0, 0));
            case QUEEN -> temp.getChildren().add(new Queen(color, 0, 0));
            case BISHOP -> temp.getChildren().add(new Bishop(color, 0, 0));
            case ROOK -> temp.getChildren().add(new Rook(color, 0, 0));
        }
        temp.setOnMouseClicked((e) -> promote(type, color));

        return temp;
    }

    private void promote(PieceType type, PieceColor color) {
        board.getPieces().remove(board.getTile(point.getX(), point.getY()).getPiece());
        board.getChildren().remove(board.getTile(point.getX(), point.getY()).getPiece());
        board.getTile(point.getX(), point.getY()).setPiece(null);
        Piece piece = null;
        switch (type) {
            case KNIGHT -> piece = new Knight(color, point.getX(), point.getY());
            case QUEEN -> piece = new Queen(color, point.getX(), point.getY());
            case BISHOP -> piece = new Bishop(color, point.getX(), point.getY());
            case ROOK -> piece = new Rook(color, point.getX(), point.getY());
        }
        board.getTile(point.getX(), point.getY()).setPiece(piece);
        board.getPieces().add(piece);
        onPromote.handle(null);
    }


}
