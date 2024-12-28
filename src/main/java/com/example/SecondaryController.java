package com.example;

import java.io.IOException;

import com.example.character.Pacman;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;



public class SecondaryController {
   
    @FXML
    Canvas canvas;
    @FXML
    Label textPoint;
    @FXML
    Label textLive;
    @FXML 
    Label textLevel;
    
    GameLoop gameLoop;
   
    public void initialize(){
        
        GraphicsContext g = (GraphicsContext)canvas.getGraphicsContext2D();
        gameLoop = GameLoop.getInstance(g);
        gameLoop.g=g;
        textPoint.textProperty().bind(Bindings.convert(GameLoop.point));
        textLevel.textProperty().bind(Bindings.convert(GameLoop.level));
        textLive.textProperty().bind(Bindings.convert(GameLoop.lives));
        
        canvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                canvas.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        gameLoop.pacman.addKeyListener(event.getCode());
                        canvas.requestFocus();
                    }
                });
                canvas.requestFocus();
            } 
        });
        gameLoop.start();

        
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");

    }
    

}