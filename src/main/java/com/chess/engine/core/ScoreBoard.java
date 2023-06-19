package com.chess.engine.core;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.io.File;

import static com.chess.engine.utils.Constants.Sizes.*;
import static com.chess.engine.utils.Constants.Textures.FONT_PATH;

public class ScoreBoard extends StackPane {
    private final Player player;
    private final Label score = new Label("0");
    private final Rectangle background = new Rectangle();

    public ScoreBoard(Player player) {
        this.player = player;

        background.setWidth(SCOREBOARD_SIZE);
        background.setHeight(SCOREBOARD_SIZE);

        double endX = (TILE_SIZE * (Board.WIDTH - 3)) + ((TILE_SIZE * 3) - SCOREBOARD_SIZE);
        double endY = (TILE_SIZE * (Board.HEIGHT - 3)) + ((TILE_SIZE * 3) - SCOREBOARD_SIZE);

        switch (player.getColor()) {
            case RED -> relocate(endX, endY);
            case BLUE -> relocate(0, endY);
            case YELLOW -> relocate(0, 0);
            case GREEN -> relocate(endX, 0);
        }

        background.setFill(player.getColor().getColor());

        Font customFont = Font.loadFont(new File(FONT_PATH).toURI().toString(), SCOREBOARD_SIZE / 5);
        VBox vBox = new VBox();
        Label heading = new Label("SCORE:");

        heading.setFont(customFont);
        score.setFont(customFont);

        heading.setTextFill(player.getColor().getTextColor());
        score.setTextFill(player.getColor().getTextColor());

        vBox.getChildren().add(heading);
        vBox.getChildren().add(score);
        vBox.setAlignment(Pos.CENTER);

        getChildren().add(background);
        getChildren().add(vBox);
    }

    public void updateScore() {
        score.setText(Integer.toString(player.getScore()));
    }

    public void highlightScore() {
        background.setStroke(player.getColor().getHighlightColor());
        background.setStrokeWidth(10);
        background.setStrokeType(StrokeType.INSIDE);
    }

    public void noHighlightScore() {
        background.setStroke(Color.TRANSPARENT);
    }
}
