package com.chess;

import com.chess.engine.core.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
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

        Scene scene = new Scene(root, Color.valueOf(BACKGROUND_COLOR));
        primaryStage.setScene(scene);

        primaryStage.setTitle("4p Chess");
        primaryStage.setMinWidth(TILE_SIZE * Board.WIDTH);
        primaryStage.setMinHeight(TILE_SIZE * Board.HEIGHT);
        primaryStage.getIcons().add(new Image("file:src/main/java/com/chess/gui/r_pawn.png"));

        primaryStage.setFullScreen(true);
        scene.setOnKeyPressed((e) -> keyHandler(e, primaryStage));

        primaryStage.show();
    }

    private void keyHandler(KeyEvent e, Stage primaryStage) {
        switch (e.getCode()) {
            case F -> changeFullscreen(primaryStage);
            case Q -> System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void changeFullscreen(Stage primaryStage) {
        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }
}
