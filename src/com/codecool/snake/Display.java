package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    private Text text = new Text(80,20,"Health: 100");


    public Display(Pane pane) {
        displayPane = pane;
        displayPane.getChildren().add(text);
        text.setFont(Font.font("Verdana", 20));
        text.setFill(Color.DARKRED);
    }

    public void changeHealtTitle(String updatedHealth){
        displayPane.getChildren().remove(text);
        text = new Text(80,20, updatedHealth);
        text.setFont(Font.font ("Verdana", 20));
        text.setFill(Color.DARKRED);
        displayPane.getChildren().add(text);
    }


    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
