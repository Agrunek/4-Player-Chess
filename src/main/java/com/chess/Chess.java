package com.chess;

import com.chess.engine.core.Board;
import com.chess.engine.core.EndingScreen;
import com.chess.engine.core.Game;
import com.chess.engine.core.HomePage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static com.chess.engine.utils.Constants.Colors.*;
import static com.chess.engine.utils.Constants.Sizes.*;

public class Chess extends Application {

    private Game game;
    private HomePage homePage;
    private EndingScreen endingScreen;

    private final StackPane root = new StackPane();
    private final StackPane gameContent = new StackPane();

    @Override
    public void start(Stage primaryStage) {

        game = new Game(update -> onFinished());
        homePage = new HomePage(getHostServices(), update -> onStart());

        gameContent.setBackground(new Background(new BackgroundFill(Color.valueOf(BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY)));
        gameContent.getChildren().add(game);
        root.getChildren().addAll(gameContent, homePage);

        Scene scene = new Scene(root);
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

    public void changeFullscreen(Stage primaryStage) {
        primaryStage.setFullScreen(!primaryStage.isFullScreen());
    }

    private void onStart() {
        root.getChildren().remove(homePage);
    }

    private void onFinished() {
        endingScreen = new EndingScreen(game.getWinner(), update -> onRestart());
        root.getChildren().add(endingScreen);
    }

    private void onRestart() {
        gameContent.getChildren().remove(game);
        game = new Game(update -> onFinished());
        gameContent.getChildren().add(game);
        root.getChildren().remove(endingScreen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
