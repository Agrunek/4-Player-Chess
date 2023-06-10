package com.chess.engine.core;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import static com.chess.engine.utils.Constants.Sizes.*;

public class ScoreBoard extends StackPane {

    private final Label label = new Label();
    private final Rectangle background = new Rectangle();

    public ScoreBoard(Player player) {
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

        label.setText("SCORE:" + player.getScore());

        getChildren().add(background);
        getChildren().add(label);
    }


}


