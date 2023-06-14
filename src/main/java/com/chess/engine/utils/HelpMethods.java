package com.chess.engine.utils;

import com.chess.engine.core.Board;
import com.chess.engine.core.Tile;
import com.chess.engine.pieces.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.chess.engine.pieces.PieceColor.*;
import static com.chess.engine.pieces.PieceType.*;
import static com.chess.engine.utils.Constants.Sizes.FIGURE_SIZE;
import static com.chess.engine.utils.Constants.Sizes.TILE_SIZE;
import static com.chess.engine.utils.Constants.Textures.ROOT_PATH;

public class HelpMethods {

    public static class Validation {

        public static boolean defaultValidate(Board board, Piece piece, int x, int y) {

            if (x < 0 || y < 0 || x >= Board.WIDTH || y >= Board.HEIGHT) {
                return false;
            }

            if (piece.getPoint().getX() == x && piece.getPoint().getY() == y) {
                return false;
            }

            if (board.getTile(x, y) == null) {
                return false;
            }

            if (!board.getTile(x, y).hasPiece()) {
                return true;
            }

            return board.getTile(x, y).getPiece().getColor() != piece.getColor();
        }

        public static boolean interruptValidate(Board board, Piece piece, int x, int y) {

            int tempX = piece.getPoint().getX();
            int tempY = piece.getPoint().getY();

            do {

                tempX += (tempX == x) ? 0 : (tempX < x) ? 1 : -1;
                tempY += (tempY == y) ? 0 : (tempY < y) ? 1 : -1;

                if (board.getTile(tempX, tempY) == null) {
                    return false;
                }

                if (tempX == x && tempY == y) {
                    return true;
                }

            } while (!board.getTile(tempX, tempY).hasPiece());

            return false;
        }

        public static boolean checkValidate(Board board, Piece piece, int x, int y) {

            Piece temp = board.getTile(x, y).getPiece();
            board.getPieces().remove(temp);

            int oldX = piece.getPoint().getX();
            int oldY = piece.getPoint().getY();

            piece.getPoint().setX(x);
            piece.getPoint().setY(y);

            board.getTile(x, y).setPiece(piece);
            board.getTile(oldX, oldY).setPiece(null);

            Point target = board.getKings().get(piece.getColor()).getPoint();

            boolean inCheck = board.getPieces().stream().anyMatch(e -> e.validate(board, target.getX(), target.getY()));

            piece.getPoint().setX(oldX);
            piece.getPoint().setY(oldY);

            board.getTile(x, y).setPiece(temp);
            board.getTile(oldX, oldY).setPiece(piece);

            if (temp != null) {
                board.getPieces().add(temp);
            }

            return !inCheck;
        }
    }

    public static class Initialization {

        public static void initTiles(Tile[][] tiles) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                for (int x = 0; x < Board.WIDTH; x++) {
                    if ((y > 2 && y < Board.HEIGHT - 3) || (x > 2 && x < Board.WIDTH - 3)) {
                        tiles[y][x] = new Tile(x, y);
                    }
                }
            }
        }

        public static void initPieces(Board board) {
            initRedPieces(board);
            initBluePieces(board);
            initYellowPieces(board);
            initGreenPieces(board);
        }

        private static void initRedPieces(Board board) {

            addPiece(board, RED, ROOK, 3, 13);
            addPiece(board, RED, KNIGHT, 4, 13);
            addPiece(board, RED, BISHOP, 5, 13);
            addPiece(board, RED, QUEEN, 6, 13);
            addPiece(board, RED, KING, 7, 13);
            addPiece(board, RED, BISHOP, 8, 13);
            addPiece(board, RED, KNIGHT, 9, 13);
            addPiece(board, RED, ROOK, 10, 13);

            for (int x = 3; x <= 10; x++) {
                addPiece(board, RED, PAWN, x, 12);
            }
        }

        private static void initBluePieces(Board board) {

            addPiece(board, BLUE, ROOK, 0, 3);
            addPiece(board, BLUE, KNIGHT, 0, 4);
            addPiece(board, BLUE, BISHOP, 0, 5);
            addPiece(board, BLUE, QUEEN, 0, 6);
            addPiece(board, BLUE, KING, 0, 7);
            addPiece(board, BLUE, BISHOP, 0, 8);
            addPiece(board, BLUE, KNIGHT, 0, 9);
            addPiece(board, BLUE, ROOK, 0, 10);

            for (int y = 3; y <= 10; y++) {
                addPiece(board, BLUE, PAWN, 1, y);
            }
        }

        private static void initYellowPieces(Board board) {

            addPiece(board, YELLOW, ROOK, 3, 0);
            addPiece(board, YELLOW, KNIGHT, 4, 0);
            addPiece(board, YELLOW, BISHOP, 5, 0);
            addPiece(board, YELLOW, KING, 6, 0);
            addPiece(board, YELLOW, QUEEN, 7, 0);
            addPiece(board, YELLOW, BISHOP, 8, 0);
            addPiece(board, YELLOW, KNIGHT, 9, 0);
            addPiece(board, YELLOW, ROOK, 10, 0);

            for (int x = 3; x <= 10; x++) {
                addPiece(board, YELLOW, PAWN, x, 1);
            }
        }

        private static void initGreenPieces(Board board) {

            addPiece(board, GREEN, ROOK, 13, 3);
            addPiece(board, GREEN, KNIGHT, 13, 4);
            addPiece(board, GREEN, BISHOP, 13, 5);
            addPiece(board, GREEN, KING, 13, 6);
            addPiece(board, GREEN, QUEEN, 13, 7);
            addPiece(board, GREEN, BISHOP, 13, 8);
            addPiece(board, GREEN, KNIGHT, 13, 9);
            addPiece(board, GREEN, ROOK, 13, 10);

            for (int y = 3; y <= 10; y++) {
                addPiece(board, GREEN, PAWN, 12, y);
            }
        }

        private static void addPiece(Board board, PieceColor color, PieceType type, int x, int y) {

            if (board.getTile(x, y) == null) {
                return;
            }

            Piece piece = switch (type) {
                case PAWN -> new Pawn(color, x, y);
                case KNIGHT -> new Knight(color, x, y);
                case BISHOP -> new Bishop(color, x, y);
                case ROOK -> new Rook(color, x, y);
                case QUEEN -> new Queen(color, x, y);
                case KING -> {
                    King king = new King(color, x, y);
                    board.getKings().put(color, king);
                    yield king;
                }
            };

            board.getPieces().add(piece);
            board.getTile(x, y).setPiece(piece);
        }
    }

    public static class ImageManagement {

        public static ImageView getPieceImage(PieceColor color, PieceType type) {
            String path = ROOT_PATH + getColorCode(color) + "_" + getTypeCode(type) + ".png";
            return new ImageView(new Image(path, FIGURE_SIZE, FIGURE_SIZE, true, false));
        }

        public static ImageView getTileImage(int x, int y) {
            String color = ((x + y) % 2 == 0) ? "red" : "white";
            String path = ROOT_PATH + "tile_" + color + ".png";
            return new ImageView(new Image(path, TILE_SIZE, TILE_SIZE, true, false));
        }

        public static ImageView getTileImageHighilight(int x, int y) {
            String color = ((x + y) % 2 == 0) ? "red" : "white";
            String path = ROOT_PATH + "tile_" + color + "_highlight.png";
            return new ImageView(new Image(path, TILE_SIZE, TILE_SIZE, true, false));
        }


        private static char getColorCode(PieceColor color) {
            return color.toString().charAt(0);
        }

        private static String getTypeCode(PieceType type) {
            return type.toString();
        }
    }
}
