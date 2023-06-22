# 4-Player Chess

## Introduction

This is our small university project which required us to make 4-Player based game.

We decided on 4-Player chess variant created from scratch in Java with JavaFX platform.

Hope **You** will enjoy it! 💚

## Description

It is a variant of chess which follows most of the standard rules like validating a move.

However, there are some small differences that were made with gameplay improvements in mind!

The game supports dynamic and aggressive playstyle for which player is awarded.

## Rules

Despite the standard [Chess Rules](https://www.fide.com/FIDE/handbook/LawsOfChess.pdf) there are minor differences:
- The game is played on 14x14 board with 3x3 cut corners
- The pawn promotion line is behind the middle axis of the board
- If player is defeated their pieces are cleared from the board
- The game is played until there is last player standing
- Player gets points for capturing enemy pieces
- The player who has the highest score wins

## Piece Values

Each piece has its own value that is added to the player score after a capture:

| Type   | Value |
|--------|-------|
| Pawn   | 1     |
| Knight | 3     |
| Bishop | 3     |
| Rook   | 5     |
| Queen  | 9     |
| King   | 20    |

## Key Bindings

For the most part the game is played with the use of a mouse. However there are keys for:
- changing fullscreen mode ***(F)***
- quiting the game ***(Q)***

## Postscriptum

Huge thanks to the [Fennyon](https://www.reddit.com/user/Fennyon/) for allowing us to modify & use his artwork as our piece assets.

The game needs a lot of testing so feel free to do it and give your feedback which is very welcome!

Hopefully I will furthermore develop this project in the future 🤩

## Look of the board

![Board](https://github.com/Agrunek/4-Player-Chess/assets/108937456/4a984db4-043b-4e90-996e-b8d26e3692a5)
