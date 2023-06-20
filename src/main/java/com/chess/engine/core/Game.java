package com.chess.engine.core;

import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.IllegalMoveException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.Arrays;

import static com.chess.engine.pieces.PieceColor.*;
import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;

public class Game extends StackPane {

    private final Board board = new Board();

    private final Player[] players = new Player[4];
    private int iterator = 0;

    private Piece piece = null;

    private final EventHandler<Event> onFinished;
    private PromotionUI promotionUI;

    public Game(EventHandler<Event> onFinished) {
        Group root = new Group();
        this.onFinished = onFinished;
        players[0] = new Player(RED);
        players[1] = new Player(BLUE);
        players[2] = new Player(YELLOW);
        players[3] = new Player(GREEN);
        root.getChildren().add(board);
        root.getChildren().addAll(players);
        players[0].getScoreBoard().setHighlightScore(true);
        root.setOnMouseClicked(this::mouseEvent);
        getChildren().add(root);
    }

    private void mouseEvent(MouseEvent mouseEvent) {

        int x = (int) Math.floor(mouseEvent.getX() / TILE_SIZE);
        int y = (int) Math.floor(mouseEvent.getY() / TILE_SIZE);

        if (piece == null) {
            selectPiece(x, y);
        } else {
            movePiece(x, y);
        }

        if (isGameOver()) {
            onFinished.handle(null);
        }
    }

    private void selectPiece(int x, int y) {

        if (board.getTile(x, y) == null) {
            return;
        }

        Piece selected = board.getTile(x, y).getPiece();
        if (selected == null) {
            return;
        }

        if (selected.getColor() != players[iterator].getColor()) {
            return;
        }

        board.getTile(x, y).setPicked(true);
        piece = selected;
    }

    private void movePiece(int x, int y) {

        board.getTile(piece.getPoint().getX(), piece.getPoint().getY()).setPicked(false);

        try {

            Piece popped = board.move(piece, x, y);

            if (popped != null) {
                players[iterator].addScore(popped.getType().getValue());
            }

            if (piece.isPromotion()) {
                promotionUI = new PromotionUI(board, piece, update -> onPromote());
                getChildren().add(promotionUI);
            }

            updateIterator();

            piece = null;

        } catch (IllegalMoveException e) {
            selectPiece(x, y);
        }
    }

    private void onPromote() {
        getChildren().remove(promotionUI);
    }

    private void updateIterator() {

        players[iterator].getScoreBoard().setHighlightScore(false);

        iterator = (iterator >= 3) ? 0 : iterator + 1;
        if (board.getKings().get(players[iterator].getColor()).hasLost()) {
            updateIterator();
        }

        players[iterator].getScoreBoard().setHighlightScore(true);
    }

    private boolean isGameOver() {

        int standing = 0;

        for (Player player : players) {
            if (!board.getKings().get(player.getColor()).hasLost()) {
                standing++;
            }
        }

        return standing <= 1;
    }

    public Player getWinner() {

        int maxScore = Arrays.stream(players).mapToInt(Player::getScore).max().orElse(0);

        return Arrays.stream(players).filter(e -> e.getScore() == maxScore).findFirst().orElse(null);
    }
}
