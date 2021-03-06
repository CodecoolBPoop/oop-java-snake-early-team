package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;



public class FirstEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public FirstEnemy() {
        super(5);

        setImage(Globals.getInstance().getImage("FirstEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextDouble() * 360;
        //setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }



    @Override
    public void step() {
        if (isOutOfBounds()) {
            double direction = rnd.nextDouble() * 360;
            int speed = 2;
            heading = Utils.directionToVector(direction, speed);
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());

        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void entitiesCollision(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
