package com.chess;

import com.chess.engine.core.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.chess.engine.utils.Constants.Colors.*;
import static com.chess.engine.utils.Constants.Sizes.*;


public class Chess extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Board board = new Board();
        root.getChildren().add(board);
        primaryStage.setScene(new Scene(root, Color.valueOf(BACKGROUND_COLOR)));
        primaryStage.setTitle("4p Chess");
        primaryStage.setMinWidth(TILE_SIZE*Board.WIDTH);
        primaryStage.setMinHeight(TILE_SIZE*Board.HEIGHT);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
