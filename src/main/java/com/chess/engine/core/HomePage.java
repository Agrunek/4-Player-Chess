package com.chess.engine.core;

import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

import static com.chess.engine.utils.Constants.Colors.*;
import static com.chess.engine.utils.Constants.Sizes.*;
import static com.chess.engine.utils.Constants.Paths.*;
import static com.chess.engine.utils.Constants.Textures.*;

public class HomePage extends StackPane {
    private final StackPane title = new StackPane();
    private final HBox buttons = new HBox();
    private final Label name = new Label("4 PLAYER CHESS");
    private final StackPane playButton = new StackPane();
    private final StackPane creditsButton = new StackPane();
    private final StackPane exitButton = new StackPane();

    private final HostServices host;

    public HomePage(HostServices host) {
        this.host = host;
        createTitle();
        createButtons();

        VBox menuContent = new VBox();
        menuContent.getChildren().addAll(title, buttons);
        menuContent.setSpacing(40);
        menuContent.setAlignment(Pos.CENTER);
        ImageView background = new ImageView(new Image(BACKGROUND_TEXTURE_PATH, HOME_PAGE_WIDTH, HOME_PAGE_HEIGHT, true, false));
        getChildren().addAll(background, menuContent);
    }

    private void createButtons() {
        setButton(playButton, "PLAY");
        setButton(creditsButton, "CREDITS");
        setButton(exitButton, "EXIT");
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        buttons.getChildren().addAll(playButton, creditsButton, exitButton);
    }

    private void setButton(StackPane button, String text) {
        Label label = new Label();
        label.setText(text);
        label.setTextFill(Color.valueOf(FONT_COLOR));
        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), 32);
        label.setFont(customFont);
        ImageView bg = new ImageView(new Image(BUTTON_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        switch (text) {
            case "PLAY" -> {
                button.setOnMouseEntered((e) -> buttonHover(e, bg, label));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg, label));
            }

            case "CREDITS" -> {
                button.setOnMouseEntered((e) -> buttonHover(e, bg, label));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg, label));
                button.setOnMouseClicked(this::credits);
            }
            case "EXIT" -> {
                button.setOnMouseEntered((e) -> buttonHover(e, bg, label));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg, label));
                button.setOnMouseClicked((e) -> System.exit(0));
            }
        }
        button.getChildren().addAll(bg, label);
    }

    public StackPane getPlayButton() {
        return this.playButton;
    }

    private void credits(MouseEvent e) {
        host.showDocument(GITHUB_PATH);
    }

    private void buttonHover(MouseEvent e, ImageView background, Label text) {
        background.setImage(new Image(BUTTON_HOVER_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        text.setTextFill(Color.valueOf(FONT_HOVER_COLOR));
    }

    private void buttonExitHover(MouseEvent e, ImageView background, Label text) {
        background.setImage(new Image(BUTTON_TEXTURE_PATH, BUTTON_WIDTH, BUTTON_HEIGHT, true, false));
        text.setTextFill(Color.valueOf(FONT_COLOR));
    }

    private void createTitle() {
        name.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), HOME_PAGE_HEIGHT / 8);
        name.setFont(customFont);
        name.setTextFill(Color.BLACK);

        title.getChildren().add(name);
    }
}
