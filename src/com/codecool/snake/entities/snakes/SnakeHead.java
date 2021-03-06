package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.DragonBall1;
import com.codecool.snake.entities.powerups.DragonBall3;
import com.codecool.snake.entities.powerups.DragonBall5;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void entitiesCollision(GameEntity entity) {
        System.out.println(getMessage());
        if(entity instanceof Enemy){
            snake.changeHealth(((Enemy) entity).getDamage());
            String health = "Health: " + snake.getHealth();
            Globals.getInstance().display.changeHealtTitle(health);
            snake.removePart(1);
            snake.updateBodyCounter(-1);
        }

        if(entity instanceof DragonBall1){
            snake.addPart(1);
            snake.updateBodyCounter(1);
        }
        if(entity instanceof DragonBall3){
            snake.increaseSpeed(0.3f);
            turnRate += 0.3;
        }
        if(entity instanceof DragonBall5){
            snake.addPart(5);
            snake.updateBodyCounter(5);
        }

        System.out.println(snake.getBodyCounter());
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
