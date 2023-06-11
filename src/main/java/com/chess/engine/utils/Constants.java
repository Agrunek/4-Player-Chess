package com.chess.engine.utils;

import com.chess.engine.core.Board;
import javafx.stage.Screen;

public class Constants {

    public static class Colors {
        public static final String BACKGROUND_COLOR = "#1E1E1E";
    }

    public static class Sizes {

        public static final double MONITOR_HEIGHT = Screen.getPrimary().getBounds().getHeight();
        public static final double MONITOR_WIDTH = Screen.getPrimary().getBounds().getWidth();

        public static final double TILE_SIZE = MONITOR_HEIGHT / (Board.HEIGHT + 1.5);

        public static final double FIGURE_SIZE = TILE_SIZE * 0.90;

        public static final double SCOREBOARD_SIZE = TILE_SIZE * 3 * 0.90;
    }

    public static class Textures {

        public static final String ROOT_PATH = "file:src/main/java/com/chess/gui/";
    }
}
