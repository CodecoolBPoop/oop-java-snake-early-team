package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.FirstEnemy;
import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.DragonBall3;
import com.codecool.snake.entities.powerups.DragonBall5;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class Game extends Pane {
    private Button restartBtn = new Button("Restart");
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {

        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        BackgroundImage myBI= new BackgroundImage(new Image("background.png",1000,700,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        spawnSnake();
        spawnEnemies(2);
        spawnPowerUps(2);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        getChildren().add(restartBtn);
        addRestartButtonEventHandlers();

    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i){
            new FirstEnemy();
            new SecondEnemy();
            new ThirdEnemy();
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i){
            new SimplePowerUp();
            new DragonBall3();
            new DragonBall5();
        }
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void addRestartButtonEventHandlers() {
        restartBtn.setOnAction((event -> restartGame()));
    }

    private void restartGame() {

        this.getChildren().clear();

        init();
        start();

    }
}
