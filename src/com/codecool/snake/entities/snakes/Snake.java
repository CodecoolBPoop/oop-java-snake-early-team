package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {


    private static float speed = 2;
    private int health = 100;
    private int bodyCounter = 4;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();

        addPart(4);
    }

    public int getBodyCounter() {
        return bodyCounter;
    }

    public void updateBodyCounter(int count) {
        this.bodyCounter = this.bodyCounter + count;
    }

    public int getHealth() {
        return health;
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);


        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void removePart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            parent.destroy();
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
        body.remove(parent);
    }

    public void increaseSpeed(float amount) {
        speed += amount;
    }

    public void changeHealth(int diff) {
        health -= diff;
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0 || body.isEmpty()) {
            System.out.println("Game Over");
            Globals.getInstance().stopGame();
            Globals.getInstance().infoBox("You lost the game! You have "+ bodyCounter + " score", ":(");
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }
}
