package com.chess;

import com.chess.engine.core.Board;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.chess.engine.utils.Constants.Colors.*;


public class Chess extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Group tileGroup = new Group();
        Board board = new Board();
        setBoard(board, tileGroup);
        root.getChildren().add(tileGroup);
        primaryStage.setScene(new Scene(root, Color.valueOf(BACKGROUND_COLOR)));
        primaryStage.setTitle("4p Chess");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void setBoard(Board board, Group tileGroup) {
        for (int y = 0; y < Board.HEIGHT; y++) {
            for (int x = 0; x < Board.WIDTH; x++) {
                if (board.getTile(x, y) != null) {
                    tileGroup.getChildren().add(board.getTile(x, y));
                }
            }
        }
    }

}
