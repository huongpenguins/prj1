package com.example.character;

import java.util.Random;
import com.example.Animation;
import com.example.GameLoop;

import javafx.scene.image.Image;

public class Inky extends Ghost {
    private Image up = new Image(getClass().getResource("/com/example/pictures/inky/inky_up.png").toExternalForm());
    private Image down = new Image(getClass().getResource("/com/example/pictures/inky/inky_down.png").toExternalForm());
    private Image left = new Image(getClass().getResource("/com/example/pictures/inky/inky_left.png").toExternalForm());
    private Image right = new Image(getClass().getResource("/com/example/pictures/inky/inky_right.png").toExternalForm());

    public Inky(double x, double y, GameLoop gameLoop) {
        super(x, y, gameLoop);
        scatterPoint = new int[]{28, 1};  
        scatterPoints = new int[][]{{20,4},{5,24},{28,1},{7,6},{9,15},{23,26},{24,9},{25,3},
                                     {2,15},{5,16},{14,2},{16,9},{23,12},{13,6},{28,26}}; 
        moveRight = new Animation(right, gameLoop.getG(), this, 1000000000/12);
        moveDown = new Animation(down, gameLoop.getG(), this, 1000000000/12);
        moveLeft = new Animation(left, gameLoop.getG(), this, 1000000000/12);
        moveUp = new Animation(up, gameLoop.getG(), this, 1000000000/12);
        inHouse=true;
        timeInHouse = 5000;
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
        int[] des;
        if (gameLoop.point.getValue() >= 800&& state != FRIGHTENED) {

            int desI=(int)(((gameLoop.getPacman().getY()+8)/16+1e-4) 
                    + ((gameLoop.blinky.getY()+8)/16+1e-4) )/2; // giua vi tri pacman va blinky
            int desJ= (int)(((gameLoop.getPacman().getX()+8)/16+1e-4) 
            + ((gameLoop.getPacman().getX()+8)/16+1e-4) )/2; 

            if(desI>=0&&desI<31&&desJ>=0&&desJ<28)
            if (maze[desI][desJ] == 'X') {
                for(int i = -1;i<=1;i++){
                    for(int j = -1;j<=1;j++){
                        if(desI+i>=0&&desI+i<31&&desJ+j>=0&&desJ+j<28)
                        if(maze[desI+i][desJ+j]!='X'){
                            desI+=i;
                            desJ+=j;
                            break;
                        }
                    }
                }
            }

            if(desI<0) desI=0;
            if(desJ<0) desJ=0;
            if(desI>=31) desI=30;
            if(desJ>=28) desJ=27;

            des = findDestination(desI, desJ, maze);
            if(des ==null){
                return findRandomDes();
            }
       
        } else {
            return findRandomDes();
        }
        return des;
    }
}
