package com.example.snlgame;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeAndLadderGame extends Application {

    public static int rand;
    public Label randResult;
//    public int blockWidth;
//    public int blockHeight;
    public int[][] cirPosX = new int[10][10];
    public int[][] cirPosY = new int[10][10];
    public int[][] ladderPos = new int[6][3];
    public static final int tileSize = 60;
    public static final int width = 10;
    public static final int height = 10;
    public Circle player1;
    public Circle player2;
//    public int player1Position;
//    public int player2Position;
    public boolean player1Turn = true;
    public boolean player2Turn = false;
    public static int player1XPos = 30;
    public static int player1YPos = 570;
    public static int player2XPos = 30;
    public static int player2YPos = 570;
    public int posCir1 = 1;
    public int posCir2 = 1;
    public boolean gameStart;
    public Button gameButton;
    private final Group tileGroup = new Group();


    private void getDiceValue () {

        rand = (int) (Math.random() * 6 + 1);
    }

    private void traslatePlayer (int x, int y, Circle b) {
        TranslateTransition animate= new TranslateTransition (Duration.millis(1000), b);
        animate.setToX (x);
        animate.setToY (y);
        animate.setAutoReverse (false);
        animate.play();
    }

    private void move1Player () {
        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1)
                player1XPos += 60;
            if (posCir1 % 2 == 0) {
                player1XPos -= 60;
            }
            if (player1XPos > 570) {
                player1YPos -= 60;
                player1XPos -= 60;
                posCir1++;
            }
            if (player1XPos < 30) {
                player1YPos -= 60;
                player1XPos += 60;
                posCir1++;
            }
            if (player1XPos <= 30 && player1YPos <= 30) {
                player1XPos = 30;
                player1YPos = 30;
                randResult.setText("Player One Won");
                gameButton.setText("start Again?");
            }
            if(player1XPos <30 && player1YPos<=30){
                break;
            }
        }
    }

    private void move2Player () {
        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos += 60;
            }
            if (posCir2 % 2 == 0) {
                player2XPos -= 60;
            }
            if (player2XPos > 570) {
                player2YPos -= 60;
                player2XPos -= 60;
                posCir2++;
            }
            if (player2XPos < 30) {
                player2YPos -= 60;
                player2XPos += 60;
                posCir2++;
            }
            if (player2XPos < 30 && player2YPos < 30) {
                player2XPos = 30;
                player2YPos = 30;
                randResult.setText("Player Two Won");
                gameButton.setText("start Again");
            }
            if(player2XPos <30 && player2YPos<=30){
                break;
            }
        }
    }

    private Parent createContent() {
        StackPane root = new StackPane();
        root.setPrefSize (width* tileSize, (height * tileSize) +80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++){
                Tile tile = new Tile(tileSize,tileSize);
                tile.setTranslateX (j * tileSize);
                tile.setTranslateY (i * tileSize);
                tileGroup.getChildren().add (tile);

                cirPosX[i][j] = j*tileSize;
                cirPosY[i][j] = i*tileSize;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print (cirPosX[i] [j]+ " ");
            }
            System.out.println();
        }



        player1 = new Circle ( 30);
        player1.setFill(Color.CHOCOLATE);
        player1.setId("player1");
        player1.getStyleClass ().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY (player1YPos);


        player2 = new Circle ( 30);
        player2.setFill(Color.AQUA);
        player2.setId("player1");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY (player2YPos);

        Button button1 = new Button ( "Player1");
        button1.setTranslateX (500);
        button1.setTranslateY (620);
        button1.setOnAction (new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                if(gameStart){
                    if (player1Turn) {
                        getDiceValue();
                        randResult.setText(String.valueOf(rand)) ;
                        move1Player () ;
                        traslatePlayer (player1XPos, player1YPos, player1);
                        player1Turn = false;
                        player2Turn = true;
                    }
                }
            }
        });

        Button button2 = new Button ("player2");
        button2.setTranslateX (60);
        button2.setTranslateY (620);
        button2.setOnAction (new EventHandler<ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceValue();
                        randResult.setText (String.valueOf(rand)) ;
                        move2Player();
                        traslatePlayer (player2XPos, player2YPos, player2);
                        player2Turn = false;
                        player1Turn = true;

                        for(int i=0;i<ladderPos.length;i++){
                            if(ladderPos[i][0]==player1XPos && ladderPos[i][1]==player1YPos){
                                break;
                            }

                        }
                    }
                }
            }
        });

        gameButton = new Button ( "Start Game");
        gameButton.setTranslateX (280);
        gameButton.setTranslateY (620);
        gameButton.setOnAction (new EventHandler<ActionEvent> () {
            @Override
            public void handle (ActionEvent event) {
                gameButton.setText ("Game Stated");
                player1XPos = 30;
                player1YPos = 570;
                player2XPos = 30;
                player2YPos = 570;
                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);
                player2.setTranslateX(player2XPos);
                player2.setTranslateY (player2YPos);
                gameStart = true;
            }
        });

        randResult = new Label("0");
        randResult.setTranslateX (240);
        randResult.setTranslateY (620);
        Image img = new Image ("D:\\java-2022-06\\workplace_1\\Learnings\\SNLGame" +
                        "\\src\\main\\java\\com\\example\\snlgame\\snakebg1.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage (img);
        bgImage.setFitHeight (600);
        bgImage.setFitWidth (600);


        tileGroup.getChildren().addAll (bgImage,player1,player2,button1,
                button2,gameButton,randResult);

        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene (createContent ());
        primaryStage.setTitle ("Snake and Ladder");
        primaryStage.setScene (scene);
        primaryStage.show ();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        };
    }

    public static void main(String[] args) {
        launch();
    }
}