package com.chess.engine.core;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

import static com.chess.engine.utils.Constants.Colors.FONT_COLOR;
import static com.chess.engine.utils.Constants.Colors.FONT_HOVER_COLOR;
import static com.chess.engine.utils.Constants.Sizes.*;
import static com.chess.engine.utils.Constants.Textures.*;

public class EndingScreen extends StackPane {

    private final StackPane title = new StackPane();
    private final HBox buttons = new HBox();

    private final Player winner;

    private final EventHandler<Event> onRestart;

    public EndingScreen(Player winner, EventHandler<Event> onRestart) {

        this.winner = winner;
        this.onRestart = onRestart;

        createTitle();
        createButtons();

        VBox menuContent = new VBox();
        menuContent.setAlignment(Pos.CENTER);
        menuContent.setSpacing(40);
        menuContent.getChildren().addAll(title, buttons);

        ImageView background = new ImageView(new Image(BACKGROUND_TEXTURE_PATH, HOME_PAGE_WIDTH, HOME_PAGE_HEIGHT, true, false));
        getChildren().addAll(background, menuContent);
    }

    private void createTitle() {

        Label announcement = new Label("Player " + winner.getColor().toString() + " wins!");
        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), HOME_PAGE_HEIGHT / 8);
        announcement.setFont(customFont);

        announcement.setTextFill(Color.BLACK);
        announcement.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        title.getChildren().add(announcement);
    }

    private void createButtons() {

        StackPane playAgainButton = new StackPane();
        StackPane exitButton = new StackPane();

        setButton(playAgainButton, "PLAY AGAIN");
        setButton(exitButton, "EXIT");

        playAgainButton.setOnMouseClicked(e -> onRestart.handle(null));
        exitButton.setOnMouseClicked(e -> System.exit(0));

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.getChildren().addAll(playAgainButton, exitButton);
    }

    private void setButton(StackPane button, String text) {

        Label label = new Label(text);
        label.setTextFill(Color.valueOf(FONT_COLOR));

        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), 32);
        label.setFont(customFont);

        ImageView background = new ImageView(new Image(BUTTON_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        button.setOnMouseEntered(e -> buttonHover(background, label));
        button.setOnMouseExited(e -> buttonExitHover(background, label));

        button.getChildren().addAll(background, label);
    }

    private void buttonHover(ImageView background, Label text) {
        background.setImage(new Image(BUTTON_HOVER_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        text.setTextFill(Color.valueOf(FONT_HOVER_COLOR));
    }

    private void buttonExitHover(ImageView background, Label text) {
        background.setImage(new Image(BUTTON_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        text.setTextFill(Color.valueOf(FONT_COLOR));
    }
}
