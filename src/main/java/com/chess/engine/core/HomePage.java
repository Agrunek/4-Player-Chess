package com.chess.engine.core;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import static com.chess.engine.utils.Constants.Sizes.*;

public class HomePage extends StackPane {
    private final Rectangle background = new Rectangle(HOME_PAGE_WIDTH, HOME_PAGE_HEIGHT, Color.GRAY);
    private final StackPane title = new StackPane();
    private final VBox menuContent = new VBox();
    private final Label name = new Label("4 PLAYER CHESS");

    //
    public HomePage() {
        createTitle();

        menuContent.getChildren().addAll(title);
        menuContent.setAlignment(Pos.CENTER);
        getChildren().addAll(background, menuContent);
    }

    private void createTitle() {
        name.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        name.setFont(new Font("Comic Sans MS", HOME_PAGE_HEIGHT / 10));
        title.getChildren().add(name);
    }
}
