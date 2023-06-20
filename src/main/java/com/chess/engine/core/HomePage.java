package com.chess.engine.core;

import javafx.application.HostServices;
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
import static com.chess.engine.utils.Constants.Paths.GITHUB_PATH;
import static com.chess.engine.utils.Constants.Textures.*;

public class HomePage extends StackPane {

    private final StackPane title = new StackPane();
    private final HBox buttons = new HBox();

    private final HostServices host;

    private final EventHandler<Event> onStart;

    public HomePage(HostServices host, EventHandler<Event> onStart) {

        this.host = host;
        this.onStart = onStart;

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

        Label name = new Label("4 PLAYER CHESS");
        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), HOME_PAGE_HEIGHT / 8);
        name.setFont(customFont);

        name.setTextFill(Color.BLACK);
        name.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        title.getChildren().add(name);
    }

    private void createButtons() {

        StackPane playButton = new StackPane();
        StackPane creditsButton = new StackPane();
        StackPane exitButton = new StackPane();

        setButton(playButton, "PLAY");
        setButton(creditsButton, "CREDITS");
        setButton(exitButton, "EXIT");

        playButton.setOnMouseClicked(e -> onStart.handle(null));
        creditsButton.setOnMouseClicked(e -> host.showDocument(GITHUB_PATH));
        exitButton.setOnMouseClicked(e -> System.exit(0));

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.getChildren().addAll(playButton, creditsButton, exitButton);
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
