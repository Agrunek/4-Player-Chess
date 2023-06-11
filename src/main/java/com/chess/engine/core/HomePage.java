package com.chess.engine.core;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import static com.chess.engine.utils.Constants.Sizes.*;

public class HomePage extends StackPane {
    private final Rectangle background = new Rectangle(HOME_PAGE_WIDTH, HOME_PAGE_HEIGHT, Color.GRAY);
    private final StackPane title = new StackPane();
    private final VBox menuContent = new VBox();
    private final HBox buttons = new HBox();
    private final Label name = new Label("4 PLAYER CHESS");
    private final StackPane playButton = new StackPane();
    private final StackPane creditsButton = new StackPane();
    private final StackPane exitButton = new StackPane();

    //
    public HomePage() {
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
        label.setFont(new Font("Comic Sans MS", HOME_PAGE_HEIGHT / 14));
        Rectangle bg = new Rectangle(BUTTON_WIDTH, BUTTON_HEIGHT, Color.PINK);
        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(2);
        button.getChildren().addAll(bg, label);
    }


    private void createTitle() {
        name.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        name.setFont(new Font("Comic Sans MS", HOME_PAGE_HEIGHT / 10));
        title.getChildren().add(name);
    }
}
