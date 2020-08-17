package plakolb;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends Application {

    int speed = 5;
    int foodColor = 0;
    int width = 20;
    int height = 20;
    int foodX = 0;
    int foodY = 0;
    int snekElementSize = 25;
    int score = 0;
    List<SnakeElement> snek = new ArrayList<>();
    Direction direction = Direction.left;
    boolean gameOver = false;
    Random numberForFood = new Random();

    public enum Direction {
        left,
        right,
        up,
        down
    }

    //SnakeGOOEY:
    @Override
    public void start(Stage stage) throws Exception {
        //stage styling
        stage.setTitle("SnakeSnack!");
        stage.setWidth(900);
        stage.setHeight(675);

        //creating new food on playground
        newFood();

        //root node
        BorderPane rootPane = new BorderPane();
        rootPane.getStyleClass().add("root-pane");
        rootPane.setPrefSize(900, 675);

        //node for title
        Label gameTitle = new Label("Snake Snack!");
        gameTitle.setTextFill(Paint.valueOf("#F29D52"));
        gameTitle.setFont(new Font("Bahnschrift", 25));
        gameTitle.setId("game-title");

        //node for canvas
        Canvas playground = new Canvas(width*snekElementSize, height*snekElementSize);
        GraphicsContext context = playground.getGraphicsContext2D();

        AnimationTimer animationTimer = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(context);
                    return;
                }

                if (now - lastTick > 1_000_000_000 / speed) {
                    lastTick = now;
                    tick(context);
                }
            }
        };

        animationTimer.start();

        //node for buttons
        HBox buttonBox = new HBox();
        buttonBox.getStyleClass().add("button-box");
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);

        Button restartButton = new Button("Restart Game");
        restartButton.setFont(new Font("Consolas", 12));
        restartButton.setOnAction(event -> {
            try {
                animationTimer.stop();
                restartGame(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button backToMenu = new Button("Back to Menu");
        backToMenu.setFont(new Font("Consolas", 12));
        backToMenu.setOnAction(event -> {
            try {
                ArcadeGOOEY arcadeGOOEY = new ArcadeGOOEY();
                arcadeGOOEY.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        buttonBox.getChildren().addAll(restartButton, backToMenu);

        //fill border pane
        rootPane.setCenter(playground);
        rootPane.setTop(gameTitle);
        rootPane.setBottom(buttonBox);

        //set scene...
        Scene snakeGame = new Scene(rootPane);

        //controlling the snek
        snakeGame.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                direction = Direction.up;
            }
            if (key.getCode() == KeyCode.A) {
                direction = Direction.left;
            }
            if (key.getCode() == KeyCode.S) {
                direction = Direction.down;
            }
            if (key.getCode() == KeyCode.D) {
                direction = Direction.right;
            }
        });

        //optimize control that you cannot override snek with itself (e.g. if direction == left you cannot go right)

        //add start snek elements
        snek.add(new SnakeElement(width/2, height/2));
        snek.add(new SnakeElement(width/2, height/2));
        snek.add(new SnakeElement(width/2, height/2));

        //... and ACTION!
        stage.setScene(snakeGame);
        stage.centerOnScreen();
        stage.show();

    }

    public void tick(GraphicsContext context) {
        if (gameOver) {
            context.setFill(Paint.valueOf("#F29D52"));
            context.setFont(new Font("Consolas", 40));
            context.fillText("GAME OVER", 150, 250);
            return;
        }

        //moving snek
        for (int i = snek.size() - 1; i >= 1; i--) {
            snek.get(i).xValue = snek.get(i-1).xValue;
            snek.get(i).yValue = snek.get(i-1).yValue;
        }

        switch (direction) {
            case up:
                snek.get(0).yValue--;
                if (snek.get(0).yValue < 0) {
                    gameOver = true;
                }
                break;
            case down:
                snek.get(0).yValue++;
                if (snek.get(0).yValue > height) {
                    gameOver = true;
                }
                break;
            case left:
                snek.get(0).xValue--;
                if (snek.get(0).xValue < 0) {
                    gameOver = true;
                }
                break;
            case right:
                snek.get(0).xValue++;
                if (snek.get(0).xValue > width) {
                    gameOver = true;
                }
                break;
        }

        //game over when snek bites itself
        for (int i = 1; i < snek.size(); i++) {
            if (snek.get(0).xValue == snek.get(i).xValue && snek.get(0).yValue == snek.get(i).yValue) {
                gameOver = true;
            }
        }

        //eat food
        if (foodX == snek.get(0).xValue && foodY == snek.get(0).yValue) {
            snek.add(new SnakeElement(-1, -1));
            score++;
            newFood();
        }

        //coloring the playground + showing score
        context.setFill(Paint.valueOf("black"));
        context.fillRect(0, 0, width*snekElementSize, height*snekElementSize);


        //food color
        context.setFill(Paint.valueOf("#04D1BF"));
        context.fillOval(foodX*snekElementSize, foodY*snekElementSize, snekElementSize, snekElementSize);

        //snek color
        for (SnakeElement element : snek) {
            context.setFill(Paint.valueOf("#F2636F"));
            context.fillRect(element.xValue*snekElementSize, element.yValue*snekElementSize, snekElementSize-1, snekElementSize-1);
        }

        context.setFill(Paint.valueOf("white"));
        context.setFont(new Font("Consolas", 12));
        context.fillText("Score: " + score, 10, 30);

    }

    public void newFood() {

        start: while (true) {
            foodX = numberForFood.nextInt(width);
            foodY = numberForFood.nextInt(height);

            for (SnakeElement snekElement : snek) {
                if (snekElement.xValue == foodX && snekElement.yValue == foodY) {
                    continue start;
                }
            }
            foodColor = numberForFood.nextInt(5);
            //speed++;
            break;

        }

    }

    public void restartGame(Stage stage) throws Exception {

        speed = 5;
        gameOver = false;
        score = 0;
        snek.clear();
        start(stage);

    }

}
