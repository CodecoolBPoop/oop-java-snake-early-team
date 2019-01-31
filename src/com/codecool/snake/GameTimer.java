package com.codecool.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class GameTimer {
    private static final double DEFAULT_60_FPS = 0.017;
    private double frameTime;
    public static Timeline timer = new Timeline();
    public static Timeline spawnEnemy1timer = new Timeline();
    public static Timeline spawnEnemy2timer = new Timeline();
    public static Timeline spawnEnemy3timer = new Timeline();
    public static Timeline spawnSpeedUptimer = new Timeline();
    public static Timeline spawnDragonball1timer = new Timeline();
    public static Timeline spawnDragonball5timer = new Timeline();

    GameTimer() {
        this(DEFAULT_60_FPS);
    }

    GameTimer(double frameTime) {
        this.frameTime = frameTime;
        System.out.println("GameTimer created with frame time: " + frameTime);
    }

    public void setup(Runnable loopMethod) {
        timer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime),
                ae -> loopMethod.run());
        timer.getKeyFrames().add( kf );
    }

    public void setSpawnEnemy1(){
        spawnEnemy1timer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*700),
                ae -> Game.spawnEnemies(1,"FirstEnemy"));
        spawnEnemy1timer.getKeyFrames().add( kf );

    }


    public void setSpawnEnemy2(){
        spawnEnemy2timer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*500),
                ae -> Game.spawnEnemies(1,"SecondEnemy"));
        spawnEnemy2timer.getKeyFrames().add( kf );

    }

    public void setSpawnEnemy3(){
        spawnEnemy3timer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*300),
                ae -> Game.spawnEnemies(1,"ThirdEnemy"));
        spawnEnemy3timer.getKeyFrames().add( kf );

    }


    public void setSpeedUp(){
        spawnSpeedUptimer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*170),
                ae -> Game.spawnPowerUps(1,"DragonBall3"));
        spawnSpeedUptimer.getKeyFrames().add( kf );

    }

    public void setDragonBall1(){
        spawnSpeedUptimer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*230),
                ae -> Game.spawnPowerUps(1,"DragonBall1"));
        spawnSpeedUptimer.getKeyFrames().add( kf );

    }

    public void setDragonBall5(){
        spawnSpeedUptimer.setCycleCount( Timeline.INDEFINITE );

        KeyFrame kf = new KeyFrame(
                Duration.seconds(frameTime*400),
                ae -> Game.spawnPowerUps(1,"DragonBall5"));
        spawnSpeedUptimer.getKeyFrames().add( kf );

    }


    public void play() {
        timer.play();
        spawnEnemy1timer.play();
        spawnEnemy2timer.play();
        spawnSpeedUptimer.play();
        spawnSpeedUptimer.play();
        spawnDragonball1timer.play();
        spawnDragonball5timer.play();

    }

}
