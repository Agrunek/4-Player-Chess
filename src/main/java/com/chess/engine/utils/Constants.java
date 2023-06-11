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


        public static final double HOME_PAGE_HEIGHT = MONITOR_HEIGHT / 2;
        public static final double HOME_PAGE_WIDTH = MONITOR_WIDTH / 2;

        public static final double BUTTON_HEIGHT = HOME_PAGE_HEIGHT / 8;
        public static final double BUTTON_WIDTH = HOME_PAGE_WIDTH / 4;

    }

    public static class Textures {

        public static final String ROOT_PATH = "file:src/main/java/com/chess/gui/";
    }
}
