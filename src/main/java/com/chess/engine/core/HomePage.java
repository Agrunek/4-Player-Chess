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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.File;

import static com.chess.engine.utils.Constants.Sizes.*;
import static com.chess.engine.utils.Constants.Paths.*;
import static com.chess.engine.utils.Constants.Textures.*;
import static javafx.scene.text.Font.loadFont;

public class HomePage extends StackPane {
    private final ImageView background = new ImageView(new Image(BACKGROUND_TEXTURE_PATH,HOME_PAGE_WIDTH, HOME_PAGE_HEIGHT,true,false));
    private final StackPane title = new StackPane();
    private final VBox menuContent = new VBox();
    private final HBox buttons = new HBox();
    private final Label name = new Label("4 PLAYER CHESS");
    private final StackPane playButton = new StackPane();
    private final StackPane creditsButton = new StackPane();
    private final StackPane exitButton = new StackPane();

    private final HostServices host;

    //
    public HomePage(HostServices host) {
        this.host = host;
        createTitle();
        createButtons();

        menuContent.getChildren().addAll(title, buttons);
        menuContent.setSpacing(40);
        menuContent.setAlignment(Pos.CENTER);
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
        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), 32);
        label.setFont(customFont);
        Rectangle bg = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT, Color.PINK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(2);
        switch (text) {
            case "PLAY" -> {
                button.setOnMouseEntered((e) -> buttonHover(e, bg));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg));
            }

            case "CREDITS" -> {

                button.setOnMouseEntered((e) -> buttonHover(e, bg));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg));
                button.setOnMouseClicked(this::credits);
            }
            case "EXIT" -> {
                button.setOnMouseEntered((e) -> buttonHover(e, bg));
                button.setOnMouseExited((e) -> buttonExitHover(e, bg));
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

    private void buttonHover(MouseEvent e, Rectangle background) {
        background.setStroke(Color.WHITE);
        background.setStrokeWidth(2);
    }

    private void buttonExitHover(MouseEvent e, Rectangle background) {
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(2);
    }

    private void createTitle() {
        name.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), HOME_PAGE_HEIGHT / 8);
        name.setFont(customFont);

        title.getChildren().add(name);
    }
}
