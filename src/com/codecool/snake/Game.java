package com.codecool.snake;


import com.codecool.snake.entities.enemies.FirstEnemy;
import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.powerups.DragonBall1;
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
    public GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        BackgroundImage myBI= new BackgroundImage(new Image("background.png",1450,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));
        spawnSnake();
        spawnEnemies(2, "FirstEnemy");
        spawnEnemies(2, "SecondEnemy");
        spawnEnemies(2, "ThirdEnemy");
        spawnPowerUps(1, "DragonBall1");
        spawnPowerUps(3, "DragonBall3");
        spawnPowerUps(1, "DragonBall5");

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.setSpawnEnemy1();
        gameTimer.setSpawnEnemy2();
        gameTimer.setSpawnEnemy3();
        gameTimer.setDragonBall1();
        gameTimer.setDragonBall5();
        gameTimer.setSpeedUp();
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

    public static void spawnEnemies(int numberOfEnemies, String enemy) {
        for(int i = 0; i < numberOfEnemies; ++i){
            switch (enemy) {
                case "FirstEnemy": new FirstEnemy(); break;
                case "SecondEnemy": new SecondEnemy(); break;
                case "ThirdEnemy": new SecondEnemy(); break;
            }
        }
    }

    public static void spawnPowerUps(int numberOfPowerUps, String powerup) {
        for(int i = 0; i < numberOfPowerUps; ++i){
            switch (powerup){
                case "DragonBall1": new DragonBall1(); break;
                case "DragonBall3": new DragonBall3(); break;
                case "DragonBall5": new DragonBall5(); break;
            }
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
