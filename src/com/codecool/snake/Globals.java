package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1450;
    public static final double WINDOW_HEIGHT = 800;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("dragon-head2.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("FirstEnemy", new Image("enemy1.png"));
        resources.addImage("SecondEnemy", new Image("enemy2.png"));
        resources.addImage("ThirdEnemy", new Image("enemy3.png"));
        resources.addImage("DragonBall1", new Image("dragonball1.png"));
        resources.addImage("powerup_speed", new Image("powerup_speed.png"));
        resources.addImage("DragonBall5", new Image("dragonball5.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() {
        gameLoop.stop();
        GameTimer.spawnSpeedUptimer.stop();
        GameTimer.spawnDragonball1timer.stop();
        GameTimer.spawnEnemy1timer.stop();
        GameTimer.spawnEnemy2timer.stop();
        GameTimer.spawnEnemy3timer.stop();
        GameTimer.spawnDragonball5timer.stop();
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }


}
