package com.chess.engine.core;

import com.chess.engine.pieces.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;

public class PromotionUI extends HBox {

    public PromotionUI(PieceColor color) {
        super();
        setAlignment(Pos.CENTER);
        setMaxHeight(TILE_SIZE*2);
        setMaxWidth(TILE_SIZE*5);
        setSpacing(20);
        setBackground(new Background(new BackgroundFill(new Color(0,0,0, 0.70), CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(createBox(PieceType.QUEEN, color));
        getChildren().add(createBox(PieceType.KNIGHT, color));
        getChildren().add(createBox(PieceType.BISHOP, color));
        getChildren().add(createBox(PieceType.ROOK, color));
    }


    private StackPane createBox(PieceType type, PieceColor color) {
        StackPane temp = new StackPane();
        switch (type) {
            case KNIGHT -> temp.getChildren().add(new Knight(color, 0, 0));
            case QUEEN -> temp.getChildren().add(new Queen(color, 0, 0));
            case BISHOP -> temp.getChildren().add(new Bishop(color, 0, 0));
            case ROOK -> temp.getChildren().add(new Rook(color, 0, 0));
        }

        return temp;
    }
}
