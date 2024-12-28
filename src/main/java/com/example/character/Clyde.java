package com.example.character;

import java.util.Random;

import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.image.Image;

public class Clyde extends Ghost {
    private Image up = new Image(getClass().getResource("/com/example/pictures/clyde/clyde_up.png").toExternalForm());
    private Image down = new Image(getClass().getResource("/com/example/pictures/clyde/clyde_down.png").toExternalForm());
    private Image left = new Image(getClass().getResource("/com/example/pictures/clyde/clyde_left.png").toExternalForm());
    private Image right = new Image(getClass().getResource("/com/example/pictures/clyde/clyde_right.png").toExternalForm());

    public Clyde(double x, double y, GameLoop gameLoop) {
        super(x, y, gameLoop);
        scatterPoint = new int[]{1,1};  
        scatterPoints = new int[][]{{4,6},{3,15},{28,1},{7,6},{9,15},{1,25},{24,9},{25,3},
                                     {2,6},{5,16},{11,11},{16,9},{11,21},{13,6},{28,26},{20,8}}; 
        moveRight = new Animation(right, gameLoop.getG(), this, 1000000000 / 12);
        moveDown = new Animation(down, gameLoop.getG(), this, 1000000000 / 12);
        moveLeft = new Animation(left, gameLoop.getG(), this, 1000000000 / 12);
        moveUp = new Animation(up, gameLoop.getG(), this, 1000000000 / 12);
        inHouse=true;
        timeInHouse = 7000;
        curAnimation = moveUp;
        curAnimation.start();
  
    }

    @Override
    public void update() { 
        super.update();

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public int[] getDestination(char [][] maze) {
        int des[];
        if (gameLoop.point.getValue() >= 800 && state!=FRIGHTENED) {
            int desI;
            int desJ;

            if(Math.sqrt(Math.pow(x-gameLoop.getPacman().getX(),2)
            +Math.pow(y-gameLoop.getPacman().getY(),2))<=16*8){ // khoang cach nho hon 1 so nao do

                 desI=(int)((gameLoop.getPacman().getY()+8)/16+1e-4);
                 desJ= (int)((gameLoop.getPacman().getX()+8)/16+1e-4);  

            }
            else{
                return findRandomDes();
            }

            if(desI<0) desI=0;
            if(desJ<0) desJ=0;
            if(desI>=31) desI=30;
            if(desJ>=28) desJ=27;
            des = findDestination(desI, desJ, maze);
            if(des==null) return findRandomDes();

        } else {
           des= findRandomDes();
        }
        return des;
    }
}
