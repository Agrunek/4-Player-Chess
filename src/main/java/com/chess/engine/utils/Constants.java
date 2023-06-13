package com.chess.engine.utils;

import com.chess.engine.core.Board;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class Constants {

    public static class Colors {
        public static final String BACKGROUND_COLOR = "#1E1E1E";

        public static final Color RED_PLAYER = Color.valueOf("#E71B1E");
        public static final Color BLUE_PLAYER = Color.valueOf("#39ABE2");
        public static final Color GREEN_PLAYER = Color.valueOf("#4D8D48");
        public static final Color YELLOW_PLAYER = Color.valueOf("#FFFF9A");


        public static final Color RED_PLAYER_HIGHLIGHT = Color.valueOf("#EAA55A");
        public static final Color BLUE_PLAYER_HIGHLIGHT = Color.valueOf("#312B33");
        public static final Color GREEN_PLAYER_HIGHLIGHT = Color.valueOf("#C699E9");
        public static final Color YELLOW_PLAYER_HIGHLIGHT = Color.valueOf("#32497C");

        public static final String FONT_HOVER_COLOR = "#FFFF55";
        public static final String FONT_COLOR = "#FFFFFF";
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

        public static final double SCOREBOARD_SIZE = TILE_SIZE * 3 * 0.90;
    }

    public static class Textures {

        public static final String ROOT_PATH = "file:src/main/java/com/chess/gui/";
        public static final String FONT_PATH = "src/main/java/com/chess/gui/mcFont.otf";
        public static final String BACKGROUND_TEXTURE_PATH = "file:src/main/java/com/chess/gui/wood.png";
        public static final String BUTTON_TEXTURE_PATH = "file:src/main/java/com/chess/gui/button.png";
        public static final String BUTTON_HOVER_TEXTURE_PATH = "file:src/main/java/com/chess/gui/buttonHover.png";

    }

    public static class Paths {

        public static final String GITHUB_PATH = "https://github.com/Agrunek/4-Player-Chess";
    }
}
