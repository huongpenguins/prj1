package com.example.pellet;

import com.example.GameLoop;

import javafx.scene.image.Image;

public class PowerPellet extends Pellet {

    public PowerPellet(double x, double y,GameLoop gameLoop) {
        super(x, y, 50, 16, 16,gameLoop);
        this.image = new Image(getClass().getResource("/com/example/pictures/pellet/powerpellet.png").toExternalForm());
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render() {
        if(!isEaten){
            g.drawImage(image, x, y, width, height);
        }
    }
}
