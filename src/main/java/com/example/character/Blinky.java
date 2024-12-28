package com.example.character;

import java.util.Random;

import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.image.Image;

public class Blinky extends Ghost {
    Image up = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_up.png").toExternalForm());
    Image down = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_down.png").toExternalForm());
    Image left = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_left.png").toExternalForm());
    Image right = new Image(getClass().getResource("/com/example/pictures/blinky/blinky_right.png").toExternalForm());
    
    
    public Blinky(double x, double y, GameLoop gameLoop) {
        super(x, y, gameLoop);  
        scatterPoint=new int[]{1,26};
        scatterPoints = new int[][]{{12,9},{5,3},{1,26},{7, 6},{9, 15},{14,25},{24,24},{27,1},
                            {2, 15},{6, 9},{14, 2},{16, 6},{8, 10},{13, 6}};
        
        moveRight = new Animation(right, gameLoop.getG(), this, 1000000000/12);
        moveDown = new Animation(down, gameLoop.getG(), this, 1000000000/12);
        moveLeft = new Animation(left, gameLoop.getG(), this, 1000000000/12);
        moveUp = new Animation(up, gameLoop.getG(), this, 1000000000/12);
        timeInHouse =0;
        inHouse=false;
        curAnimation= moveRight;
        curAnimation.start();
        
    }
    @Override
    public void update(){
        super.update();
        if(gameLoop.point.get()>1000){
            v=3.2;
        }
    }
    @Override
    public void render(){
        super.render();
    }
    @Override
    public int[] getDestination(char [][] maze){
        int des[] ;
        if(gameLoop.point.getValue()>=800 && state != FRIGHTENED){
            int desI=(int)((gameLoop.getPacman().getY()+8)/16+1e-4);
            int desJ= (int)((gameLoop.getPacman().getX()+8)/16+1e-4);
            if(desI<0) desI=0;
            if(desJ<0) desJ=0;
            if(desI>=31) desI=30;
            if(desJ>=28) desJ=27;
            des = findDestination(desI, desJ, maze);

        }
        else{
            
             return findRandomDes();
            
        }

        return des;
    }
}
