package com.example.pellet;

import com.example.GameLoop;

import javafx.scene.image.Image;

public class PacDot extends Pellet {
    

    public PacDot(double x, double y, GameLoop gameLoop) {
        super(x, y, 10, 4, 4,gameLoop);
        this.image = new Image(getClass().getResource("/com/example/pictures/pellet/pacdot.png").toExternalForm());
    }

    @Override
    public void update() {
    
    }

    @Override
    public void render()  {
        if(!isEaten){
            g.drawImage(image, x, y, width, height);
        }

    }
    
}
