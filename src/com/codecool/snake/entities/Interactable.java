package com.codecool.snake.entities;


// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void entitiesCollision(GameEntity entity);

    String getMessage();

}
