package com.chess.engine.core;

import com.chess.engine.pieces.PieceColor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static com.chess.engine.utils.Constants.Sizes.*;

public class ScoreBoard extends Rectangle {
    public ScoreBoard(PieceColor color) {
        setWidth(SCOREBOARD_SIZE);
        setHeight(SCOREBOARD_SIZE);

        double endX = (TILE_SIZE * (Board.WIDTH - 3)) + ((TILE_SIZE * 3) - SCOREBOARD_SIZE);
        double endY = (TILE_SIZE * (Board.HEIGHT - 3)) + ((TILE_SIZE * 3) - SCOREBOARD_SIZE);

        switch (color) {
            case RED:
                relocate(endX, endY);
                setFill(Color.RED);
                break;
            case BLUE:
                relocate(0, endY);
                setFill(Color.BLUE);
                break;
            case YELLOW:
                relocate(0, 0);
                setFill(Color.YELLOW);
                break;
            case GREEN:
                relocate(endX, 0);
                setFill(Color.GREEN);
                break;
        }
    }


}


