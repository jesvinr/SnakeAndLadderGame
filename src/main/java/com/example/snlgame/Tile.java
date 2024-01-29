package com.example.snlgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int x, int y){
        setWidth (SnakeAndLadderGame.tileSize);
        setHeight (SnakeAndLadderGame.tileSize);

        setFill(Color.AQUA);
        setStroke(Color.BLACK);
    }
}
